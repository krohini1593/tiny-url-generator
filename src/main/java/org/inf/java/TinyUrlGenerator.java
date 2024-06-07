package org.inf.java;


import org.inf.java.db.ShortUrlDB;

import java.util.Optional;

public class TinyUrlGenerator {

    private final String baseUrl;
    private final LongUrlHasher urlHasher;

    private final ShortUrlDB shortUrlDB;
    public TinyUrlGenerator(String baseUrl, LongUrlHasher urlHasher, ShortUrlDB db) {
        this.baseUrl = baseUrl;
        this.urlHasher = urlHasher;
        this.shortUrlDB = db;
    }

    public String getShortUrl(String longUrl) {
        if (longUrl == null || longUrl.isBlank()) {
            throw new RuntimeException("Invalid null/empty long url");
        }
        Optional<String> shortUrlFromDB = shortUrlDB.getShortUrlIfExists(longUrl);
        String shortUrlToReturn = "";
        shortUrlToReturn = shortUrlFromDB.orElseGet(() -> createNewShortUrl(longUrl));
        return shortUrlToReturn;
    }

    private String createNewShortUrl(String longUrl) {
        String shortUrl = String.format("%s/%s", baseUrl, urlHasher.getShortCode(longUrl));
        shortUrlDB.saveShortUrl(longUrl, shortUrl);
        return shortUrl;
    }

}
