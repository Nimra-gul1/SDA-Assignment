package LMS;

import static LMS.Library.librarian;
import static LMS.Library.persons;

// follows SRP now: Separated office assignment responsibility into its own class
// Before: Office number assignment was handled inside BaseLibrarian, violating SRP.
// Now: The responsibility is moved to OfficeAllocator.
class OfficeAllocator {
    private static int currentOfficeNumber = 0;

    public static int assignOffice(int providedOfficeNumber) {
        if (providedOfficeNumber == -1) {
            return currentOfficeNumber++;
        }
        return providedOfficeNumber;
    }
}

// follows SRP & OCP : Created a base class for all librarian roles
// Before: The Librarian class directly handled different types of librarians, violating OCP.
//Now: We have BaseLibrarian, which allows extension without modification.
abstract class BaseLibrarian extends Staff {
    protected int officeNo;

    public BaseLibrarian(int id, String n, String a, int p, double s, int of) {
        super(id, n, a, p, s);
        this.officeNo = OfficeAllocator.assignOffice(of);
    }

    // follows OCP : Now, any new librarian type can extend BaseLibrarian without modifying existing code.
    public abstract void manageLibrary();

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Office Number: " + officeNo);
    }
}

// follows SRP FIX: LibrarianManager now handles librarian-related operations separately
// Before: Librarian class managed adding librarians, violating SRP.
// Now: This logic is extracted into a dedicated LibrarianManager class.
class LibrarianManager {
    public static boolean addLibrarian(BaseLibrarian lib) { 
        //follows OCP now: Now works for any librarian type, not just a specific one.
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

// it follows OCP & LSP now: Head Librarian now correctly follows OCP & LSP
// Before: Any new librarian type had to modify an existing class, breaking OCP.
// Now: The Librarian class extends BaseLibrarian and implements its own behavior.
public class Librarian extends BaseLibrarian {
    public Librarian(int id, String n, String a, int p, double s, int of) {
        super(id, n, a, p, s, of);
    }

    @Override
    public void manageLibrary() {
        System.out.println("Managing Library as Head Librarian.");
    }
}

// it follows LSP now: AssistantLibrarian now implements an interface instead of extending BaseLibrarian
// Before: AssistantLibrarian extended BaseLibrarian but did not need all its properties ,violating LSP.
// Now: AssistantLibrarian has its own interface, following proper substitution rules.
interface LibraryAssistantRole {
    void assistInLibrary();
}

class AssistantLibrarian implements LibraryAssistantRole {
    private String name;

    public AssistantLibrarian(String name) {
        this.name = name;
    }

    @Override
    public void assistInLibrary() {
        System.out.println(name + " is assisting in library tasks.");
    }
}
