package cn.fish;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DBAct {
    // 数据库服务器
    private MongoClient mongoClient = null;

    // 数据库
    private MongoDatabase db = null;

    // 数据库服务器连接初始化
    public void mongoConnect() {
        String IP = "192.168.176.128";
        int port = 27017;
        mongoClient = new MongoClient(IP, port);
        db = mongoClient.getDatabase("library");
    }

    public void mongoConnect(String IP, int port) {
        mongoClient = new MongoClient(IP, port);
        db = mongoClient.getDatabase("library");
    }

    // 数据库服务器连接断开
    public void mongoClose() {
        mongoClient.close();
        db = null;
    }

    // 插入数据
    public void dbInsert(String collectionName, Document document) {
        MongoCollection<Document> collection = db.getCollection(collectionName);
        collection.insertOne(document);
    }

    // 查找数据
    public FindIterable<Document> dbFind(String collectionName, Document query) {
        MongoCollection<Document> collection = db.getCollection(collectionName);
        return collection.find(query);
    }

    // 查找全部数据
    public FindIterable<Document> dbFindAll(String collectionName) {
        MongoCollection<Document> collection = db.getCollection(collectionName);
        return collection.find();
    }

    // 删除数据
    public void dbDelete(String collectionName, Document document) {
        MongoCollection<Document> collection = db.getCollection(collectionName);
        collection.deleteOne(document);
    }

}