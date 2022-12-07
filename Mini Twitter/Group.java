import java.text.SimpleDateFormat;
import java.util.Date;

public class Group implements Data {
    
    // delare variables and initialize it
    private String groupID;
    private String createdTime;
    private SimpleDateFormat formatCreatedDate = new SimpleDateFormat("MM/dd/YYYY");

    //getters and setters

    @Override
    public void setID(String groupID){
        this.groupID = groupID;
    }

    @Override
    public String getID(){
        return groupID;
    }

    public String toString(){
        return groupID;
    }

    public String formatTimeCreationDate(){
        return formatCreatedDate.format(new Date(System.currentTimeMillis()));
    }

  
    @Override
    public void setCreatedDate() {
        createdTime = formatTimeCreationDate();
    }

    @Override
    public String getCreatedDate() {
        return createdTime;
    }

    @Override
    public User getUser() {
        // TODO Auto-generated method stub
        return null;
    }



}
