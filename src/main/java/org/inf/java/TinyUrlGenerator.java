package org.inf.java;


public class TinyUrlGenerator {

    private String baseUrl;
    private LongUrlHasher urlHasher;
    public TinyUrlGenerator(String baseUrl, LongUrlHasher urlHasher) {
        this.baseUrl = baseUrl;
        this.urlHasher = urlHasher;
    }

    public String getShortUrl(String longUrl) {
        if (longUrl == null || longUrl.isBlank()) {
            throw new RuntimeException("Invalid null/empty long url");
        }
        String shortCode = urlHasher.getShortCode(longUrl);
        return String.format("%s/%s", baseUrl, shortCode);
    }
}
