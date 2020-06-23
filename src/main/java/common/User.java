package common;

import java.security.*;

import tools.PublicKeyGeneration;

public class User {
    private String name;
    private PrivateKey prk;
    private PublicKey puk;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PrivateKey getPrk() {
        return prk;
    }

    public void setPrk(PrivateKey prk) {
        this.prk = prk;
    }

    public PublicKey getPuk() {
        return puk;
    }

    public void setPuk(PublicKey puk) {
        this.puk = puk;
    }

    public User(String name) {
        this.name = name;
        // 生成公钥／私钥对:
        PublicKeyGeneration publicKeyGeneration = new PublicKeyGeneration(2048);
        this.prk = publicKeyGeneration.getPrivateKey();
        this.puk = publicKeyGeneration.getPublicKey();
    }
}
