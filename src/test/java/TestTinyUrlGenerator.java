import org.inf.java.HasherFactory;
import org.inf.java.TinyUrlGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

/**
 * Test cases
 * 1. Generate tiny url for long url
 * 2. Test for null or empty input value
 * 3. Return already created tiny url if exists
 */
public class TestTinyUrlGenerator {

    private static TinyUrlGenerator tinyUrlGenerator;

    @BeforeAll
     public static void setUp() {
        final String baseUrl = "http://infurl";
        tinyUrlGenerator = new TinyUrlGenerator(baseUrl, HasherFactory.getMD5Hasher());
    }

    @Test
    void testShortUrlForValidLongUrl (){
        String longUrl = "https://example.com/visit?new=True";
        String expectedShortUrl = "http://infurl/bee40f54e1408b66ff7d7db8c6c2c523";
        assertEquals(expectedShortUrl,tinyUrlGenerator.getShortUrl(longUrl));
    }

    @Test
    void testShortUrlForBlankLongUrl (){
        assertThrowsExactly(RuntimeException.class, ()->tinyUrlGenerator.getShortUrl(""));
        assertThrowsExactly(RuntimeException.class, ()->tinyUrlGenerator.getShortUrl("  "));
    }

    @Test
    void testShortUrlForNullLongUrl (){
        assertThrowsExactly(RuntimeException.class, ()->tinyUrlGenerator.getShortUrl(null));
    }
}
