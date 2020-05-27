package instance;

import java.net.Socket;
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
    public void initSend() throws IOException {
        outputStream = socket.getOutputStream();//这些流在一个socket中似乎只能打开一次。
    }

    public void endSend() throws IOException {
        outputStream.close();
    }

    public void sendMessage(String message) throws Exception {
        outputStream.write(message.getBytes("UTF-8"));
    }

    /*
       输入获取部分
     */

    public void initReceive() throws IOException {
        inputStream = socket.getInputStream();//这些流在一个socket中似乎只能打开一次。
    }

    public void endReceive() throws IOException {
        inputStream.close();
    }

    public String receiveMessage() throws IOException {
        //缓冲区
        byte[] bytes = new byte[1024];
        //解码器
        String message = null;
        int len;
        len = inputStream.read(bytes);
        if (len != -1) {
            message = new String(bytes, 0, len, "UTF-8");
        }
        return message;
    }

}
