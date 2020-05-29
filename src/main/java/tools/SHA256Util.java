//这个类可以将指定String转化为sha256串
package tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Util {
    public static byte[] stringToSHA256(String source){
        byte[] bytes;
        MessageDigest messageDigest;
        try {
            messageDigest=MessageDigest.getInstance("SHA-256");
            bytes=messageDigest.digest(source.getBytes());
        } catch(NoSuchAlgorithmException e) {
            throw new RuntimeException("生成SHA256序列失败");
        }
        return bytes;
    }
}
