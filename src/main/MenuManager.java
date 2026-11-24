
package main;

import java.util.Scanner;

public class MenuManager {

    private Scanner scanner = new Scanner(System.in);

    // Display main menu
    public int showMainMenu() {

        System.out.println("\n===== LOSTLINK MAIN MENU =====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");

        System.out.print("Enter your choice: ");

        return scanner.nextInt();
    }

    // Display student menu
    public void showStudentMenu() {

        System.out.println("\n===== STUDENT MENU =====");
        System.out.println("1. Report Lost Item");
        System.out.println("2. Report Found Item");
        System.out.println("3. Search Items");
        System.out.println("4. Logout");
    }

    // Display staff menu
    public void showStaffMenu() {

        System.out.println("\n===== STAFF MENU =====");
        System.out.println("1. View Lost Items");
        System.out.println("2. View Found Items");
        System.out.println("3. Search Items");
        System.out.println("4. Logout");
    }

    // Display admin menu
    public void showAdminMenu() {

        System.out.println("\n===== ADMIN MENU =====");
        System.out.println("1. Manage Users");
        System.out.println("2. View All Reports");
        System.out.println("3. Logout");
    }
}
