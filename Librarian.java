// The violation of the OCP (Open/Closed Principle) in the Librarian class occurs because if we need to add new types of librarians (e.g., AssistantLibrarian) in the future, we have to modify the Librarian class. To fix this, we will use a base abstract class or an interface.
package LMS;

import static LMS.Library.librarian;
import static LMS.Library.persons;

// Violation:
// The Librarian class directly inherits from Staff, but if we need to create new types of librarians (e.g., AssistantLibrarian), we have to modify the Librarian class, which violates the OCP (Open/Closed Principle).
// Fix:
// Create an ILibrarian interface or an AbstractLibrarian base class and use subclasses to handle specific functionalities.
public abstract class BaseLibrarian extends Staff {

    int officeNo;
    public static int currentOfficeNumber = 0;

    public BaseLibrarian(int id, String n, String a, int p, double s, int of) {
        super(id, n, a, p, s);
        if (of == -1) {
            officeNo = currentOfficeNumber;
        } else {
            officeNo = of;
        }
        currentOfficeNumber++;
    }

    // Common librarian functionalities
    public abstract void manageLibrary();

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Office Number: " + officeNo);
    }
}

// Concrete Librarian Class
public class Librarian extends BaseLibrarian {

    public Librarian(int id, String n, String a, int p, double s, int of) {
        super(id, n, a, p, s, of);
    }

    @Override
    public void manageLibrary() {
        System.out.println("Managing Library as Head Librarian.");
    }

    public static boolean addLibrarian(Librarian lib) {
        if (librarian == null) {
            librarian = lib;
            persons.add(librarian);
            return true;
        } else {
            System.out.println("\nSorry, the library already has one librarian.");
            return false;
        }
    }
}

// New type of librarian
public class AssistantLibrarian extends BaseLibrarian {

    public AssistantLibrarian(int id, String n, String a, int p, double s, int of) {
        super(id, n, a, p, s, of);
    }

    @Override
    public void manageLibrary() {
        System.out.println("Assisting in Library Management.");
    }
}
