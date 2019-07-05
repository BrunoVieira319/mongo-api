package com.fundatec.mongoproject.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Component
public class UserDao {

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public UserDao() {
        this.client = new MongoClient("localhost", 27017);
        this.database = client.getDatabase("users");
        this.collection = database.getCollection("users");
    }

    public void save(Document document) {
        collection.insertOne(document);
    }

    public List<Document> findAll() {
        return documentsToList(collection.find());
    }

    public List<Document> filter(Document document) {
        return documentsToList(collection.find(document));
    }

    public long update(String username, Document update) {
        UpdateResult result = collection.updateMany(eq("username", username), new Document("$set", update));
        return result.getMatchedCount();
    }

    public long delete(Document document) {
        DeleteResult result = this.collection.deleteMany(document);
        return result.getDeletedCount();
    }

    public List<Document> documentsToList(Iterable<Document> documents) {
        List<Document> documentsInList = new ArrayList<>();
        documents.forEach(documentsInList::add);
        return documentsInList;
    }

}
