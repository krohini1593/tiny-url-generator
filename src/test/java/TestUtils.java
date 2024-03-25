import org.inf.java.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {

    @Test
    void testByteArrayToHexStringConversion() {
        var bytes = new byte[]{90, -63};
        assertEquals("5ac1", Utils.byteArrayToHexString(bytes));
    }
}
