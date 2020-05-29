package module;

import common.Message;
import instance.Client;
import tools.ObjectAndBytes;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.security.PublicKey;

public class PublicKeyExchange {
    Client client;

    public PublicKeyExchange(Client client) {
        this.client = client;
    }

    public PublicKey doExchange() throws Exception{
        PublicKey othPuk = null;
        System.out.println("现在正在交换公钥...");
        byte [] bytes = ObjectAndBytes.toByteArray(client.user.getPuk());
        client.initSend();
        client.sendMessage(bytes);
        client.initReceive();
        byte[] rePuk = client.receiveMessage();
        othPuk = (PublicKey)ObjectAndBytes.toObject(rePuk);
        System.out.println("交换公钥成功");
        return othPuk;
    }

}
