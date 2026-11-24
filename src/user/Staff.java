
package user;

public class Staff extends User {

    private String staffId;
    private String department;

    public Staff(int userId, String name, String email,
                 String passwordHash, String staffId,
                 String department) {

        super(userId, name, email, passwordHash, "STAFF");

        this.staffId = staffId;
        this.department = department;
    }

    public String getStaffId() {
        return staffId;
    }

    public String getDepartment() {
        return department;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
