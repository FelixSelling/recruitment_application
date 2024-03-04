package kth.iv1201.group9.recruitment_application.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import kth.iv1201.group9.recruitment_application.exception.ValidationException;
import kth.iv1201.group9.recruitment_application.repository.PersonRepository;

@Transactional
@Service
public class ValidationService {
    @Autowired
    private PersonRepository personRepo;

    /**
     * Validates a name by checking if it is not empty and contains only regular
     * characters (a-z and A-Z).
     *
     * @param name the name to be validated
     * @return the validated name
     * @throws ValidationException if the name is empty or contains non-regular
     *                             characters
     */
    public String validateName(String name) throws ValidationException {
        // Check if the name is not empty
        if (name == null || name.isEmpty()) {
            throw new ValidationException("error.registration.validation.name.required");
        }
        // Check if the name contains only regular characters
        // a-z and A-Z
        if (!Pattern.matches("[a-zA-Z]+", name)) {
            throw new ValidationException("error.registration.validation.name.invalid");
        }
        return name;
    }

    /**
     * Validates a surname by checking if it is not empty and contains only regular
     * characters (a-z and A-Z).
     *
     * @param surname the surname to be validated
     * @return the validated surname
     * @throws ValidationException if the surname is empty or contains
     *                             non-regular characters
     */
    public String validateSurname(String surname) throws ValidationException {
        // Check if the surname is not empty
        if (surname == null || surname.isEmpty()) {
            throw new ValidationException("error.registration.validation.surname.required");
        }
        // Check if the surname contains only regular characters
        // a-z and A-Z
        if (!Pattern.matches("[a-zA-Z]+", surname)) {
            throw new ValidationException("error.registration.validation.surname.invalid");
        }
        return surname;
    }

    /**
     * Validates an email by checking if it is not empty and contains a valid email
     * address.
     *
     * @param email the email to be validated
     * @return the validated email
     * @throws ValidationException if the email is empty or not a valid email
     *                             address
     */
    public String validateEmail(String email) throws ValidationException {
        // Check if the email is not empty
        if (email == null || email.isEmpty()) {
            throw new ValidationException("error.registration.validation.email.required");
        }
        // Check if the email is valid
        // Source: https://howtodoinjava.com/regex/java-regex-validate-email-address/
        if (!Pattern.matches(
                "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
                email)) {
            throw new ValidationException("error.registration.validation.email.invalid");
        }
        // Check if the email is unique
        // if (personRepo.findByEmail(email) != null) {
        // throw new ValidationException("error.registration.validation.email.taken");
        // }
        return email;
    }

    /**
     * Validates a personal number by checking if it is not empty, contains a valid
     * date, is a correct personal number and is unique.
     *
     * @param pnr the personal number to be validated
     * @return the validated personal number
     * @throws ValidationException if the pnr is empty, not in the correct
     *                             format, does not contain a valid date, can
     *                             not be validated or is not unique
     */
    public String validatePnr(String pnr) throws ValidationException {
        // Check if the pnr is not empty
        if (pnr == null || pnr.isEmpty()) {
            throw new ValidationException("error.registration.validation.pnr.required");
        }
        // If 12 digits, add a hyphen after the 8th digit
        if (Pattern.matches("\\d{12}", pnr)) {
            pnr = pnr.substring(0, 8) + "-" + pnr.substring(8, 12);
        }
        // Check if the pnr contains only digits and a hyphen, 8 digits followed by a
        // hyphen and 4 digits
        if (!Pattern.matches("\\d{8}-\\d{4}", pnr)) {
            throw new ValidationException("error.registration.validation.pnr.format.invalid");
        }
        // Check if the pnr contains a valid date
        if (!validDate(pnr.substring(0, 8))) {
            throw new ValidationException("error.registration.validation.pnr.date.invalid");
        }
        // Check if the pnr is a correct personal number
        if (!validPnr(pnr)) {
            throw new ValidationException("error.registration.validation.pnr.invalid");
        }
        // Check if the pnr is unique
        if (personRepo.findByPnr(pnr) != null) {
            throw new ValidationException("error.registration.validation.pnr.taken");
        }
        return pnr;
    }

    /**
     * Validates a username by checking if it is not empty, contains only regular
     * alphanumerical characters and is unique.
     *
     * @param username the username to be validated
     * @return the validated username
     * @throws ValidationException if the username is empty, contains
     *                             non-regular alphanumerical characters or is
     *                             not unique
     */
    public String validateUsername(String username) throws ValidationException {
        // Check if the username is not empty
        if (username == null || username.isEmpty()) {
            throw new ValidationException("error.registration.validation.username.required");
        }
        // Check if the username contains only regular alphanumerical characters
        // a-z, A-Z and 0-9
        if (!Pattern.matches("[a-zA-Z0-9]+", username)) {
            throw new ValidationException("error.registration.validation.username.invalid");
        }
        // Check if the username is unique
        if (personRepo.findByUsername(username) != null) {
            throw new ValidationException("error.registration.validation.username.taken");
        }
        return username;
    }

    /**
     * Validates a password by checking if it is not empty and contains at least one
     * digit, one lowercase letter and one uppercase letter.
     *
     * @param password the password to be validated
     * @return the validated password
     * @throws ValidationException if the password is empty or does not contain
     *                             at least one digit, one lowercase letter and
     *                             one uppercase letter
     */
    public String validatePassword(String password) throws ValidationException {
        // Check if the password is not empty
        if (password == null || password.isEmpty()) {
            throw new ValidationException("error.registration.validation.password.required");
        }
        // Check if the password contains at least one digit, one lowercase letter and
        // one uppercase letter
        // To add a check for a special character, add
        // "(?=.*[!@#$%^&*()-_=+\\[\\]{};:'\",.<>\\/?])" to the regex
        if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", password)) {
            throw new ValidationException(
                    "error.registration.validation.password.invalid");
        }
        return password;
    }

    /**
     * Validates a date by checking if it is not null and contains a valid date.
     * 
     * @param date the date string to be validated
     * @return true if the date is valid, false otherwise
     */
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
            if (year < 1900 || year > Year.now().getValue() - 1) {
                return false;
            }

            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Validates a personal number by checking if it is not null, is exactly 13
     * characters long and is valid according to the Luhn algorithm.
     * 
     * @param pnr the personal number to be validated
     * @return true if the personal number is valid, false otherwise
     */
    private boolean validPnr(String pnr) {
        // Check if the pnr is exactly 13 characters long (including the hyphen)
        if (pnr == null || pnr.length() != 13) {
            return false;
        }

        // Check the format: 8 digits, a hyphen, then 4 digits
        if (!pnr.matches("\\d{8}-\\d{4}")) {
            return false;
        }

        // Remove the hyphen to work with the digits only, and remove the first two
        // digits to conform to the version of the Luhn algorithm that is used in Sweden
        String digits = pnr.replace("-", "").substring(2, 12);

        // Calculate the control digit using the Luhn algorithm
        return isLuhnValid(digits);
    }

    /**
     * Checks if the given digits are valid according to the Luhn algorithm.
     * 
     * @param digits the digits to be validated
     * @return true if the digits are valid, false otherwise
     */
    private boolean isLuhnValid(String digits) {
        // Luhn algorithm
        // Returns true if the given digits are valid according to the Luhn algorithm
        // It processes each digit, doubling every second digit from the right. If the
        // doubled value is greater than 9, it is reduced by adding the digits
        // (equivalent to subtracting 9).
        // The sum of all processed digits is computed, and the number is valid if this
        // sum is divisible by 10.
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
