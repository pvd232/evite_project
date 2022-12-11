import java.util.ArrayList;
import java.util.List;

public class Host extends Guest{

    // make budget a whole new function later on

    private List<Party> partyList;

    public Host(String email, String name, String userName, String password) {
        super(email, name, userName, password);
        partyList= new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Host," + getEmail()+","+getName()+","+getUserName()+ ","+getPassword();
    }

    public String toFileString(){
        return "Host," + getEmail()+","+getName()+","+getUserName()+ ","+getPassword()+",";
    }



    //host would not extend premium right?



    // add new party
    // get
    // sending invitations = in the evite system



}
