package instance;

import java.net.Socket;

public class Client extends Instance{
    Socket socket;
    public Client(Socket socket){
        this.socket=socket;
    }

    public void sendMessage(String message) throws Exception{
        super.sendMessage(socket,message);
    }

    public String getMessage() throws Exception{
        String message=super.getMessage(socket);
        return message;
    }

}


