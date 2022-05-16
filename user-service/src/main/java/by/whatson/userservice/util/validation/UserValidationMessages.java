package by.whatson.userservice.util.validation;

public class UserValidationMessages {
    public static final String FOR_USERNAME=
            "Username consists of alphanumeric characters (a-zA-Z0-9), lowercase, or uppercase.\n" +
            "Username allowed of the dot (.), underscore (_), and hyphen (-).\n" +
            "The dot (.), underscore (_), or hyphen (-) must not be the first or last character.\n" +
            "The dot (.), underscore (_), or hyphen (-) does not appear consecutively, e.g., java..regex\n" +
            "The number of characters must be between 5 to 20.";
    public static final String FOR_PHONE="Phone number is invalid";
}
