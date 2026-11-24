
package user;

public class InputValidator {

    // Validate user's name
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    // Validate email
    public static boolean isValidEmail(String email) {
        return email != null
                && email.contains("@")
                && email.contains(".");
    }

    // Validate password
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }

    // Validate user role
    public static boolean isValidRole(String role) {
        return role != null
                && (role.equalsIgnoreCase("STUDENT")
                || role.equalsIgnoreCase("STAFF")
                || role.equalsIgnoreCase("ADMIN"));
    }
}
