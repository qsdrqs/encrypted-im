package instance;

import java.net.Socket;

public class Server extends Instance{
    Socket socket;
    Server(Socket socket){
        this.socket=socket;
    }

}


