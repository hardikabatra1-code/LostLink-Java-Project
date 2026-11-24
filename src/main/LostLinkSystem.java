
package main;

import java.util.Scanner;

import user.User;
import user.UserService;
import item.ItemService;

public class LostLinkSystem {

    private Scanner scanner = new Scanner(System.in);
    private UserService userService = new UserService();
    private ItemService itemService = new ItemService();

    // MAIN MENU
    public void start() {

        int choice;

        System.out.println("==============================");
        System.out.println("       WELCOME TO LOSTLINK");
        System.out.println("==============================");

        do {
            System.out.println("\nMAIN MENU");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    register();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    System.out.println("Thank you for using LostLink!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 3);
    }

    // USER REGISTRATION
    private void register() {

        System.out.println("\n--- USER REGISTRATION ---");

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter role (STUDENT/STAFF/ADMIN): ");
        String role = scanner.nextLine();

        userService.registerUser(name, email, password, role);
    }

    // USER LOGIN
    private void login() {

        System.out.println("\n--- USER LOGIN ---");

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        boolean success = userService.loginUser(email, password);

        if (success) {

            User user = userService.getCurrentUser();

            System.out.println("\nWelcome, " + user.getName() + "!");
            System.out.println("Your role: " + user.getRole());

            switch (user.getRole().toUpperCase()) {

                // STUDENT DASHBOARD
                case "STUDENT":

                    int studentChoice;

                    do {
                        System.out.println("\n===== STUDENT DASHBOARD =====");
                        System.out.println("1. Report Lost Item");
                        System.out.println("2. Report Found Item");
                        System.out.println("3. Search Items");
                        System.out.println("4. Logout");

                        System.out.print("Enter your choice: ");
                        studentChoice = Integer.parseInt(scanner.nextLine());

                        switch (studentChoice) {

                            case 1:
                                reportLostItem();
                                break;

                            case 2:
                                reportFoundItem();
                                break;

                            case 3:
                                System.out.print("Enter item name, category or description to search: ");
                                String keyword = scanner.nextLine();
                                itemService.searchItems(keyword);
                                break;

                            case 4:
                                System.out.println("Logging out...");
                                break;

                            default:
                                System.out.println("Invalid choice! Try again.");
                        }

                    } while (studentChoice != 4);

                    break;

                // STAFF DASHBOARD
                case "STAFF":

                    int staffChoice;

                    do {
                        System.out.println("\n===== STAFF DASHBOARD =====");
                        System.out.println("1. View Lost Reports");
                        System.out.println("2. View Found Reports");
                        System.out.println("3. Manage Reports");
                        System.out.println("4. Logout");

                        System.out.print("Enter your choice: ");
                        staffChoice = Integer.parseInt(scanner.nextLine());

                        switch (staffChoice) {

                            case 1:
                                System.out.println("\n===== ALL LOST REPORTS =====");
                                itemService.viewLostItems();
                                break;

                            case 2:
                                System.out.println("\n===== ALL FOUND REPORTS =====");
                                itemService.viewFoundItems();
                                break;

                            case 3:
                                manageReports();
                                break;

                            case 4:
                                System.out.println("Logging out...");
                                break;

                            default:
                                System.out.println("Invalid choice! Try again.");
                        }

                    } while (staffChoice != 4);

                    break;

                // ADMIN DASHBOARD
                case "ADMIN":

                    int adminChoice;

                    do {
                        System.out.println("\n===== ADMIN DASHBOARD =====");
                        System.out.println("1. Manage Users");
                        System.out.println("2. Manage All Reports");
                        System.out.println("3. View Statistics");
                        System.out.println("4. Logout");

                        System.out.print("Enter your choice: ");
                        adminChoice = Integer.parseInt(scanner.nextLine());

                        switch (adminChoice) {

                            case 1:
                                userService.viewAllUsers();
                                break;

                            case 2:
                                itemService.viewAllItems();
                                break;

                            case 3:
                                itemService.viewStatistics();
                                break;

                            case 4:
                                System.out.println("Logging out...");
                                break;

                            default:
                                System.out.println("Invalid choice! Try again.");
                        }

                    } while (adminChoice != 4);

                    break;

                default:
                    System.out.println("Unknown user role!");
            }

            userService.logout();
        }
    }

    // REPORT LOST ITEM
    private void reportLostItem() {

        System.out.println("\n===== REPORT LOST ITEM =====");

        System.out.print("Enter item name: ");
        String name = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.print("Enter location where item was lost: ");
        String location = scanner.nextLine();

        System.out.print("Enter item date (YYYY-MM-DD): ");
        String itemDate = scanner.nextLine();

        System.out.print("Enter last seen date (YYYY-MM-DD): ");
        String lastSeenDate = scanner.nextLine();

        System.out.print("Enter reward (or press Enter for no reward): ");
        String reward = scanner.nextLine();

        User user = userService.getCurrentUser();

        itemService.reportLostItem(
                name, description, category, location,
                itemDate, lastSeenDate, reward, user.getUserId()
        );
    }

    // REPORT FOUND ITEM
    private void reportFoundItem() {

        System.out.println("\n===== REPORT FOUND ITEM =====");

        System.out.print("Enter item name: ");
        String name = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.print("Enter location where item was found: ");
        String location = scanner.nextLine();

        System.out.print("Enter item date (YYYY-MM-DD): ");
        String itemDate = scanner.nextLine();

        System.out.print("Enter found date (YYYY-MM-DD): ");
        String foundDate = scanner.nextLine();

        System.out.print("Enter current location of item: ");
        String currentLocation = scanner.nextLine();

        User user = userService.getCurrentUser();

        itemService.reportFoundItem(
                name, description, category, location,
                itemDate, foundDate, currentLocation, user.getUserId()
        );
    }

    // MANAGE REPORTS
    private void manageReports() {

        System.out.println("\n===== MANAGE REPORTS =====");

        System.out.print("Enter Item ID: ");
        int itemId = Integer.parseInt(scanner.nextLine());

        System.out.println("1. Mark as OPEN");
        System.out.println("2. Mark as RESOLVED");

        System.out.print("Enter your choice: ");
        int statusChoice = Integer.parseInt(scanner.nextLine());

        String status;

        if (statusChoice == 1) {
            status = "OPEN";
        } else if (statusChoice == 2) {
            status = "RESOLVED";
        } else {
            System.out.println("Invalid choice!");
            return;
        }

        itemService.updateItemStatus(itemId, status);
    }
}
