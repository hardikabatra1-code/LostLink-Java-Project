
package user;

public class Student extends User {

    private String studentId;
    private String course;

    public Student(int userId, String name, String email,
                   String passwordHash, String studentId,
                   String course) {

        super(userId, name, email, passwordHash, "STUDENT");

        this.studentId = studentId;
        this.course = course;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourse() {
        return course;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
