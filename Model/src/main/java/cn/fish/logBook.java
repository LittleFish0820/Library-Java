package cn.fish;

public class logBook {
    private String actDate = "";
    private String bookID = "";
    private String bookName = "";
    private String stuID = "";
    private String stuName = "";
    private String actMode = "";

    public logBook() {}

    public String getActDate() {
        return actDate;
    }

    public void setActDate(String actDate) {
        this.actDate = actDate;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getActMode() {
        return actMode;
    }

    public void setActMode(String actMode) {
        this.actMode = actMode;
    }
}
