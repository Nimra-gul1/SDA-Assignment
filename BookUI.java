package LMS;

import java.io.*;

public class BookUI {
    /*
       It follows SRP now:created a separate BookUI class for user interactions.
       Previously, the Book class handled **both** book data and UI-related operations.
       Now UI logic (printing and updating book details) is placed in this dedicated class.
    */

    //ui handling for displaying book info
    public static void printBookInfo(Book book) {
        System.out.println(book.getTitle() + "\t\t\t" + book.getAuthor() + "\t\t\t" + book.getSubject());
    }

    //ui handling for updating book info
    public static void updateBookInfo(Book book) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("\nEnter new Author (leave blank to keep current): ");
        String newAuthor = reader.readLine();
        if (!newAuthor.isEmpty()) book.setAuthor(newAuthor);

        System.out.print("Enter new Subject (leave blank to keep current): ");
        String newSubject = reader.readLine();
        if (!newSubject.isEmpty()) book.setSubject(newSubject);

        System.out.print("Enter new Title (leave blank to keep current): ");
        String newTitle = reader.readLine();
        if (!newTitle.isEmpty()) book.setTitle(newTitle);

        System.out.println("\nBook information updated successfully.");
    }
}
