package cn.fish;

import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;

public class UsersAct {
    // 管理员注册
    public void adminRegist(UserAdmin newUser) {
        final Document document = new Document();
        document.put("userName", newUser.getUserName());
        document.put("userPWD", newUser.getUserPWD());
        document.put("userORG", newUser.getUserORG());
        document.put("isAdmin", newUser.isAdmin());
        DBAct dbAct = new DBAct();
        dbAct.mongoConnect();
        dbAct.dbInsert("adminInfo", document);
        dbAct.mongoClose();
    }
    // 用户登录
    public boolean adminLogin(UserAdmin loginUser) {
        Document query = new Document();
        query.put("userName", loginUser.getUserName());
        query.put("userPWD", loginUser.getUserPWD());
        query.put("userORG", loginUser.getUserORG());
        query.put("isAdmin", loginUser.isAdmin());
        DBAct dbAct = new DBAct();
        dbAct.mongoConnect();
        final ArrayList<Document> findResultsList = new ArrayList<Document>();
        FindIterable<Document> iterable = dbAct.dbFind("adminInfo", query);
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                findResultsList.add(document);
            }
        });
        dbAct.mongoClose();
        if (findResultsList.size() == 1) {
            return true;
        }
        return false;
    }

    // 用户姓名查询
    public String findUserNameByID(String stuID) {
        Document query = new Document();
        query.put("stuID", stuID);
        DBAct dbAct = new DBAct();
        dbAct.mongoConnect();
        final ArrayList<Document> findResultsList = new ArrayList<Document>();
        FindIterable<Document> iterable = dbAct.dbFind("stuInfo", query);
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                findResultsList.add(document);
            }
        });
        dbAct.mongoClose();
        if(findResultsList.size() == 1) {
            return findResultsList.get(0).getString("stuName");
        }
        return "查无此用户，请检查用户编号!";
    }

    // 学生添加
    public void stuAdd(UserStudent newStuUser) {
        final Document document = new Document();
        document.put("stuID", newStuUser.getStuID());
        document.put("stuName", newStuUser.getStuName());
        document.put("stuClass", newStuUser.getStuClass());
        DBAct dbact = new DBAct();
        dbact.mongoConnect();
        dbact.dbInsert("stuInfo", document);
        dbact.mongoClose();
    }

    // 学生删除
    public void stuDelete(UserStudent oldStuUser) {
        final Document document = new Document();
        document.put("stuID", oldStuUser.getStuID());
        DBAct dbAct = new DBAct();
        dbAct.mongoConnect();
        dbAct.dbDelete("stuInfo", document);
        dbAct.mongoClose();
    }
}
