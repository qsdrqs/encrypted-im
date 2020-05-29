package instance;

import common.User;

import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class Client extends Instance {
    public User user;

    public Client(Socket socket,String Username) throws NoSuchAlgorithmException {
        super(socket);
        this.user = new User(Username);
    }

    public String getIP(){
        return super.socket.getInetAddress().getHostAddress();
    }

}

