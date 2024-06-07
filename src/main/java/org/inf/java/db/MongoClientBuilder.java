package org.inf.java.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoClientBuilder {

    private MongoClient mongoClient;
    private MongoClientBuilder() {
    }
    public static MongoClientBuilder newClientWithUri(String uri) {
        var builder = new MongoClientBuilder();
        builder.newClient(uri);
        return builder;
    }

    private void newClient(String uri) {
        this.mongoClient = MongoClients.create(uri);
    }

    public MongoClient build() {
        return mongoClient;
    }
}
