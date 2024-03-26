import org.inf.java.HasherFactory;
import org.inf.java.LongUrlHasher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases
 * 1. Generate hashcode for long url (sha256 and md5)
 * 2. Test hasher for consecutive inputs
 */
public class TestHashCodeGeneration {

    @Test
    void testSHAHashGeneration() {
        String longUrl = "https://example.com/visit?new=True";
        LongUrlHasher urlHasher = HasherFactory.getSHAHasher();
        var shortCode = urlHasher.getShortCode(longUrl);
        assertEquals("12440b95d63d7ed22462d6548005fde1dbc2875e550e2495d989de061bc73d2c", urlHasher.getShortCode(longUrl));
    }

    @Test
    void testMD5HashGeneration() {
        String longUrl = "https://example.com/visit?new=True";
        LongUrlHasher urlHasher = HasherFactory.getMD5Hasher();
        var shortCode = urlHasher.getShortCode(longUrl);
        assertEquals("bee40f54e1408b66ff7d7db8c6c2c523", urlHasher.getShortCode(longUrl));
    }

    @Test
    void testSHAHashGenerationOnConsecutiveInputs() {
        LongUrlHasher urlHasher = HasherFactory.getSHAHasher();
        String longUrl = "https://example.com/visit?new=True";
        assertEquals("12440b95d63d7ed22462d6548005fde1dbc2875e550e2495d989de061bc73d2c", urlHasher.getShortCode(longUrl));
        longUrl = "https://example.com/visit?new=False";
        assertEquals("4ba2311fcb17b19d5c13068819ad0624f3f50df074c9108183711f2a6fdb3947", urlHasher.getShortCode(longUrl));
    }
}
