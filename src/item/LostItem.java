
package item;

public class LostItem extends Item {

    private String lastSeenDate;
    private String reward;

    public LostItem(int itemId, String itemName, String description,
                    String category, String location, String itemDate,
                    String status, String lastSeenDate, String reward) {

        super(itemId, itemName, description, category,
              location, itemDate, status);

        this.lastSeenDate = lastSeenDate;
        this.reward = reward;
    }

    public String getLastSeenDate() {
        return lastSeenDate;
    }

    public String getReward() {
        return reward;
    }
}

