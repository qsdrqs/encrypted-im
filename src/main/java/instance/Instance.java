package instance;

import java.net.Socket;
import java.io.*;

public abstract class Instance {
    public void sendMessage(Socket socket, String message) throws Exception {
        OutputStream stream = socket.getOutputStream();
        stream.write(message.getBytes("UTF-8"));
        stream.close();
    }

    public String getMessage(Socket socket) throws Exception {
        InputStream stream = socket.getInputStream();
        //缓冲区
        byte[] bytes = new byte[1024];
        //解码器
        StringBuilder message = new StringBuilder();
        int len;
        len = stream.read(bytes);
        //没有消息就等待
        //while (len == 0) {
            //len = stream.read(bytes);
        //}
        while (len != -1) {
            System.out.println(len);
            message.append(new String(bytes, 0, len, "UTF-8"));
            len=stream.read(bytes);
        }
        return message.toString();
    }



}
