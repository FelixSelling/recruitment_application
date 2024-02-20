package kth.iv1201.group9.recruitment_application.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import kth.iv1201.group9.recruitment_application.domain.DTO.PersonDTO;
import kth.iv1201.group9.recruitment_application.domain.entity.Person;
import kth.iv1201.group9.recruitment_application.domain.entity.Role;
import kth.iv1201.group9.recruitment_application.repository.PersonRepository;

@Service
public class RegistrationService {
    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @SuppressWarnings("null")
    public void handleRegisteredUser(PersonDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("UserDTO is null");
        }
        personRepo.save(validateInput(userDTO));
    }

    private Person validateInput(PersonDTO userDTO) {
        String name = userDTO.getName();
        String surname = userDTO.getSurname();
        String email = userDTO.getEmail();
        String pnr = userDTO.getPnr();
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        // NAME
        // Check if the name is not empty
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        // Check if the name contains only regular characters
        // a-z and A-Z
        if (!Pattern.matches("[a-zA-Z]+", name)) {
            throw new IllegalArgumentException("Name can only contain regular characters");
        }

        // SURNAME
        // Check if the surname is not empty
        if (surname == null || surname.isEmpty()) {
            throw new IllegalArgumentException("Surname is required");
        }
        // Check if the surname contains only regular characters
        // a-z and A-Z
        if (!Pattern.matches("[a-zA-Z]+", surname)) {
            throw new IllegalArgumentException("Surname can only contain regular characters");
        }

        // EMAIL
        // Check if the email is not empty
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        // Check if the email is valid
        // Source: https://howtodoinjava.com/regex/java-regex-validate-email-address/
        if (!Pattern.matches(
                "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
                email)) {
            throw new IllegalArgumentException("Email is not valid");
        }

        // PNR
        // Check if the pnr is not empty
        if (pnr == null || pnr.isEmpty()) {
            throw new IllegalArgumentException("Personal number is required");
        }
        // If 12 digits, add a hyphen after the 8th digit
        if (Pattern.matches("\\d{12}", pnr)) {
            pnr = pnr.substring(0, 8) + "-" + pnr.substring(8, 12);
        }
        // Check if the pnr contains only digits and a hyphen, 8 digits followed by a
        // hyphen and 4 digits
        if (!Pattern.matches("\\d{8}-\\d{4}", pnr)) {
            throw new IllegalArgumentException("Personal number needs to be in the format YYYYMMDD-XXXX");
        }
        // Check if the pnr contains a valid date
        if (!validDate(pnr.substring(0, 8))) {
            throw new IllegalArgumentException("Personal number does not contain a valid date");
        }
        // Check if the pnr is a correct personal number
        if (!validPnr(pnr)) {
            throw new IllegalArgumentException("Personal number can not be validated");
        }
        // Check if the pnr is unique
        if (personRepo.findByPnr(pnr) != null) {
            throw new IllegalArgumentException("Personal number is already registered");
        }

        // USERNAME
        // Check if the username is not empty
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        // Check if the username contains only regular alphanumerical characters
        // a-z, A-Z and 0-9
        if (!Pattern.matches("[a-zA-Z0-9]+", username)) {
            throw new IllegalArgumentException("Username can only contain regular alphanumerical characters");
        }
        // Check if the username is unique
        if (personRepo.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username is already taken");
        }

        // PASSWORD
        // Check if the password is not empty
        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        // Check if the password contains at least one digit, one lowercase letter, one
        // uppercase letter and one special character
        // To add a check for a special character, add
        // "(?=.*[!@#$%^&*()-_=+\\[\\]{};:'\",.<>\\/?])" to the regex
        if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", password)) {
            throw new IllegalArgumentException(
                    "Password must contain at least one digit, one lowercase letter, one uppercase letter and be at least 8 characters long");
        }

        // Create a new user and return it for saving
        Person user = new Person();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPnr(pnr);
        user.setUsername(username);
        user.setRole(new Role(2));
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }

    private boolean validDate(String date) {
        if (date == null || date.length() != 8) {
            return false;
        }

        // Throws DateTimeParseException if the date is not valid
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        try {
            LocalDate parsedDate = LocalDate.parse(date, formatter);

            // Check for year constraints
            int year = parsedDate.getYear();
            // Check if the year is within a reasonable range
            if (year < 1900 || year > Year.now().getValue() - 10) {
                return false;
            }

            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean validPnr(String pnr) {
        // Check if the pnr is exactly 13 characters long (including the hyphen)
        if (pnr == null || pnr.length() != 13) {
            return false;
        }

        // Check the format: 8 digits, a hyphen, then 4 digits
        if (!pnr.matches("\\d{8}-\\d{4}")) {
            return false;
        }

        // Remove the hyphen to work with the digits only
        String digits = pnr.replace("-", "");

        // Calculate the control digit using the Luhn algorithm
        return isLuhnValid(digits);
    }

    // Luhn algorithm
    // Returns true if the given digits are valid according to the Luhn algorithm
    // It processes each digit, doubling every second digit from the right. If the
    // doubled value is greater than 9, it is reduced by adding the digits
    // (equivalent to subtracting 9).
    // The sum of all processed digits is computed, and the number is valid if this
    // sum is divisible by 10.
    private boolean isLuhnValid(String digits) {
        int sum = 0;
        boolean alternate = false;

        for (int i = digits.length() - 1; i >= 0; i--) {
            // ASCII and UNICODE magic where '0' is 48 and '9' is 57, so subtracting '0'
            // from a digit char will give the int value of the digit
            int n = digits.charAt(i) - '0'; // Convert char to int

            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }

            sum += n;
            alternate = !alternate;
        }

        return sum % 10 == 0;
    }
}
