public class User {
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


    // Got this code from A11
    //Specifically the check and updatePassword code
}



