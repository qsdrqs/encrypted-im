/*
重构的主要目的是实现端到端的通信，即C/C通信
先在公网上实现通信
 */

//这个类主要实现建立套接字连接
package module;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketLink{
    public static Socket SocketListen(int port){
        ServerSocket listen=null;
        Socket socket=null;
        try{
            listen=new ServerSocket(port);
            socket=listen.accept();
            System.out.println("Connection established!");
        } catch (Exception e){
            System.out.println("Some Error Occurred When Initialize Listening Module!");
        }
        //FIXME:是否要加返回非空的判断?
        return socket;
    }

    public static Socket SocketSend(String ip, int port){
        Socket socket=null;
        try {
            socket =new Socket(ip,port);
            System.out.println("Connection established!");
            return socket;
        } catch(IOException e1) {
            System.out.println("Input Error!");
        }
        //FIXME:是否要加返回非空的判断?
        return socket;
    }

    public static void main (String[] args){
        //先跟自己通信 TODO:将ip与端口的值参数化
        String ip="127.0.0.1";
        int port=12345;
        Scanner sc=new Scanner(System.in);
        System.out.println("1.Server 2.Client");
        int select=Integer.parseInt(sc.next());
        Socket socket = null;
        if(select==1){
            socket=SocketListen(port);
        }
        else if(select==2){
            socket=SocketSend(ip,port);
        }
        else
            System.out.println("Error Input!");
        
        msg message=new msg(socket,sc);
    }
}
