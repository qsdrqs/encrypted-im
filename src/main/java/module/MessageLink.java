//多线程实现不间断通信
package module;

import instance.Client;
import tools.ObjectAndBytes;

import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import common.Message;

public class MessageLink {

    Client client;
    Scanner sc;
    Socket socket;
    public MessageLink(Client client, Scanner sc) {
        this.client = client;
        this.sc = sc;
    }

    public void run(Socket socket ) {
        this.socket = socket;
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
                byte[] bytes=ObjectAndBytes.toByteArray(message);
                client.sendMessage(bytes);
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
                    byte[] bytes=client.receiveMessage();
                    if(bytes==null){
                        continue;
                    }
                    Message message=(Message)ObjectAndBytes.toObject(bytes);
                    System.out.println("Once Receive");
                    if (message.getContext().equals("exit")) {
                        System.out.println("远端结束了连接。");
                        break;
                    }
                    System.out.println("来自" + client.getIP() + "的消息：" + message.getContext());
                }
                client.endReceive();
            } catch (Exception e) {
                System.out.println("Listen Error!");
                e.printStackTrace();
            }
        }
    }
}
