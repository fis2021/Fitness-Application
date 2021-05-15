package fitness.application.models;
import org.dizitart.no2.objects.Id;

public class Chat {
    private String sender;
    private String reciever;
    @Id
    private String name;
    private String message;
    private String finalMessage;

    public Chat(){
    }

    public Chat(String name , String message)
    {
        this.name=name;
        this.message=message;
        this.finalMessage = message;
    }

    public String getSender(){return sender;}

    public void setSender(String username){this.sender=sender;}

    public String getReciever(){return reciever;}

    public void setReciever(String username){this.reciever=reciever;}

    public String getName(){return name;}

    public void setName(String name){this.name=name;}

    public String getMessage(){return message;}

    public void setMessage(String message){this.message=message;}

    public String getFinalMessage(){return finalMessage;}

    public void setFinalMessage(String message){this.finalMessage=message;}

}
