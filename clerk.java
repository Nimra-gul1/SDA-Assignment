// StaffMember class (base class)

public abstract class StaffMember {

    int id;
    String name;
    String address;
    int phone;
    double salary;

    // Constructor to initialize common fields for all staff members
    public StaffMember(int id, String name, String address, int phone, double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
    }

    // Abstract method for printing staff info (can be overridden by specific staff classes)
    public abstract void printInfo();
}

// Clerk class (extends StaffMember)
public class Clerk extends StaffMember {

    int deskNo;
    public static int currentdeskNumber = 0;

    public Clerk(int id, String name, String address, int phone, double salary, int deskNo) {
        super(id, name, address, phone, salary);

        if (deskNo == -1) {
            this.deskNo = currentdeskNumber;
        } else {
            this.deskNo = deskNo;
        }

        currentdeskNumber++;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Desk Number: " + deskNo);
    }
}

// SeniorClerk class (extends Clerk)
public class SeniorClerk extends Clerk {

    public SeniorClerk(int id, String name, String address, int phone, double salary, int deskNo) {
        super(id, name, address, phone, salary, deskNo);
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Role: Senior Clerk");
    }
}

// JuniorClerk class (extends Clerk)
public class JuniorClerk extends Clerk {

    public JuniorClerk(int id, String name, String address, int phone, double salary, int deskNo) {
        super(id, name, address, phone, salary, deskNo);
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Role: Junior Clerk");
    }
}

// Main class to test the implementation
public class Main {

    public static void main(String[] args) {
        // Creating instances of different clerks
        Clerk clerk1 = new Clerk(101, "John Doe", "123 Main St", 123456789, 30000, -1);
        SeniorClerk seniorClerk = new SeniorClerk(102, "Jane Smith", "456 Oak St", 987654321, 40000, -1);
        JuniorClerk juniorClerk = new JuniorClerk(103, "Emily Johnson", "789 Pine St", 112233445, 25000, -1);

        // Printing info for each clerk
        clerk1.printInfo();
        System.out.println();

        seniorClerk.printInfo();
        System.out.println();

        juniorClerk.printInfo();
    }
}
