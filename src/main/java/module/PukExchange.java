package module;

import common.Message;
import instance.Client;
import tools.ObjectAndBytes;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.security.PublicKey;

public class PukExchange {
    Client client;

    public PukExchange(Client client) {
        this.client = client;
    }

    public PublicKey doExchange() {
        PublicKey othPuk = null;
        System.out.println("现在正在交换公钥...");
        byte [] bytes = client.user.getPuk().getEncoded();
        client.initSend();
        client.sendMessage(bytes);
        client.initReceive;
        byte[] rePuk = client.receiveMessage();
        othPuk = (PublicKey)ObjectAndBytes.toObject(rePuk);
        return othPuk;
    }

}
