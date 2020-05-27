package instance;

import common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public abstract class Instance {

    protected Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public Instance(Socket socket) {
        this.socket = socket;
    }

    /*
       输出获取部分
     */
    public void sendMessage(Scanner sc) throws Exception {
       // outputStream = (ObjectOutputStream) socket.getOutputStream();
        while (true) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Message m = new Message();
            m.doInit();
            String message=sc.nextLine();
            if (message.equals("exit")) {
                break;
            }
            /*
            对消息的处理
             */
            m.setContext(message);
            m.setTimeStamp(new Date().toString());
            oos.writeObject(m);
        }
    }

    /*
       输入获取部分
     */

    public String receiveMessage() throws IOException,ClassNotFoundException {

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        System.out.println("start reading.");
        Message m = (Message) ois.readObject();
        System.out.println("end reading.");
        return  m.getContext();

    }

}
