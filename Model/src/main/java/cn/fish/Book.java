package cn.fish;

public class Book {
    private String bookName;
    private String bookID;
    private String bookSummary;

    public Book() {}

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookID() {
        return this.bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookSummary() {
        return this.bookSummary;
    }

    public void setBookSummary(String bookSummary) {
        this.bookSummary = bookSummary;
    }
}