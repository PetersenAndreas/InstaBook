package encryption;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class CryptoMngr {
    private static final String PROPERTIESFILEPATH = "/crypto.properties";
    private static ArrayList<String> cryptoArray = new ArrayList();


    public static ArrayList<String> cryptoPath(){
        try {
            InputStream input = CryptoMngr.class.getResourceAsStream(PROPERTIESFILEPATH);

            Properties pros = new Properties();
            pros.load(input);

            String key = pros.getProperty("encrypt_key");
            String initVector = pros.getProperty("encrypt_initVector");
            String cipher_instance = pros.getProperty("cipher_instance");
            cryptoArray.add(key);
            cryptoArray.add(initVector);
            cryptoArray.add(cipher_instance);
            if (key == null || initVector == null || cipher_instance == null) {
                throw new IOException();
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException("Could not read from properties-file");
        }
        return cryptoArray;
    }

    public static String encrypt(String value) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(cryptoPath().get(0).getBytes("UTF-8"), "AES");
            IvParameterSpec iv = new IvParameterSpec(cryptoPath().get(1).getBytes("UTF-8"));

            Cipher cipher = Cipher.getInstance(cryptoPath().get(2));
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String encrypted) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(cryptoPath().get(0).getBytes("UTF-8"), "AES");
            IvParameterSpec iv = new IvParameterSpec(cryptoPath().get(1).getBytes("UTF-8"));

            Cipher cipher = Cipher.getInstance(cryptoPath().get(2));
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}