import java.util.*;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    int enrolled;

    Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0; // No students enrolled initially
    }

    boolean isAvailable() {
        return enrolled < capacity;
    }

    void enrollStudent() {
        if (isAvailable()) {
            enrolled++;
        } else {
            System.out.println("Course " + title + " is full.");
        }
    }

    void dropStudent() {
        if (enrolled > 0) {
            enrolled--;
        } else {
            System.out.println("No students to drop from course " + title + ".");
        }
    }

    @Override
    public String toString() {
        return code + ": " + title + " (" + enrolled + "/" + capacity + " enrolled) - " + description;
    }
}

class Student {
    String studentID;
    String name;
    List<Course> registeredCourses = new ArrayList<>();

    Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    void registerCourse(Course course) {
        if (course.isAvailable()) {
            registeredCourses.add(course);
            course.enrollStudent();
            System.out.println(name + " has registered for " + course.title);
        } else {
            System.out.println(course.title + " is full. Registration failed.");
        }
    }

    void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.dropStudent();
            System.out.println(name + " has dropped " + course.title);
        } else {
            System.out.println(name + " is not registered for " + course.title);
        }
    }

    void viewRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println(name + " is not registered for any courses.");
        } else {
            System.out.println(name + "'s Registered Courses:");
            for (Course course : registeredCourses) {
                System.out.println(course);
            }
        }
    }
}

public class UniversityRegistrationSystem {
    static List<Course> courses = new ArrayList<>();
    static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        // Creating some sample courses
        Course course1 = new Course("CS101", "Introduction to Computer Science", "Learn basic computer science concepts", 3);
        Course course2 = new Course("MATH101", "Calculus I", "Introductory calculus course", 2);
        courses.add(course1);
        courses.add(course2);

        // Creating some sample students
        Student student1 = new Student("S001", "Alice");
        Student student2 = new Student("S002", "Bob");
        students.add(student1);
        students.add(student2);

        // Display available courses
        displayCourses();

        // Student registration
        student1.registerCourse(course1);
        student2.registerCourse(course1);  // Bob also registers for CS101
        student2.registerCourse(course2);  // Bob registers for MATH101

        // Viewing registered courses
        student1.viewRegisteredCourses();
        student2.viewRegisteredCourses();

        // Dropping a course
        student1.dropCourse(course1);
        student1.viewRegisteredCourses();

        // Recheck available courses after registration and drop
        displayCourses();
    }

    // Display the available courses
    public static void displayCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }
}
