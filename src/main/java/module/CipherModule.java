package module;


import javax.crypto.Cipher;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

public class CipherModule {
    /*
     * Cipher加密最大117bytes，解密最大128bytes，因此需要拆解
     * @author qsdrqs
     */

    // 用公钥加密:
    public static byte[] encrypt(byte[] message, PublicKey puk) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, puk);

        //拆解部分
        int MAX_ENCRYPT_BLOCK = 256;
        int offset = 0;//偏移值
        int bytesLength = message.length;
        byte[] result={};
        byte[] cache={};
        while (bytesLength > offset) {
            if (bytesLength - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(message, offset, MAX_ENCRYPT_BLOCK);
                offset += MAX_ENCRYPT_BLOCK;
            } else {
                cache = cipher.doFinal(message, offset, bytesLength - offset);
                offset = bytesLength;
            }
            result = Arrays.copyOf(result, result.length + cache.length);
            System.arraycopy(cache, 0, result, result.length - cache.length, cache.length);
        }

        return result;
    }

    // 用私钥解密:
    public static byte[] decrypt(byte[] input, PrivateKey prk) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prk);

        //拆解部分
        int MAX_DECRYPT_BLOCK = 256;
        int offset = 0;//偏移值
        int bytesLength = input.length;
        byte[] result={};
        byte[] cache={};
        while (bytesLength > offset) {
            if (bytesLength - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(input, offset, MAX_DECRYPT_BLOCK);
                offset += MAX_DECRYPT_BLOCK;
            } else {
                cache = cipher.doFinal(input, offset, bytesLength - offset);
                offset = bytesLength;
            }
            result = Arrays.copyOf(result, result.length + cache.length);
            System.arraycopy(cache, 0, result, result.length - cache.length, cache.length);
        }

        return result;
    }
}
