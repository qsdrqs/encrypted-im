package module;

import common.User;

import javax.crypto.Cipher;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class CipherModule {

    // 用公钥加密:
    public static byte[] encrypt(byte[] message, PublicKey puk) throws GeneralSecurityException {
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("RSA");
        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, puk);
        return cipher.doFinal(message);
    }

    // 用私钥解密:
    public static byte[] decrypt(byte[] input, PrivateKey prk) throws GeneralSecurityException {
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("RSA");
        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, prk);
        return cipher.doFinal(input);
    }
}