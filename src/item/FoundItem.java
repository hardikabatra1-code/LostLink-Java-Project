
package item;

public class FoundItem extends Item {

    private String foundDate;
    private String currentLocation;
    private boolean submittedToStaff;

    public FoundItem(int itemId, String itemName, String description,
                     String category, String location, String itemDate,
                     String status, String foundDate,
                     String currentLocation, boolean submittedToStaff) {

        super(itemId, itemName, description, category,
              location, itemDate, status);

        this.foundDate = foundDate;
        this.currentLocation = currentLocation;
        this.submittedToStaff = submittedToStaff;
    }

    public String getFoundDate() {
        return foundDate;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public boolean isSubmittedToStaff() {
        return submittedToStaff;
    }
}
