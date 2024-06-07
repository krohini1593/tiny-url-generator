package org.inf.java.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.text.html.Option;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class ShortUrlMongoStore implements ShortUrlDB{

    private final MongoClient mongoClient;
    private final MongoDatabase shortUrlDB;

    private final MongoCollection<Document> shortUrlToLongUrlMap;
    public ShortUrlMongoStore(MongoClient mongoClient, String dbName, String collection) {
        this.mongoClient = mongoClient;
        this.shortUrlDB = mongoClient.getDatabase(dbName);
        this.shortUrlToLongUrlMap = shortUrlDB.getCollection(collection);
    }
    @Override
    public void saveShortUrl(String longUrl, String shortUrl) {
        shortUrlToLongUrlMap.insertOne(new Document().append("short_code", shortUrl).append("long_url", longUrl));
    }

    @Override
    public String getLongUrlFor(String shortUrl) {
        return "";
    }

    @Override
    public Optional<String> getShortUrlIfExists(String longUrl) {
        Document doc = shortUrlToLongUrlMap.find(eq("long_url", longUrl)).first();
        if(doc == null) {
            return Optional.empty();
        }
        return Optional.of(String.valueOf(doc.get("short_code")));
    }
}
