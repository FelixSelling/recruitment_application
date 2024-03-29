package kth.iv1201.group9.recruitment_application.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import kth.iv1201.group9.recruitment_application.exception.ValidationException;

/**
 * The ValidationService class provides methods for validating various types of
 * data,
 * such as names, surnames, email addresses, personal numbers, usernames, and
 * passwords.
 */
@Transactional
@Service
public class ValidationService {

    /**
     * Validates a name by checking if it is not empty and contains only regular
     * characters (a-z and A-Z).
     * 
     * @param name the name to be validated
     * @return true if the name is valid
     * @throws ValidationException if the name is empty or contains invalid
     *                             characters
     */
    public boolean validateName(String name) throws ValidationException {
        // Check if the name is not empty
        if (name == null || name.isEmpty()) {
            throw new ValidationException("error.validation.name.required");
        }
        // Check if the name contains only regular characters
        // a-z and A-Z
        if (!Pattern.matches("[a-zA-Z]+", name)) {
            throw new ValidationException("error.validation.name.invalid");
        }
        return true;
    }

    /**
     * Validates a surname by checking if it is not empty and contains only regular
     * characters (a-z and A-Z).
     *
     * @param surname the surname to be validated
     * @return true if the surname is valid
     * @throws ValidationException if the surname is empty or contains invalid
     *                             characters
     */
    public boolean validateSurname(String surname) throws ValidationException {
        // Check if the surname is not empty
        if (surname == null || surname.isEmpty()) {
            throw new ValidationException("error.validation.surname.required");
        }
        // Check if the surname contains only regular characters
        // a-z and A-Z
        if (!Pattern.matches("[a-zA-Z]+", surname)) {
            throw new ValidationException("error.validation.surname.invalid");
        }
        return true;
    }

    /**
     * Validates an email by checking if it is not empty and contains a valid email
     * address.
     * 
     * @param email the email address to be validated
     * @return true if the email address is valid
     * @throws ValidationException if the email address is empty or invalid
     */
    public boolean validateEmail(String email) throws ValidationException {
        // Check if the email is not empty
        if (email == null || email.isEmpty()) {
            throw new ValidationException("error.validation.email.required");
        }
        // Check if the email is valid
        // Source: https://howtodoinjava.com/regex/java-regex-validate-email-address/
        if (!Pattern.matches(
                "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
                email)) {
            throw new ValidationException("error.validation.email.invalid");
        }
        return true;
    }

    /**
     * Validates a personal number by checking if it is not empty, contains a valid
     * date and is a correct personal number.
     *
     * @param pnr The personal number to be validated.
     * @return true if the personal number is valid
     * @throws ValidationException if the personal number is empty, has an invalid
     *                             format, an invalid date, or is incorrect.
     */
    public boolean validatePnr(String pnr) throws ValidationException {
        // Check if the pnr is not empty
        if (pnr == null || pnr.isEmpty()) {
            throw new ValidationException("error.validation.pnr.required");
        }
        // Check if the pnr contains only digits and a hyphen, 8 digits followed by a
        // hyphen and 4 digits
        if (!Pattern.matches("\\d{8}-\\d{4}", pnr)) {
            throw new ValidationException("error.validation.pnr.format.invalid");
        }
        // Check if the pnr contains a valid date
        if (!validDate(pnr.substring(0, 8))) {
            throw new ValidationException("error.validation.pnr.date.invalid");
        }
        // Check if the pnr is a correct personal number
        if (!validPnr(pnr)) {
            throw new ValidationException("error.validation.pnr.invalid");
        }
        return true;
    }

    /**
     * Validates a username by checking if it is not empty and contains only regular
     * alphanumerical characters.
     *
     * @param username the username to be validated
     * @return true if the username is valid
     * @throws ValidationException if the username is empty or contains invalid
     *                             characters
     */
    public boolean validateUsername(String username) throws ValidationException {
        // Check if the username is not empty
        if (username == null || username.isEmpty()) {
            throw new ValidationException("error.validation.username.required");
        }
        // Check if the username contains only regular alphanumerical characters
        // a-z, A-Z and 0-9
        if (!Pattern.matches("[a-zA-Z0-9]+", username)) {
            throw new ValidationException("error.validation.username.invalid");
        }
        return true;
    }

    /**
     * Validates a password based on the following criteria:
     * - The password must not be empty.
     * - The password must contain at least one digit, one lowercase letter, and one
     * uppercase letter.
     * - The password must be at least 8 characters long.
     * 
     * @param password the password to be validated
     * @return true if the password is valid
     * @throws ValidationException if the password is not valid
     */
    public boolean validatePassword(String password) throws ValidationException {
        // Check if the password is not empty
        if (password == null || password.isEmpty()) {
            throw new ValidationException("error.validation.password.required");
        }
        // Check if the password contains at least one digit, one lowercase letter and
        // one uppercase letter
        // To add a check for a special character, add
        // "(?=.*[!@#$%^&*()-_=+\\[\\]{};:'\",.<>\\/?])" to the regex
        if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", password)) {
            throw new ValidationException(
                    "error.validation.password.invalid");
        }
        return true;
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
