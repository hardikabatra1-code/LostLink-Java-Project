
package item;

public class ItemService {

    private ItemDAO itemDAO;

    public ItemService() {
        itemDAO = new ItemDAO();
    }

    // REPORT LOST ITEM
    public boolean reportLostItem(String name, String description,
            String category, String location, String itemDate,
            String lastSeenDate, String reward, int userId) {

        LostItem lostItem = new LostItem(
                0, name, description, category, location,
                itemDate, "OPEN", lastSeenDate, reward
        );

        boolean result = itemDAO.addItem(lostItem, userId, "LOST");

        if (result) {
            System.out.println("Lost item reported successfully!");
        } else {
            System.out.println("Failed to report lost item.");
        }

        return result;
    }

    // REPORT FOUND ITEM
    public boolean reportFoundItem(String name, String description,
            String category, String location, String itemDate,
            String foundDate, String currentLocation, int userId) {

        FoundItem foundItem = new FoundItem(
                0, name, description, category, location,
                itemDate, "OPEN", foundDate, currentLocation, false
        );

        boolean result = itemDAO.addItem(foundItem, userId, "FOUND");

        if (result) {
            System.out.println("Found item reported successfully!");
        } else {
            System.out.println("Failed to report found item.");
        }

        return result;
    }

    // VIEW LOST ITEMS
    public void viewLostItems() {
        itemDAO.viewItems("LOST");
    }

    // VIEW FOUND ITEMS
    public void viewFoundItems() {
        itemDAO.viewItems("FOUND");
    }

    // ADMIN: VIEW ALL REPORTS
    public void viewAllItems() {
        itemDAO.viewAllItems();
    }

    // SEARCH ITEMS
    public void searchItems(String keyword) {
        itemDAO.searchItems(keyword);
    }

    // UPDATE ITEM STATUS
    public boolean updateItemStatus(int itemId, String status) {

        boolean result = itemDAO.updateItemStatus(itemId, status);

        if (result) {
            System.out.println("Item status updated successfully!");
        } else {
            System.out.println("Failed to update item status. Check Item ID.");
        }

        return result;
    }

    // VIEW ADMIN STATISTICS
    public void viewStatistics() {
        itemDAO.viewStatistics();
    }
}
