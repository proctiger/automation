package uol.collective.offer.click.test;

import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CryptoUtils
{

    private static Logger logger = LoggerFactory.getLogger( CryptoUtils.class );

    private static String digestAlgorithm = "MD5";

    private static final BigInteger CIPHER_KEY = new BigInteger( "105516464640323713306969498184081089733" );

    private static ThreadLocal<Cipher> encryptCipherHolder = new ThreadLocal<Cipher>()
    {

        @Override
        protected synchronized Cipher initialValue()
        {
            Key key = new SecretKeySpec( CIPHER_KEY.toByteArray(), "AES" );

            Cipher encryptCipher = null;
            try
            {
                encryptCipher = Cipher.getInstance( "AES" );
                encryptCipher.init( Cipher.ENCRYPT_MODE, key );

            } catch ( Exception e )
            {
                logger.error( "Error starting up AES Cypher", e );
            }
            return encryptCipher;
        }
    };

    private static ThreadLocal<Cipher> decryptCipherHolder = new ThreadLocal<Cipher>()
    {

        @Override
        protected synchronized Cipher initialValue()
        {
            Key key = new SecretKeySpec( CIPHER_KEY.toByteArray(), "AES" );

            Cipher decryptCipher = null;
            try
            {
                decryptCipher = Cipher.getInstance( "AES" );
                decryptCipher.init( Cipher.DECRYPT_MODE, key );

            } catch ( Exception e )
            {
                logger.error( "Error starting up AES Cypher", e );
            }
            return decryptCipher;
        }
    };

    public static Cipher getEncryptCipher()
    {
        return encryptCipherHolder.get();
    }

    public static Cipher getDecryptCipher()
    {
        return decryptCipherHolder.get();
    }

    public String encrypt(String value)
    {
        try
        {
            return Hex.encodeHexString( getEncryptCipher().doFinal( value.getBytes() ) );
        } catch ( Exception e )
        {
            logger.error( "Encrypt error", e );
        }
        return null;
    }

    public String decrypt(String value)
    {
        try
        {
            return new String( getDecryptCipher().doFinal( Hex.decodeHex( value.toCharArray() ) ) );
        } catch ( Exception e )
        {
            logger.error( "Decrypt error", e );
        }
        return null;
    }

    public String getDigest(String value)
    {
        String digest = null;
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance( digestAlgorithm );
            BigInteger hash = new BigInteger( 1, md.digest( value.getBytes() ) );
            digest = hash.toString( 16 );
            while ( digest.length() < 32 )
            {
                digest = 0 + digest;
            }
        } catch ( NoSuchAlgorithmException e )
        {
            logger.error( "Nao foi encontrada a implementacao para gerar o digest: " + e.getMessage() );
        }
        return digest;
    }
}