
package user;

public class Admin extends User {

    private String adminId;
    private String accessLevel;

    public Admin(int userId, String name, String email,
                 String passwordHash, String adminId,
                 String accessLevel) {

        super(userId, name, email, passwordHash, "ADMIN");

        this.adminId = adminId;
        this.accessLevel = accessLevel;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}
