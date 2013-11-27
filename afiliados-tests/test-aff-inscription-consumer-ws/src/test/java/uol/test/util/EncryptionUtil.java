/**
 * 
 */
package uol.test.util;

import java.math.BigInteger;
import java.nio.charset.Charset;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @author rsilva
 * 
 */
public class EncryptionUtil {

    private static final BigInteger CIPHER_KEY = new BigInteger("105822464640323713309919498184081015433");

    private static SecretKeySpec KEY = new SecretKeySpec(CIPHER_KEY.toByteArray(), "AES");

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private EncryptionUtil() {
    }

    public static String encrypt(String text) {
        byte[] plaintext = text.getBytes(UTF_8);
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, KEY);
            byte[] cipherText = cipher.doFinal(plaintext);
            byte[] base64 = Base64.encodeBase64(cipherText);
            return new String(base64, UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
