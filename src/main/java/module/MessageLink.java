//多线程实现不间断通信
package module;

import instance.Client;
import tools.ObjectAndBytes;
import tools.CipherModule;
import tools.SHA256Util;

import java.net.Socket;
import java.security.PublicKey;
import java.util.Date;
import java.util.Scanner;

import common.Message;
import org.apache.commons.codec.binary.Hex;

public class MessageLink {

    private Client client;
    private Scanner sc;
    private PublicKey otherPuK;
    private Date newestDate=null;

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

    private byte[] signature(String source) throws Exception{
        byte[] shaResult=SHA256Util.stringToSHA256(source);
        byte[] encryptResult=CipherModule.encrypt(shaResult, client.user.getPrk());
        return encryptResult;
    }

    private boolean chkSign(String source,byte[] signature) throws Exception{
        //decrypt
        byte[] decryptedBytes=CipherModule.decrypt(signature,otherPuK);

        //check
        byte[] shaResult=SHA256Util.stringToSHA256(source);
        for (int i=0; i<decryptedBytes.length; ++i) {
            if(decryptedBytes[i]!=shaResult[i]){
                return false;
            }
        }
        return true;
    }

    private void send() {
        try {
            System.out.println("你现在可以发消息了：");
            client.initSend();
            while (true) {
                Message message = new Message();
                message.setContext(sc.nextLine());
                message.setTimeStap(new Date());
                message.setSender(client.user.getName());
                message.setSignature(signature(message.getContext()+message.getTimeStap().toString()));

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
                    //处理空消息
                    if(message.getContext().equals("")){
                        continue;
                    }

                    //验证签名
                    if(!chkSign(message.getContext()+message.getTimeStap().toString(),message.getSignature())){
                        System.out.println("签名验证失败，丢弃消息。");
                        continue;
                    }

                    //防重放
                    if(newestDate!=null&& message.getTimeStap().before(newestDate)){
                        System.out.println("时间戳验证失败，丢弃消息。");
                        continue;
                    }
                    newestDate=message.getTimeStap();

                    if (message.getContext().equals("exit")) {
                        System.out.println("远端结束了连接。");
                        break;
                    }

                    System.out.println("来自" + message.getSender() + "的消息：" + message.getContext());
                }
                client.endReceive();
            } catch (Exception e) {
                System.out.println("Listen Error!");
                e.printStackTrace();
            }
        }
    }
}
