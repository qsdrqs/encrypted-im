package instance;

import module.Main;
import common.User;

import java.io.*;
import java.net.Socket;

public class Client{
    public User user;

    protected Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;

    public Client(String ip,int port,String username,String passwd) {
        try {
            socket=new Socket(ip,port);
        } catch(Exception e) {
            e.printStackTrace();
        }
        this.user = new User(username);
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

        byte[] buffer = new byte[10240];

        int len=inputStream.read(buffer);

        //保证返回的buffer长度跟收到的长度匹配
        byte[] bytes=new byte[len];
        System.arraycopy(buffer,0,bytes,0,len);
        return bytes;
    }

}
