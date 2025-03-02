package LMS;

public class HoldRequestUI {
    /*
       it follows SRP now: this class handles all UI-related logic and moved `printHoldRequests` from `HoldRequestOperations` and removed `print()` method from `HoldRequest`.
    */

    public static void printHoldRequests(HoldRequestOperations holdRequestOperations) {
        if (!holdRequestOperations.getHoldRequests().isEmpty()) {
            System.out.println("\nHold Requests:");
            System.out.println("------------------------------------------------------------");
            System.out.println("No.\t\tBook Title\t\tBorrower's Name\t\tRequest Date");
            System.out.println("------------------------------------------------------------");

            for (int i = 0; i < holdRequestOperations.getHoldRequests().size(); i++) {
                System.out.print(i + "-" + "\t\t");
                printHoldRequest(holdRequestOperations.getHoldRequests().get(i));
            }
        } else {
            System.out.println("\nNo Hold Requests.");
        }
    }

    public static void printHoldRequest(HoldRequest hr) {
        System.out.print(hr.getBook().getTitle() + "\t\t\t\t" + 
                         hr.getBorrower().getName() + "\t\t\t\t" + 
                         hr.getRequestDate() + "\n");
    }
}
