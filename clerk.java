// Base class for all staff members
public abstract class Clerk {

    int id;
    String name;
    String address;
    int phone;
    double salary;
    ClerkType type; //  OCP Fix: Use a type instead of subclasses

    // Constructor to initialize common fields
    public Clerk(int id, String name, String address, int phone, double salary, ClerkType type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
        this.type = type;
    }

    // Method to enforce info printing in subclasses
    public void printInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("Salary: " + salary);
        System.out.println("Role: " + type);
    }
}

// OCP Fix: Instead of separate classes, use an enum for roles
enum ClerkType {
    REGULAR, SENIOR, JUNIOR;
}

// Main class to test implementation
public class Main {
    public static void main(String[] args) {
        //  LSP Fix: No need for separate subclasses
        Clerk clerk1 = new Clerk(101, "John Doe", "123 Main St", 123456789, 30000, ClerkType.REGULAR);
        Clerk seniorClerk = new Clerk(102, "Jane Smith", "456 Oak St", 987654321, 40000, ClerkType.SENIOR);
        Clerk juniorClerk = new Clerk(103, "Emily Johnson", "789 Pine St", 112233445, 25000, ClerkType.JUNIOR);

        // Printing info for each clerk
        clerk1.printInfo();
        System.out.println();

        seniorClerk.printInfo();
        System.out.println();

        juniorClerk.printInfo();
    }
}
