import java.util.Objects;

public class User implements Premium {
    private String email;


    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User," + email + ",";
    }

    public String toFileString() {

        return "User," + email + ",";
    }

    @Override
    public void showAdditionalInformation(Database database, String partyId) {

    }

// Got this code from A11
    //Specifically the check and updatePassword code
}



