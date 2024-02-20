public class Validation {
    public static void validateInput(String firstName, String lastName, String address,
                                     String email, String password) throws UserInputValidationException {
        // Validate First Name: Alphabetic characters only
        if (!firstName.matches("[a-zA-Z]+")) {
            throw new UserInputValidationException("Invalid first name. Please use alphabetic characters only.");
        }

        // Validate Last Name: Alphabetic characters only
        if (!lastName.matches("[a-zA-Z]+")) {
            throw new UserInputValidationException("Invalid last name. Please use alphabetic characters only.");
        }

        // Validate Address: Alphanumeric characters and space
        if (!address.matches("[a-zA-Z0-9 ]+")) {
            throw new UserInputValidationException("Invalid address. "
                    + "Please use alphanumeric characters and space only.");
        }

        // Validate Email Address: Must contain '@' symbol
        if (!email.contains("@")) {
            throw new UserInputValidationException("Invalid email address. Please include '@' symbol.");
        }

        // Validate Password: Alphanumeric characters and special characters
        if (!password.matches("[a-zA-Z0-9@#$%^&*!]+")) {
            throw new UserInputValidationException("Invalid password. "
                    + "Please use alphanumeric characters and special characters only.");
        }
    }
}
