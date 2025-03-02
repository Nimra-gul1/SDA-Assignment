package LMS;

import static LMS.Library.librarian;
import static LMS.Library.persons;

//it follows SRP now: Separated office assignment responsibility
class OfficeAllocator {
    private static int currentOfficeNumber = 0;

    public static int assignOffice(int providedOfficeNumber) {
        if (providedOfficeNumber == -1) {
            return currentOfficeNumber++;
        }
        return providedOfficeNumber;
    }
}

// it follows SRP & OCP now: Base class for all librarian roles
abstract class BaseLibrarian extends Staff {
    protected int officeNo;

    public BaseLibrarian(int id, String n, String a, int p, double s, int of) {
        super(id, n, a, p, s);
        this.officeNo = OfficeAllocator.assignOffice(of);
    }

    //it follows OCP : This method ensures any new librarian type follows the same structure
    public abstract void manageLibrary();

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Office Number: " + officeNo);
    }
}

//it follows SRP now: LibrarianManager now handles librarian-related operations
class LibrarianManager {
    public static boolean addLibrarian(BaseLibrarian lib) { //it follows OCP now: Works for any librarian type
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

//it follows OCP and LSP: Head Librarian now follows the proper structure
public class Librarian extends BaseLibrarian {
    public Librarian(int id, String n, String a, int p, double s, int of) {
        super(id, n, a, p, s, of);
    }

    @Override
    public void manageLibrary() {
        System.out.println("Managing Library as Head Librarian.");
    }
}

//it follows LSP now: AssistantLibrarian does not extend BaseLibrarian (only assists)
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
