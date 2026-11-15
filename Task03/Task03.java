
import java.util.HashSet;
import java.util.Set;

class Student {
    String regNo;
    String name;

    Student(String regNo, String name) {
        this.regNo = regNo;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student other = (Student) o;
        return regNo.equals(other.regNo);
    }

    // TODO 1: hashCode() will be added after the first experiment.

    @Override
    public String toString() {
        return regNo + " " + name;
    }
}

public class Task03 {

    static void test1() {
        System.out.println("\nTEST 1 - SAME OBJECT SCANNED TWICE");
        Set<Student> set = new HashSet<>();

        Student s1 = new Student("192311001", "Arun");

        set.add(s1);
        set.add(s1);

        System.out.println("Prediction: 1 student");
        System.out.println("Actual Set Size: " + set.size());
    }

    static void test2() {
        System.out.println("\nTEST 2 - DIFFERENT OBJECTS, SAME REGISTRATION NUMBER");
        Set<Student> set = new HashSet<>();

        set.add(new Student("192311001", "Arun"));
        set.add(new Student("192311002", "Divya"));
        set.add(new Student("192311001", "Arun"));

        System.out.println("Prediction: 2 unique students");
        System.out.println("Actual Set Size: " + set.size());
        System.out.println("Expected after correction: 2");
    }

    static void test3() {
        System.out.println("\nTEST 3 - CHANGE REGISTRATION NUMBER");

        Set<Student> set = new HashSet<>();

        Student s1 = new Student("192311010", "Kumar");
        set.add(s1);

        System.out.println("Before modification: " + set.contains(s1));

        s1.regNo = "192311099";

        System.out.println("After modification: " + set.contains(s1));
        System.out.println("Note: Changing a key field can affect HashSet lookup.");
    }

    public static void main(String[] args) {

        System.out.println("HASHSET EQUALS AND HASHCODE ANALYSIS");
        System.out.println("====================================");

        System.out.println("\nCURRENT VERSION: hashCode() NOT OVERRIDDEN");

        test1();
        test2();
        test3();

        System.out.println("\nTESTING COMPLETED");
    }
}
