package studentManagement;

public class Course {
    private String id;
    private String name;
    private double grade;

    public Course(String id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public Course(String id, double grade) {
        this.id = id;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return id + "," + grade;
    }
}
