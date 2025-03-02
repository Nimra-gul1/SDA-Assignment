package LMS;

public class HoldRequestUI {
    /*
       it follows srp now: this class handles ui-related logic.
       Moved `printHoldRequests` from `HoldRequestOperations`.
       Now the operations class is more cleaner and more reusable.
    */

    public static void printHoldRequests(HoldRequestOperations holdRequestOperations) {
        if (!holdRequestOperations.getHoldRequests().isEmpty()) {
            System.out.println("\nHold Requests:");
            System.out.println("------------------------------------------------------------");
            System.out.println("No.\t\tBook Title\t\tBorrower's Name\t\tRequest Date");
            System.out.println("------------------------------------------------------------");

            for (int i = 0; i < holdRequestOperations.getHoldRequests().size(); i++) {
                System.out.print(i + "-" + "\t\t");
                holdRequestOperations.getHoldRequests().get(i).print();
            }
        } else {
            System.out.println("\nNo Hold Requests.");
        }
    }
}
