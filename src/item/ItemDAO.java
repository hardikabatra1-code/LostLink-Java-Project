
package item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseConnection;

public class ItemDAO {

    // ADD LOST OR FOUND ITEM
    public boolean addItem(Item item, int userId, String itemType) {

        String query = "INSERT INTO items "
                + "(item_name, description, category, location, item_date, "
                + "item_type, status, user_id, last_seen_date, reward, "
                + "found_date, current_location, submitted_to_staff) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, item.getItemName());
            ps.setString(2, item.getDescription());
            ps.setString(3, item.getCategory());
            ps.setString(4, item.getLocation());
            ps.setString(5, item.getItemDate());
            ps.setString(6, itemType);
            ps.setString(7, item.getStatus());
            ps.setInt(8, userId);

            if (item instanceof LostItem) {
                LostItem lostItem = (LostItem) item;
                ps.setString(9, lostItem.getLastSeenDate());
                ps.setString(10, lostItem.getReward());
            } else {
                ps.setNull(9, java.sql.Types.DATE);
                ps.setNull(10, java.sql.Types.VARCHAR);
            }

            if (item instanceof FoundItem) {
                FoundItem foundItem = (FoundItem) item;
                ps.setString(11, foundItem.getFoundDate());
                ps.setString(12, foundItem.getCurrentLocation());
                ps.setBoolean(13, foundItem.isSubmittedToStaff());
            } else {
                ps.setNull(11, java.sql.Types.DATE);
                ps.setNull(12, java.sql.Types.VARCHAR);
                ps.setBoolean(13, false);
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding item: " + e.getMessage());
            return false;
        }
    }

    // VIEW LOST OR FOUND ITEMS
    public void viewItems(String itemType) {

        String query = "SELECT * FROM items WHERE item_type = ? ORDER BY item_id";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, itemType);

            try (ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                while (rs.next()) {
                    found = true;

                    System.out.println("\n-------------------------");
                    System.out.println("Item ID: " + rs.getInt("item_id"));
                    System.out.println("Name: " + rs.getString("item_name"));
                    System.out.println("Description: " + rs.getString("description"));
                    System.out.println("Category: " + rs.getString("category"));
                    System.out.println("Location: " + rs.getString("location"));
                    System.out.println("Date: " + rs.getString("item_date"));
                    System.out.println("Type: " + rs.getString("item_type"));
                    System.out.println("Status: " + rs.getString("status"));
                    System.out.println("-------------------------");
                }

                if (!found) {
                    System.out.println("No reports found!");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error viewing items: " + e.getMessage());
        }
    }

    // ADMIN: VIEW ALL ITEMS
    public void viewAllItems() {

        String query = "SELECT * FROM items ORDER BY item_id";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            System.out.println("\n===== ALL LOST AND FOUND REPORTS =====");

            while (rs.next()) {
                found = true;

                System.out.println("\n-------------------------");
                System.out.println("Item ID: " + rs.getInt("item_id"));
                System.out.println("Name: " + rs.getString("item_name"));
                System.out.println("Description: " + rs.getString("description"));
                System.out.println("Category: " + rs.getString("category"));
                System.out.println("Location: " + rs.getString("location"));
                System.out.println("Date: " + rs.getString("item_date"));
                System.out.println("Type: " + rs.getString("item_type"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("Reported By User ID: " + rs.getInt("user_id"));
                System.out.println("-------------------------");
            }

            if (!found) {
                System.out.println("No reports found!");
            }

        } catch (SQLException e) {
            System.out.println("Error viewing all reports: " + e.getMessage());
        }
    }

    // SEARCH ITEMS
    public void searchItems(String keyword) {

        String query = "SELECT * FROM items "
                + "WHERE item_name LIKE ? "
                + "OR category LIKE ? "
                + "OR description LIKE ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            String searchValue = "%" + keyword + "%";

            ps.setString(1, searchValue);
            ps.setString(2, searchValue);
            ps.setString(3, searchValue);

            try (ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                while (rs.next()) {
                    found = true;

                    System.out.println("\n-------------------------");
                    System.out.println("Item ID: " + rs.getInt("item_id"));
                    System.out.println("Name: " + rs.getString("item_name"));
                    System.out.println("Description: " + rs.getString("description"));
                    System.out.println("Category: " + rs.getString("category"));
                    System.out.println("Location: " + rs.getString("location"));
                    System.out.println("Type: " + rs.getString("item_type"));
                    System.out.println("Status: " + rs.getString("status"));
                    System.out.println("-------------------------");
                }

                if (!found) {
                    System.out.println("No matching items found!");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching items: " + e.getMessage());
        }
    }

    // UPDATE ITEM STATUS
    public boolean updateItemStatus(int itemId, String status) {

        String query = "UPDATE items SET status = ? WHERE item_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, status);
            ps.setInt(2, itemId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating item status: " + e.getMessage());
            return false;
        }
    }

    // VIEW ADMIN STATISTICS
    public void viewStatistics() {

        String query = "SELECT "
                + "(SELECT COUNT(*) FROM users) AS total_users, "
                + "(SELECT COUNT(*) FROM items) AS total_reports, "
                + "(SELECT COUNT(*) FROM items WHERE item_type = 'LOST') AS lost_reports, "
                + "(SELECT COUNT(*) FROM items WHERE item_type = 'FOUND') AS found_reports, "
                + "(SELECT COUNT(*) FROM items WHERE status = 'OPEN') AS open_reports, "
                + "(SELECT COUNT(*) FROM items WHERE status = 'RESOLVED') AS resolved_reports";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {

                System.out.println("\n===== LOSTLINK STATISTICS =====");

                System.out.println("Total Registered Users: "
                        + rs.getInt("total_users"));

                System.out.println("Total Reports: "
                        + rs.getInt("total_reports"));

                System.out.println("Lost Reports: "
                        + rs.getInt("lost_reports"));

                System.out.println("Found Reports: "
                        + rs.getInt("found_reports"));

                System.out.println("Open Reports: "
                        + rs.getInt("open_reports"));

                System.out.println("Resolved Reports: "
                        + rs.getInt("resolved_reports"));
            }

        } catch (SQLException e) {
            System.out.println("Error displaying statistics: " + e.getMessage());
        }
    }
}
