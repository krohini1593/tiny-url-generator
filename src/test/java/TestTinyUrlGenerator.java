import org.inf.java.HasherFactory;
import org.inf.java.TinyUrlGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("When long url is valid expected short url is generated")
    void testShortUrlForValidLongUrl (){
        String longUrl = "https://example.com/visit?new=True";
        String expectedShortUrl = "http://infurl/bee40f54e1408b66ff7d7db8c6c2c523";
        assertEquals(expectedShortUrl,tinyUrlGenerator.getShortUrl(longUrl));
    }

    @Test
    @DisplayName("When long url is empty/blank short url generator throws exception")
    void testShortUrlForBlankLongUrl (){
        var exception = assertThrowsExactly(RuntimeException.class, ()->tinyUrlGenerator.getShortUrl(""));
        assertEquals("Invalid null/empty long url", exception.getMessage());
        exception = assertThrowsExactly(RuntimeException.class, ()->tinyUrlGenerator.getShortUrl("  "));
        assertEquals("Invalid null/empty long url", exception.getMessage());
    }

    @Test
    @DisplayName("When long url is null short url generator throws exception")
    void testShortUrlForNullLongUrl (){
        var exception = assertThrowsExactly(RuntimeException.class, ()->tinyUrlGenerator.getShortUrl(null));
        assertEquals("Invalid null/empty long url", exception.getMessage());
    }
}
