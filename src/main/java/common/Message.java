package common;

import java.security.PublicKey;
import java.util.Date;

public class Message implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int MesType;
    private String sender;     //信息的发送者
    private String  receiver;   //信息的接受者
    private String context;    //信息内容
    private Date timeStap;   //时间戳
    private String signture;   //数字签名

    public int getMesType() {
        return MesType;
    }

    public void setMesType(int mesType) {
        MesType = mesType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getTimeStap() {
        return timeStap;
    }

    public void setTimeStap(Date timeStap) {
        this.timeStap = timeStap;
    }
    public String getSignture() {
        return signture;
    }

    public void setSignture(String signture) {
        this.signture = signture;
    }

    public Message(){
        this.timeStap = new Date();
    }
}
