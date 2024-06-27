package studentManagement;
import java.util.ArrayList;
import java.util.List;
public class Student {
    private int id;
    private String name;
    private List<Course> courses;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(String courseId) {
        courses.removeIf(course -> course.getId().equals(courseId));
    }

    public double calculateGPA() {
        if (courses.isEmpty()) return 0.0;
        double totalPoints = 0.0;
        for (Course course : courses) {
            totalPoints += course.getGrade();
        }
        return totalPoints / courses.size();
    }

    @Override
    public String toString() {
        return id + "," + name + "," + courses.toString().replace("[", "").replace("]", "").replace(" ", "");
    }

    public static Student fromString(String studentStr) {
        String[] parts = studentStr.split(",", 3);
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        Student student = new Student(id, name);
        if (parts.length > 2) {
            String[] courseStrs = parts[2].split(",");
            for (int i = 0; i < courseStrs.length; i += 2) {
                student.addCourse(new Course(courseStrs[i], Double.parseDouble(courseStrs[i + 1])));
            }
        }
        return student;
    }
}
