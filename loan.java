package LMS;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Scanner;
//DIP violation:
//The Loan class directly depends on Library.getInstance().per_day_fine for fine calculations.
//This creates tight coupling between Loan and Library.
//If Library changes how fines are stored, Loan also needs modification.

//DIP fixed:
//Instead of using Library.getInstance() for fine calculation, Loan now depends on an interface (FineStrategy).
//Different fine rules can be used by passing a different FineStrategy to Loan.


// Interface for Fine Calculation (Fixing DIP)
interface FineStrategy {
    double computeFine(Date issuedDate, Date returnedDate);
}

// Concrete Fine Calculation Strategy
class StandardFineStrategy implements FineStrategy {
    private final int bookReturnDeadline;
    private final double perDayFine;

    public StandardFineStrategy(int bookReturnDeadline, double perDayFine) {
        this.bookReturnDeadline = bookReturnDeadline;
        this.perDayFine = perDayFine;
    }

    @Override
    public double computeFine(Date issuedDate, Date returnedDate) {
        long daysLate = ChronoUnit.DAYS.between(issuedDate.toInstant(), returnedDate.toInstant());
        daysLate = daysLate - bookReturnDeadline;
        return (daysLate > 0) ? daysLate * perDayFine : 0;
    }
}

//Loan Class (DIP Fixed)
public class Loan {
    private Borrower borrower;
    private Book book;
    private Staff issuer;
    private Date issuedDate;
    private Date dateReturned;
    private Staff receiver;
    private boolean finePaid;
    private FineStrategy fineStrategy; // DIP applied: Loan depends on abstraction

    // Constructor now takes a FineStrategy as a parameter (Dependency Injection)
    public Loan(Borrower bor, Book b, Staff i, Staff r, Date iDate, Date rDate, boolean fPaid, FineStrategy fineStrategy) {
        this.borrower = bor;
        this.book = b;
        this.issuer = i;
        this.receiver = r;
        this.issuedDate = iDate;
        this.dateReturned = rDate;
        this.finePaid = fPaid;
        this.fineStrategy = fineStrategy; // DIP: Using abstraction instead of direct Library call
    }

    // Fine Calculation using FineStrategy
    public double computeFine() {
        if (!finePaid) {
            return fineStrategy.computeFine(issuedDate, new Date());
        }
        return 0;
    }

    public void payFine() {
        double totalFine = computeFine();
        if (totalFine > 0) {
            System.out.println("\nTotal Fine generated: Rs " + totalFine);
            System.out.println("Do you want to pay? (y/n)");
            
            Scanner input = new Scanner(System.in); 
            String choice = input.next();
            
            finePaid = choice.equalsIgnoreCase("y");
        } else {
            System.out.println("\nNo fine is generated.");
            finePaid = true;
        }
    }
}

