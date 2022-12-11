import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Guest extends User{
    //private RSVP rsvp;
    //? this would not be an instance variable then?


    private String name;
    private String userName;
    //Might not need this*
    private String password;
    private List<Party> invitations;
// or List<PartyId>

    //? This doesn't need to be an instance variable then if you make premium


    public Guest(String email, String name, String userName, String password) {

        super(email);
        //System.out.print("Constructor name:" + name);
        //this.rsvp= rsvp;
        //Set this up later*
        this.userName= userName;
        this.password= password;
        this.name= name;
        invitations= new ArrayList<>();

    }

    @Override
    public String toString() {
        return "Guest,"+getEmail()+ ","+name+","+userName+","+password;
    }

    public String toFileString(){
        return "Guest,"+ getEmail()+ ","+name+","+userName+","+password+",";
    }

    public String getName() {
        return name;
    }

    public List<Party> getInvitations() {
        return invitations;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInvitations(List<Party> invitations) {
        this.invitations=invitations;
    }

    public boolean checkPassword(String attempt) {
        return attempt.equals(password);
    }

    public boolean updatePassword(String attempt, String newPword) {
        boolean updated = false;
        if (checkPassword(attempt)) {
            this.password = newPword;
            updated = true;
        }
        return updated;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest user = (Guest) o;

        boolean truth = false;
        if (Objects.equals(userName, user.getUserName()) && Objects.equals(password, user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

}
