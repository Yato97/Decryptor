import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Decrypt {
    private static final String ENCRYPTION_KEY = "12345678926457893563672635876353"; // Secret Key
    private static final String ENCRYPTION_IV = "1234567891123456"; // Salt

    public static String encrypt(String src) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, makeKey(), makeIv()); // Initialiser la classe pour le mode de
                                                                   // fonctionnement précisé (Cipher.ENCRYPT_MODE et
                                                                   // Cipher.DECRYPT_MODE)
            byte[] encrypted = cipher.doFinal(src.getBytes()); // Ajouter la dernière partie des données à traiter et
                                                               // générer le résultat
            String toHex = Hex.encodeHexString(encrypted); // Conversion Base43 => Hex
            return toHex;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String src) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, makeKey(), makeIv()); // Initialiser la classe pour le mode de
                                                                   // fonctionnement précisé (Cipher.ENCRYPT_MODE et
                                                                   // Cipher.DECRYPT_MODE)
            byte[] encodeBase = Hex.decodeHex(src.toCharArray()); // Conversion Hex => Base 64
            String result = Base64.getEncoder().encodeToString((encodeBase));
            return new String(cipher.doFinal(Base64.getDecoder().decode(result))); // Ajouter la dernière partie des
                                                                                   // données à traiter et générer le
                                                                                   // résultat
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static AlgorithmParameterSpec makeIv() { // Genere le vecteur d'initialisation
        try {
            return new IvParameterSpec(ENCRYPTION_IV.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    static Key makeKey() { // Genere la clé secréte
        byte[] key;
        try {
            key = ENCRYPTION_KEY.getBytes("UTF-8");
            return new SecretKeySpec(key, "AES");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // Convertisseur Hexa
    private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
            'F' };

    public static final String toHex(byte[] data) {
        final StringBuffer sb = new StringBuffer(data.length * 2);
        for (int i = 0; i < data.length; i++) {
            sb.append(DIGITS[(data[i] >>> 4) & 0x0F]);
            sb.append(DIGITS[data[i] & 0x0F]);
        }
        return sb.toString();
    }
}