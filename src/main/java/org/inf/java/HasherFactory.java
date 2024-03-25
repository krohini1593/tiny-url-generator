package org.inf.java;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Provides utility function to generate SHA256 and MD5 hashes.
 * They are of different lengths and cannot be used as substitutes for one another
 * in a real world tiny url scenario, but this just to demonstrate Java function interface.
 */
public class HasherFactory {

    public static LongUrlHasher getSHAHasher() {
       return (longUrl) -> {
            try {
                var hashBytes = MessageDigest.getInstance("SHA-256").digest(longUrl.getBytes(StandardCharsets.UTF_8));
                return Utils.byteArrayToHexString(hashBytes);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static LongUrlHasher getMD5Hasher() {
        return (longUrl) -> {
            try {
                var hashBytes = MessageDigest.getInstance("MD5").digest(longUrl.getBytes(StandardCharsets.UTF_8));
                return Utils.byteArrayToHexString(hashBytes);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        };
    }

}
