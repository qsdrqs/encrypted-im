
package module;

import instance.Client;

import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static boolean isServer(Scanner sc) {
        System.out.println("1.Server 2.Client");
        int select = Integer.parseInt(sc.next());
        if (select == 1) {
            return true;
        } else {
            return false;
        }
        //TODO:异常判断

    }

    public static boolean isMessage(Scanner sc) throws Exception{
        System.out.println("1.Message 2.File");
        int select = Integer.parseInt(sc.next());
        if (select == 1) {
            return true;
        } else if(select==2)
            return false;
        else{

            System.err.println("Error!");
            return false;
        }

    }

    public static void main (String[] args) throws Exception {
        String serverIp = "127.0.0.1";
        int port = 12345;
        Scanner sc = new Scanner(System.in);
        boolean is_server=isServer(sc);
        boolean is_message=isMessage(sc);

        Socket socket=null;
        SocketLink link;
        if (is_server) {
            link =new SocketLink(port);
        } else {
            link =new SocketLink(serverIp,port);
        }
        socket=link.getSocket();

        if (is_message){
            Client client = new Client(socket);
            MessageLink messageLink=new MessageLink(client,sc);
            messageLink.run(socket);
        }

        System.out.println("Exiting..");

        sc.close();
    }
}
