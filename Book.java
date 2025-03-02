package LMS;

import java.io.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Book {
    private int bookID;
    private String title;
    private String subject;
    private String author;
    private boolean isIssued;
    private HoldRequestOperations holdRequestsOperations = new HoldRequestOperations(); // No Wrapper Needed

    static int currentIdNumber = 0;

    public Book(int id, String t, String s, String a, boolean issued) {
        currentIdNumber++;
        bookID = (id == -1) ? currentIdNumber : id;
        title = t;
        subject = s;
        author = a;
        isIssued = issued;
    }

    // Directly using HoldRequestOperations
    public void printHoldRequests() {
        ArrayList<HoldRequest> holdRequests = holdRequestsOperations.getHoldRequests();

        if (!holdRequests.isEmpty()) {
            System.out.println("\nHold Requests are:");
            System.out.println("------------------------------------------------------------");
            System.out.println("No.\t\tBook Title\t\tBorrower's Name\t\tRequest Date");
            System.out.println("------------------------------------------------------------");

            for (int i = 0; i < holdRequests.size(); i++) {
                System.out.print(i + "-" + "\t\t");
                holdRequests.get(i).print();
            }
        } else {
            System.out.println("\nNo Hold Requests.");
        }
    }

    public void printInfo() {
        System.out.println(title + "\t\t\t" + author + "\t\t\t" + subject);
    }

    public void changeBookInfo() throws IOException {
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\nUpdate Author? (y/n)");
        if (scanner.next().equals("y")) {
            System.out.println("\nEnter new Author: ");
            author = reader.readLine();
        }

        System.out.println("\nUpdate Subject? (y/n)");
        if (scanner.next().equals("y")) {
            System.out.println("\nEnter new Subject: ");
            subject = reader.readLine();
        }

        System.out.println("\nUpdate Title? (y/n)");
        if (scanner.next().equals("y")) {
            System.out.println("\nEnter new Title: ");
            title = reader.readLine();
        }

        System.out.println("\nBook is successfully updated.");
    }

    public String getTitle() { return title; }
    public String getSubject() { return subject; }
    public String getAuthor() { return author; }
    public boolean getIssuedStatus() { return isIssued; }
    public void setIssuedStatus(boolean s) { isIssued = s; }
    public int getID() { return bookID; }
    public static void setIDCount(int n) { currentIdNumber = n; }

    // Hold Request Management
    public void makeHoldRequest(Borrower borrower) {
        ArrayList<HoldRequest> holdRequests = holdRequestsOperations.getHoldRequests();

        for (HoldRequest hr : holdRequests) {
            if (hr.getBorrower() == borrower) {
                System.out.println("\nYou already have one hold request for this book.\n");
                return;
            }
        }
        placeBookOnHold(borrower);
    }

    public void placeBookOnHold(Borrower borrower) {
        HoldRequest hr = new HoldRequest(borrower, this, new Date());
        holdRequestsOperations.addHoldRequest(hr);
        borrower.addHoldRequest(hr);
        System.out.println("\nThe book " + title + " has been successfully placed on hold by " + borrower.getName() + ".\n");
    }

    public void serviceHoldRequest(HoldRequest hr) {
        holdRequestsOperations.removeHoldRequest();
        hr.getBorrower().removeHoldRequest(hr);
    }
}
