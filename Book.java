package LMS;

public class Book {
    private int bookID;
    private String title;
    private String subject;
    private String author;
    private boolean isIssued;

    static int currentIdNumber = 0;

    public Book(int id, String t, String s, String a, boolean issued) {
        currentIdNumber++;
        bookID = (id == -1) ? currentIdNumber : id;
        title = t;
        subject = s;
        author = a;
        isIssued = issued;
    }

    /* 
       SRP is followed here: removed ui-related code (printing & updating book details).
        Previously, the Book class handled both data management and user interaction.
        Now only book-related data is stored and managed here.
    */

    /*------------ Getter and Setter Functions ---------*/
    public String getTitle() { return title; }
    public String getSubject() { return subject; }
    public String getAuthor() { return author; }
    public boolean getIssuedStatus() { return isIssued; }
    public void setIssuedStatus(boolean issued) { this.isIssued = issued; }
    public int getID() { return bookID; }
    public static void setIDCount(int n) { currentIdNumber = n; }

    /*  No more print statements or input handling here*/
}
