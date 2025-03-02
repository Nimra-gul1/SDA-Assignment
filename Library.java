
import java.sql.*;
import java.util.*;

// Base Library class (formerly Person)
public abstract class Library {

    private int id;
    private String name;
    private String password;
    private String address;
    private int phoneNumber;

    public Library(int id, String name, String password, String address, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

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
        return phoneNumber;
    }

    // Abstract method to be implemented by subclasses
    public abstract void insertStaffIntoDatabase(Connection con) throws SQLException;
}

// Refactored: DatabaseService handles DB insertion logic separately (SRP)
class DatabaseService {

    private StaffInsertionStrategy insertionStrategy;

    public DatabaseService(StaffInsertionStrategy insertionStrategy) {
        this.insertionStrategy = insertionStrategy;
    }

    public void insertStaffIntoDatabase(Connection con, Staff staff) throws SQLException {
        insertionStrategy.insertStaffIntoDatabase(con, staff);
    }
}

// Strategy interface for insertion logic
interface StaffInsertionStrategy {

    void insertStaffIntoDatabase(Connection con, Staff staff) throws SQLException;
}

// Concrete strategies for each staff type (Clerk, Librarian, Manager)
class ClerkInsertionStrategy implements StaffInsertionStrategy {

    @Override
    public void insertStaffIntoDatabase(Connection con, Staff staff) throws SQLException {
        Clerk clerk = (Clerk) staff;
        String template = "INSERT INTO LIBRARY.STAFF (S_ID, TYPE, SALARY) VALUES (?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(template);
        stmt.setInt(1, staff.getId());
        stmt.setString(2, "Clerk");
        stmt.setDouble(3, staff.getSalary());
        stmt.executeUpdate();

        template = "INSERT INTO LIBRARY.CLERK (C_ID, DESK_NO) VALUES (?, ?)";
        stmt = con.prepareStatement(template);
        stmt.setInt(1, staff.getId());
        stmt.setInt(2, clerk.getDeskNo());
        stmt.executeUpdate();
    }
}

class LibrarianInsertionStrategy implements StaffInsertionStrategy {

    @Override
    public void insertStaffIntoDatabase(Connection con, Staff staff) throws SQLException {
        Librarian librarian = (Librarian) staff;
        String template = "INSERT INTO LIBRARY.STAFF (S_ID, TYPE, SALARY) VALUES (?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(template);
        stmt.setInt(1, staff.getId());
        stmt.setString(2, "Librarian");
        stmt.setDouble(3, staff.getSalary());
        stmt.executeUpdate();

        template = "INSERT INTO LIBRARY.LIBRARIAN (L_ID, OFFICE_NO) VALUES (?, ?)";
        stmt = con.prepareStatement(template);
        stmt.setInt(1, staff.getId());
        stmt.setInt(2, librarian.getOfficeNo());
        stmt.executeUpdate();
    }
}

class ManagerInsertionStrategy implements StaffInsertionStrategy {

    @Override
    public void insertStaffIntoDatabase(Connection con, Staff staff) throws SQLException {
        Manager manager = (Manager) staff;
        String template = "INSERT INTO LIBRARY.STAFF (S_ID, TYPE, SALARY) VALUES (?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(template);
        stmt.setInt(1, staff.getId());
        stmt.setString(2, "Manager");
        stmt.setDouble(3, staff.getSalary());
        stmt.executeUpdate();

        template = "INSERT INTO LIBRARY.MANAGER (M_ID, DEPARTMENT) VALUES (?, ?)";
        stmt = con.prepareStatement(template);
        stmt.setInt(1, staff.getId());
        stmt.setString(2, manager.getDepartment());
        stmt.executeUpdate();
    }
}

// Abstract Staff class with proper dependencies (DIP)
abstract class Staff extends Library {

    private double salary;
    private StaffInsertionStrategy insertionStrategy;

    public Staff(int id, String name, String password, String address, int phoneNumber, double salary, StaffInsertionStrategy insertionStrategy) {
        super(id, name, password, address, phoneNumber);
        this.salary = salary;
        this.insertionStrategy = insertionStrategy;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public void insertStaffIntoDatabase(Connection con) throws SQLException {
        insertionStrategy.insertStaffIntoDatabase(con, this);
    }
}

// Clerk, Librarian, Manager classes (subclasses of Staff)
class Clerk extends Staff {

    private int deskNo;

    public Clerk(int id, String name, String password, String address, int phoneNumber, double salary, int deskNo, StaffInsertionStrategy insertionStrategy) {
        super(id, name, password, address, phoneNumber, salary, insertionStrategy);
        this.deskNo = deskNo;
    }

    public int getDeskNo() {
        return deskNo;
    }
}

class Librarian extends Staff {

    private int officeNo;

    public Librarian(int id, String name, String password, String address, int phoneNumber, double salary, int officeNo, StaffInsertionStrategy insertionStrategy) {
        super(id, name, password, address, phoneNumber, salary, insertionStrategy);
        this.officeNo = officeNo;
    }

    public int getOfficeNo() {
        return officeNo;
    }
}

class Manager extends Staff {

    private String department;

    public Manager(int id, String name, String password, String address, int phoneNumber, double salary, String department, StaffInsertionStrategy insertionStrategy) {
        super(id, name, password, address, phoneNumber, salary, insertionStrategy);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}

// Main application class
public class LibraryApplication {

    public static void main(String[] args) {
        // Create strategies
        StaffInsertionStrategy clerkStrategy = new ClerkInsertionStrategy();
        StaffInsertionStrategy librarianStrategy = new LibrarianInsertionStrategy();
        StaffInsertionStrategy managerStrategy = new ManagerInsertionStrategy();

        // Create staff objects
        Staff clerk = new Clerk(1, "John", "pass123", "Address", 12345, 25000, 101, clerkStrategy);
        Staff librarian = new Librarian(2, "Alice", "pass123", "Address", 12345, 30000, 102, librarianStrategy);
        Staff manager = new Manager(3, "Bob", "pass123", "Address", 12345, 35000, "Admin", managerStrategy);

        // Use DatabaseService to insert into DB
        // DatabaseService dbService = new DatabaseService(clerkStrategy);
        // dbService.insertStaffIntoDatabase(connection, clerk);
    }
}
