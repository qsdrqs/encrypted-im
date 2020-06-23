package instance;

import common.Message;
import tools.ObjectAndBytes;
import tools.PublicKeyGeneration;

import java.io.OutputStream;
import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;

public class Server {
    HashMap<Integer, Socket> socketMap;
    ServerSocket listenSocket = null;
    PublicKey publicKey;
    PrivateKey privateKey;

    public Server(int port) {
        PublicKeyGeneration publicKeyGeneration = new PublicKeyGeneration(2048);
        try {
            listenSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Listen implements Runnable {

        @Override
        public void run() {
            Socket socket=null;
            try {
                socket=listenSocket.accept();
                Message TransPubKey= new Message();


            } catch(Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void sendMessage(OutputStream outputStream,byte[] bytes){

    }
}
