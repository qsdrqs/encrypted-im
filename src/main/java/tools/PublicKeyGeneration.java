package tools;

import java.security.*;

public class PublicKeyGeneration {
    private PublicKey publicKey = null;
    private PrivateKey privateKey = null;

    public PublicKeyGeneration(int keysize) {
        KeyPairGenerator gen = null;
        try {
            gen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        gen.initialize(keysize);
        KeyPair kp = gen.generateKeyPair();
        publicKey = kp.getPublic();
        privateKey = kp.getPrivate();
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
}
