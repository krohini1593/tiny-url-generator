import com.mongodb.client.MongoClient;
import org.inf.java.HasherFactory;
import org.inf.java.TinyUrlGenerator;
import org.inf.java.db.MongoClientBuilder;
import org.inf.java.db.ShortUrlDB;
import org.inf.java.db.ShortUrlMongoStore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases
 * 1. Generate tiny url for long url
 * 2. Test for null or empty input value
 * 3. Return already created tiny url if exists
 */
public class TestTinyUrlGenerator {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.0");

    private static MongoClient mongoClient;
    private static TinyUrlGenerator tinyUrlGenerator;

    private static ShortUrlDB shortUrlDB;

    @BeforeAll
     public static void setUp() {
        final String baseUrl = "http://infurl";

        mongoDBContainer.start();
        final String mongoDBUrl = mongoDBContainer.getConnectionString();
        mongoClient = MongoClientBuilder.newClientWithUri(mongoDBUrl).build();
        shortUrlDB = new ShortUrlMongoStore(mongoClient, "test", "short_to_long_url");
        tinyUrlGenerator = new TinyUrlGenerator(baseUrl, HasherFactory.getMD5Hasher(), shortUrlDB);
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

    @Test
    @DisplayName("When long url is valid and not present in db new short url is generated")
    void testNewShortUrlForValidLongUrl() {
        String longUrl = "https://example.com/visit?new=True";
        assertEquals(Optional.empty(), shortUrlDB.getShortUrlIfExists(longUrl));
        String shortUrl = tinyUrlGenerator.getShortUrl(longUrl);
        assertEquals(shortUrl, shortUrlDB.getShortUrlIfExists(longUrl).get());
    }

    @AfterAll
    public static void teardown() {
        mongoClient.close();
        mongoDBContainer.stop();
    }
}
