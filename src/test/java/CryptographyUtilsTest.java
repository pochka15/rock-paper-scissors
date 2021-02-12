import cryptography.CryptographyUtils;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CryptographyUtilsTest {

    @Test
    void hmacSha256() {
        assertEquals(CryptographyUtils.byteArrToHex(CryptographyUtils.hmacSha256(
                "Some secret key".getBytes(StandardCharsets.UTF_8),
                "Some secret message".getBytes(StandardCharsets.UTF_8))),
                     "b2b041af6e04a7f52d8553c75c47b84c9c3a9ef55b68c82c9d04aed8aa248d23");
    }
}