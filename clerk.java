// Base class for all staff members
public abstract class clerk {

    int id;
    String name;
    String address;
    int phone;
    double salary;

    // Constructor to initialize common fields
    public clerk(int id, String name, String address, int phone, double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
    }

    // Abstract method to enforce info printing in subclasses
    public abstract void printInfo();
}

// Clerk class (inherits from StaffMember)
public class Clerk extends clerk {

    int deskNo;  // Desk number is now assigned externally

    /**
     * LSP Fix: 
     * - Removed static `currentdeskNumber`, making desk assignment flexible.
     * - Now, desk numbers are assigned explicitly by the caller instead of enforced.
     */
    public Clerk(int id, String name, String address, int phone, double salary, int deskNo) {
        super(id, name, address, phone, salary);
        this.deskNo = deskNo;  // Desk number is explicitly passed
    }

    @Override
    public void printInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("Salary: " + salary);
        System.out.println("Desk Number: " + deskNo);
    }
}

// SeniorClerk class (inherits from Clerk)
public class SeniorClerk extends Clerk {

    public SeniorClerk(int id, String name, String address, int phone, double salary, int deskNo) {
        super(id, name, address, phone, salary, deskNo); // Explicit desk number
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Role: Senior Clerk");
    }
}

// JuniorClerk class (inherits from Clerk)
public class JuniorClerk extends Clerk {

    public JuniorClerk(int id, String name, String address, int phone, double salary, int deskNo) {
        super(id, name, address, phone, salary, deskNo); // Explicit desk number
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Role: Junior Clerk");
    }
}

// Main class to test implementation
public class Main {

    public static void main(String[] args) {
        // Desk numbers are now assigned explicitly by the caller
        Clerk clerk1 = new Clerk(101, "John Doe", "123 Main St", 123456789, 30000, 1);
        SeniorClerk seniorClerk = new SeniorClerk(102, "Jane Smith", "456 Oak St", 987654321, 40000, 2);
        JuniorClerk juniorClerk = new JuniorClerk(103, "Emily Johnson", "789 Pine St", 112233445, 25000, 3);

        // Printing info for each clerk
        clerk1.printInfo();
        System.out.println();

        seniorClerk.printInfo();
        System.out.println();

        juniorClerk.printInfo();
    }
}
