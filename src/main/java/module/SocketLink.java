
//这个类主要实现建立套接字连接
package module;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import instance.*;

public class SocketLink {
    public static Socket SocketListen(int port) {
        ServerSocket listen = null;
        Socket socket = null;
        try {
            listen = new ServerSocket(port);
            socket = listen.accept();
            System.out.println("Connection established!");
        } catch (Exception e) {
            System.out.println("Some Error Occurred When Initialize Listening Module!");
        }
        //FIXME:是否要加返回非空的判断?
        return socket;
    }

    public static Socket SocketSend(String ip, int port) {
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
            System.out.println("Connection established!");
            return socket;
        } catch (IOException e1) {
            System.out.println("Can't Connect to Server!");
        }
        //FIXME:是否要加返回非空的判断?
        return socket;
    }

    public static boolean isServer(Scanner sc) {
        System.out.println("1.Server 2.Client");
        int select = Integer.parseInt(sc.next());
        if (select == 1) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean isMessage(Scanner sc) {
        System.out.println("1.Message 2.File");
        int select = Integer.parseInt(sc.next());
        if (select == 1) {
            return true;
        } else
            return false;

    }

    public static void main (String[] args) throws Exception {
        String server_ip = "127.0.0.1";
        int port = 12345;
        Scanner sc = new Scanner(System.in);
        Socket socket = null;
        boolean is_server;
        if (isServer(sc)) {
            socket = SocketListen(port);
            is_server = true;
        } else {
            socket = SocketSend(server_ip, port);
            is_server = false;
        }
        Client client = new Client(socket);
        if (is_server) {
            System.out.print("输入信息:");
            while (sc.nextLine() == null) {
            }
            client.sendMessage(sc.nextLine());
        } else {
            String message = client.getMessage();
            System.out.println("来自" + socket.getInetAddress() + "的消息：" + message);
        }

        sc.close();
        socket.close();
    }
}
