
package item;

public class Item {

    private int itemId;
    private String itemName;
    private String description;
    private String category;
    private String location;
    private String itemDate;
    private String status;

    public Item(int itemId, String itemName, String description,
                String category, String location, String itemDate,
                String status) {

        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.category = category;
        this.location = location;
        this.itemDate = itemDate;
        this.status = status;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public String getItemDate() {
        return itemDate;
    }

    public String getStatus() {
        return status;
    }
}

