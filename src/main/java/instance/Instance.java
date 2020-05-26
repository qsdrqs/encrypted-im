package instance;

import java.net.Socket;
import java.util.Scanner;
import java.io.*;

public abstract class Instance {

    protected Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public Instance(Socket socket) {
        this.socket = socket;
    }

    /*
       输出获取部分
     */
    public void sendMessage(Scanner sc) throws Exception {
        outputStream = socket.getOutputStream();
        while (true) {
            String message=sc.next();
            if (message.equals("exit")) {
                break;
            }
            outputStream.write(message.getBytes("UTF-8"));
        }
        outputStream.close();
    }

    /*
       输入获取部分
     */

    public String receiveMessage() throws IOException {
        inputStream = socket.getInputStream(); //这些流在一个socket中似乎只能打开一次。
        //缓冲区
        byte[] bytes = new byte[1024];
        //解码器
        String message=null;
        int len;
        System.out.println("start reading.");
        len = inputStream.read(bytes);
        System.out.println("end reading.");
        while (len != -1) {
            message=new String(bytes, 0, len, "UTF-8");
            System.out.println("来自" + socket.getInetAddress().toString() + "的消息：" + message);
            len = inputStream.read(bytes);
        }
        inputStream.close();
        System.out.println("message");
        return message;
    }

}
