//这个类主要实现建立套接字连接
package module;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;

public class SocketLink {
    private Socket socket=null;

    private Socket socketServer(int port) {
        ServerSocket listen = null;
        Socket socket = null;
        try {
            listen = new ServerSocket(port);
            socket = listen.accept();
            System.out.print("Connection on server established ");
        } catch (Exception e) {
            System.out.println("Some Error Occurred When Initialize Listening Module!");
        }
        return socket;
    }

    private Socket socketClient(String ip, int port) {
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
            System.out.println("Connection on client established ");
            return socket;
        } catch (IOException e1) {
            System.out.println("Can't Connect to Server!");
        }
        return socket;
    }

    //for client
    public SocketLink(String ip,int port){
        socket=socketClient(ip,port);
    }

    //for server
    public SocketLink(int port){
        socket=socketServer(port);
    }

    public Socket getSocket(){
        return socket;
    }

    @Override
    protected void finalize() throws Throwable {
        socket.close();
        super.finalize();
    }
}
