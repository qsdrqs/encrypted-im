package common;

public class Message implements java.io.Serializable{
    private String MesType;
    private String sender;  //信息的发送者
    private String receiver;   //信息的接受者
    private String context;     //信息内容
    private String TimeStamp;   //时间戳
    private String signture;    //数字签名

    public String getMesType() {
        return MesType;
    }

    public void setMesType(String mesType) {
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

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }
    public String getSignture() {
        return signture;
    }

    public void setSignture(String signture) {
        this.signture = signture;
    }
    public void doInit(){
        this.MesType = "0";
        this.sender = "Unknow";
        this.receiver = "Unknow";
        this.context = null;
        this.signture = "*******";
        this.TimeStamp = null;
    }

}
