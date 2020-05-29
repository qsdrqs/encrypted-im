package instance;

import common.Message;
import tools.ObjectAndBytes;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public abstract class Instance {

    protected Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;

    public Instance(Socket socket) {
        this.socket = socket;
    }

    /*
       输出部分
     */
    public void initSend() throws IOException {
        outputStream = socket.getOutputStream();
    }

    public void endSend() throws IOException {
        outputStream.close();
    }

    public void sendMessage(byte[] bytes) throws Exception {
        outputStream.write(bytes);

    }

    /*
       输入部分
     */

    public void initReceive() throws Exception{
        inputStream = socket.getInputStream();
    }

    public void endReceive() throws Exception{
        inputStream.close();
    }

    public byte[] receiveMessage() throws Exception {

        System.out.println("start reading.");
        byte[] bytes = new byte[10240];

        inputStream.read(bytes);
        return bytes;
    }

}
