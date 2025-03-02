package LMS;

//  FIXED LSP: Created a separate interface for salary-related functionality
public interface staff {
    double getSalary();
}

//  FIXED LSP: Person remains generic (no salary field here)
public abstract class Person {
    protected int id;
    protected String name;
    protected String address;
    protected int phoneNo;

    public Person(int id, String name, String address, int phoneNo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    //  Print common person details
    public void printInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Phone No: " + phoneNo);
    }
}

//  FIXED LSP: Now Staff implements Payable (only Staff has salary)
public class Staff extends Person implements staff {
    private double salary; // Moved salary responsibility here

    public Staff(int id, String name, String address, int phoneNo, double salary) {
        super(id, name, address, phoneNo);
        this.salary = salary;
    }

    @Override
    public void printInfo() {
        super.printInfo(); 
        System.out.println("Salary: " + salary); //  Now only Staff prints salary
    }

    @Override
    public double getSalary() {
        return salary; //  Only Staff has salary logic
    }
}

//  A new class (e.g., Student) can extend Person without being forced to have salary
public class Student extends Person {
    private String course;

    public Student(int id, String name, String address, int phoneNo, String course) {
        super(id, name, address, phoneNo);
        this.course = course;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Course: " + course); //  No salary here, LSP fixed
    }
}

//  Demonstration (LSP Fixed)
public class Main {
    public static void main(String[] args) {
        Staff staffMember = new Staff(201, "Alice", "789 Pine St", 987654321, 50000);
        Student studentMember = new Student(301, "Bob", "456 Oak St", 112233445, "Computer Science");

        System.out.println("Staff Details:");
        staffMember.printInfo(); //  Works fine for staff

        System.out.println("\nStudent Details:");
        studentMember.printInfo(); // Works fine for student (no salary forced)
    }
}