package LMS;

public abstract class Person {
    protected int id; // Unique ID for each person
    protected String password; // Authentication password
    protected String name; // Name of the person
    protected String address; // Address of the person
    protected int phoneNo; // Phone number

    /**
     * Constructor allowing flexibility in setting ID and password.
     * - Fixes LSP: Removed forced ID incrementation, allowing subclasses to handle IDs as needed.
     * - Fixes LSP: Password is no longer tied to ID, giving freedom to use different authentication methods.
     */
    public Person(int id, String password, String name, String address, int phoneNo) {
        this.id = id; 
        this.password = password; 
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    /**
     * Prints basic person details.
     * - This method remains the same, but now subclasses can extend it safely without being forced into a specific ID system.
     */
    public void printInfo() {
        System.out.println("-----------------------------------------");
        System.out.println("\nThe details are: \n");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Phone No: " + phoneNo + "\n");
    }

    /* ---------- Setter Methods ---------- */

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    /* ---------- Getter Methods ---------- */

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNumber() {
        return phoneNo;
    }

    public int getID() {
        return id;
    }

