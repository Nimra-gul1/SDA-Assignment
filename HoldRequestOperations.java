package LMS;

import java.util.ArrayList;
import java.util.List;

public class HoldRequestOperations {
    private List<HoldRequest> holdRequests = new ArrayList<>();

    /*
       it follows SRP now: Removed print statements.
       This class now only handles hold request operations.
       Printing logic has been moved to `HoldRequestUI`.
    */

    public void makeHoldRequest(Borrower borrower, Book book) {
        for (HoldRequest hr : holdRequests) {
            if (hr.getBorrower() == borrower) {
                
                // UI-related message removed
                return;
            }
        }
        placeBookOnHold(borrower, book);
    }

    public void placeBookOnHold(Borrower borrower, Book book) {
        HoldRequest hr = new HoldRequest(borrower, book, new java.util.Date());
        holdRequests.add(hr);
        borrower.addHoldRequest(hr);
    }

    public void serviceHoldRequest(HoldRequest hr) {
        holdRequests.remove(hr);
        hr.getBorrower().removeHoldRequest(hr);
    }

    public List<HoldRequest> getHoldRequests() {
        return holdRequests;
    }
}
