package instance;

import common.Message;
import tools.ObjectAndBytes;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public abstract class Instance {

    protected Socket socket;

    public Instance(Socket socket) {
        this.socket = socket;
    }

    /*
       输出获取部分
     */
    public void sendMessage(Scanner sc) throws Exception {
        OutputStream outputStream = socket.getOutputStream();
        while (true) {
            Message m = new Message();
            String message = sc.nextLine();
            if (message.equals("exit")) {
                break;
            }
            m.setContext(message);
            m.setTimeStap(new Date());
            //obj to bytes
            byte[] bytes = ObjectAndBytes.toByteArray(m);
            //message encryption

            outputStream.write(bytes);

        }
    }

    /*
       输入获取部分
     */


    public String receiveMessage() throws Exception {

        InputStream inputStream = socket.getInputStream();
        System.out.println("start reading.");
        byte[] bytes = new byte[1024000];

        int len = inputStream.read(bytes);
        Message dencrytedMsg = null;
        if (len != -1) {

            //message dencryption

            //bytes to obj

            dencrytedMsg = (Message)ObjectAndBytes.toObject(bytes);
        }
        return  dencrytedMsg.getContext();
    }

}
