package org.inf.java;

@FunctionalInterface
public interface LongUrlHasher {
    String getShortCode(String longUrl);
}
