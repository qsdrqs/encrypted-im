package tools;


import javax.crypto.Cipher;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

public class AsymCipherModule {
    /**
     * Cipher加密最大117bytes，解密最大128bytes，因此需要拆解
     * @author qsdrqs
     */

    //加密:
    public static byte[] encrypt(byte[] message, PublicKey puk) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, puk);

        return split(message,cipher,245);
    }

    public static byte[] encrypt(byte[] message, PrivateKey prk) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, prk);

        return split(message,cipher,245);
    }


    //拆解部分
    private static byte[] split(byte[] input,Cipher cipher,int block) throws GeneralSecurityException{
        int offset = 0;//偏移值
        int bytesLength = input.length;
        byte[] result={};
        byte[] cache={};
        while (bytesLength > offset) {
            if (bytesLength - offset > block) {
                cache = cipher.doFinal(input, offset, block);
                offset += block;
            } else {
                cache = cipher.doFinal(input, offset, bytesLength - offset);
                offset = bytesLength;
            }
            result = Arrays.copyOf(result, result.length + cache.length);
            System.arraycopy(cache, 0, result, result.length - cache.length, cache.length);
        }
        return result;
    }


    //解密:
    public static byte[] decrypt(byte[] input, PrivateKey prk) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prk);

        return split(input,cipher,256);
    }

    public static byte[] decrypt(byte[] input, PublicKey puk) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, puk);

        return split(input,cipher,256);
    }
}
