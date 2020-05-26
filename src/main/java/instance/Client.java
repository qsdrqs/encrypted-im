package instance;

import java.net.Socket;

public class Client extends Instance {

    public Client(Socket socket) {
        super(socket);
    }

    public String getIP(){
        return super.socket.getInetAddress().toString();
    }




}

