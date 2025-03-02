package LMS;

import java.util.Date;

public class HoldRequest {
    private Borrower borrower;
    private Book book;
    private Date requestDate;

    public HoldRequest(Borrower bor, Book b, Date reqDate) {
        borrower = bor;
        book = b;
        requestDate = reqDate;
    }

    public Borrower getBorrower() { return borrower; }
    public Book getBook() { return book; }
    public Date getRequestDate() { return requestDate; }
}
