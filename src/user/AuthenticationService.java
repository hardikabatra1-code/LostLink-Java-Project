
package user;

public class AuthenticationService {

    private User currentUser;

    // Get the currently logged-in user
    public User getCurrentUser() {
        return currentUser;
    }

    // Check whether a user is logged in
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    // Set the logged-in user
    public void setCurrentUser(User user) {
        currentUser = user;
    }

    // Logout the current user
    public void logout() {

        if (currentUser != null) {
            currentUser = null;
            System.out.println("You have been logged out successfully!");
        } else {
            System.out.println("No user is currently logged in.");
        }
    }
}
