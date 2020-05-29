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
       // outputStream = (ObjectOutputStream) socket.getOutputStream();
        while (true) {
           // OutputStream output = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Message m = new Message();
            String message=sc.nextLine();
            if (message.equals("exit")) {
                break;
            }
            m.setContext(message);
            m.setTimeStap(new Date());
            //obj to bytes
            byte[] bytes = ObjectAndBytes.toByteArray(m);
            //message encryption


            //bytes to obj
            Message EncryptedMsg = (Message) ObjectAndBytes.toObject(bytes);
           // output.write(ObjectAndBytes.toByteArray(m));
            oos.writeObject(EncryptedMsg);
        }
    }

    /*
       输入获取部分
     */

    public String receiveMessage() throws Exception {

        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        System.out.println("start reading.");

        Message m = (Message)input.readObject();
        //obj to bytes
        byte[] bytes = ObjectAndBytes.toByteArray(m);

        //message dencryption

        //bytes to obj
        Message dencrytedMsg = (Message)ObjectAndBytes.toObject(bytes);
        return  dencrytedMsg.getContext();
    }

}
