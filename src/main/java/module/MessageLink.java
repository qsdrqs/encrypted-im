//多线程实现不间断通信
package module;

import instance.Client;

import java.net.Socket;
import java.security.PublicKey;
import java.util.Scanner;

public class MessageLink {

    Client client;
    Scanner sc;
    Socket socket;
    private PublicKey OtherpuK;
    public MessageLink(Client client, Scanner sc) {
        this.client = client;
        this.sc = sc;
    }

    public void run(Socket socket ) {
        this.socket = socket;
        Listen listenThread = new Listen();
        listenThread.start();
        //交换公钥
       // PukExchange.doExchange(socket,this.client);
        send();
    }

    private void send() {
        try {
            System.out.println("你现在可以发消息了：");
            client.sendMessage(sc);
        } catch (Exception e) {
            System.out.println("Send Error!");
            e.printStackTrace();
        }
    }

    private class Listen extends Thread {
        @Override
        public void run() {
                try {
                    while (true) {
                        String info = client.receiveMessage();
                        System.out.println("Once Receive");
                        if (info.equals("exit")) {
                            System.out.println("远端结束了连接。");
                            break;
                        }
                        System.out.println("来自" + client.getIP() + "的消息：" + info);
                    }
                } catch (Exception e) {
                    System.out.println("Listen Error!");
                    e.printStackTrace();
                }
        }
    }
}
