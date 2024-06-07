package org.inf.java.db;

import java.util.Optional;

public interface ShortUrlDB {

    void saveShortUrl(String longUrl, String shortCode);

    String getLongUrlFor(String shortCode);

    Optional<String> getShortUrlIfExists(String longUrl);
}
