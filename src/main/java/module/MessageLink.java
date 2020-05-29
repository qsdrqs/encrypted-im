//多线程实现不间断通信
package module;

import instance.Client;
import tools.ObjectAndBytes;

import java.net.Socket;
import java.security.PublicKey;
import java.util.Date;
import java.util.Scanner;

import common.Message;

public class MessageLink {

    private Client client;
    private Scanner sc;
    private PublicKey otherPuK;

    public MessageLink(Client client, Scanner sc,PublicKey otherPuK) {
        this.client = client;
        this.sc = sc;
        this.otherPuK= otherPuK;
    }

    public void run() {
        Listen listenThread = new Listen();
        listenThread.start();
        send();
    }

    private void send() {
        try {
            System.out.println("你现在可以发消息了：");
            client.initSend();
            while (true) {
                Message message = new Message();
                message.setContext(sc.nextLine());
                message.setTimeStap(new Date());
                byte[] sourceBytes=ObjectAndBytes.toByteArray(message);

                //encrypt
                byte[] encryptedBytes=CipherModule.encrypt(sourceBytes,otherPuK);

                client.sendMessage(encryptedBytes);
                if (message.getContext().equals("exit")) {
                    break;
                }
            }
            client.endSend();
        } catch (Exception e) {
            System.out.println("Send Error!");
            e.printStackTrace();
        }
    }

    private class Listen extends Thread {
        @Override
        public void run() {
            try {
                client.initReceive();
                while (true) {
                    byte[] sourceBytes=client.receiveMessage();
                    if(sourceBytes==null){
                        continue;
                    }

                    //decrypt
                    byte[] decryptedBytes=CipherModule.decrypt(sourceBytes,client.user.getPrk());

                    Message message=(Message)ObjectAndBytes.toObject(decryptedBytes);
                    if (message.getContext().equals("exit")) {
                        System.out.println("远端结束了连接。");
                        break;
                    }

                    //处理空消息
                    if(message.getContext().equals("")){
                        continue;
                    }
                    System.out.println("来自" + client.user.getName() + "的消息：" + message.getContext());
                }
                client.endReceive();
            } catch (Exception e) {
                System.out.println("Listen Error!");
                e.printStackTrace();
            }
        }
    }
}
