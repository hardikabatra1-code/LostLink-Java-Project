
package user;

public class UserService {

    private AuthenticationService authService;
    private UserDAO userDAO;

    // Constructor
    public UserService() {
        authService = new AuthenticationService();
        userDAO = new UserDAO();
    }

    // Register a new user
    public boolean registerUser(String name, String email,
                                String password, String role) {

        if (!InputValidator.isValidName(name)
                || !InputValidator.isValidEmail(email)
                || !InputValidator.isValidPassword(password)
                || !InputValidator.isValidRole(role)) {

            System.out.println("Invalid user details!");
            return false;
        }

        if (userDAO.findUserByEmail(email) != null) {
            System.out.println("Email already registered!");
            return false;
        }

        User user = new User(
                0, name, email, password, role.toUpperCase()
        );

        boolean registered = userDAO.registerUser(user);

        if (registered) {
            System.out.println("Registration successful!");
        }

        return registered;
    }

    // Login user
    public boolean loginUser(String email, String password) {

        User user = userDAO.findUserByEmail(email);

        if (user != null
                && user.getPasswordHash().equals(password)) {

            authService.setCurrentUser(user);
            System.out.println("Login successful!");
            return true;
        }

        System.out.println("Invalid email or password!");
        return false;
    }

    // Get currently logged-in user
    public User getCurrentUser() {
        return authService.getCurrentUser();
    }

    // Check login status
    public boolean isLoggedIn() {
        return authService.isLoggedIn();
    }

    // Logout user
    public void logout() {
        authService.logout();
    }

    // Update current user's session
    public void setCurrentUser(User user) {
        authService.setCurrentUser(user);
    }

    // Admin: View all registered users
    public void viewAllUsers() {
        userDAO.viewAllUsers();
    }
}
