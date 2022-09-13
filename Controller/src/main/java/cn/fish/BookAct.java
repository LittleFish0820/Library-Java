package cn.fish;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.ArrayList;

public class BookAct {
    public ArrayList<Document> getNotBackBook() {
        ArrayList<Document> test = new ArrayList<Document>();
        return test;
    }

    public void bookDelete(Book oldBook) {
        final Document document = new Document();
        document.put("bookID", oldBook.getBookID());
        DBAct dbAct = new DBAct();
        dbAct.mongoConnect();
        dbAct.dbDelete("bookInfo", document);
        dbAct.mongoClose();
    }

    public void bookAdd(Book newBook) {
        final Document document = new Document();
        document.put("bookID", newBook.getBookID());
        document.put("bookName", newBook.getBookName());
        document.put("bookSummary", newBook.getBookSummary());
        DBAct dbact = new DBAct();
        dbact.mongoConnect();
        dbact.dbInsert("bookInfo", document);
        dbact.mongoClose();
    }

    public void logRecord(LogBook outBook) {
        final Document document = new Document();
        document.put("actDate", outBook.getActDate());
        document.put("actMode", outBook.getActMode());
        document.put("bookID", outBook.getBookID());
        document.put("bookName", outBook.getBookName());
        document.put("stuID", outBook.getStuID());
        document.put("stuName", outBook.getStuName());
        DBAct dbAct = new DBAct();
        dbAct.mongoConnect();
        dbAct.dbInsert("bookLog", document);
        if (outBook.getActMode() == "借出") {
            document.remove("actMode");
            dbAct.dbInsert("outSideBook", document);
        }
        dbAct.mongoClose();
    }


    public String findBookNameByID(String bookID) {
        Document query = new Document();
        query.put("bookID", bookID);
        DBAct dbAct = new DBAct();
        dbAct.mongoConnect();
        final ArrayList<Document> findResultsList = new ArrayList<Document>();
        FindIterable<Document> iterable = dbAct.dbFind("bookInfo", query);
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                findResultsList.add(document);
            }
        });
        dbAct.mongoClose();
        if(findResultsList.size() == 1) {
            return findResultsList.get(0).getString("bookName");
        }
        return "查无此书籍，请输入正确的图书编号!";
    }
}
