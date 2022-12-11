public class PremiumUser extends Host implements Premium{
    String creditCardNumber;
    String securityCode;

    public PremiumUser(String email, String name, String userName, String password, String creditCardNumber, String securityCode) {
        super(email, name, userName, password);
        this.creditCardNumber= creditCardNumber;
        this.securityCode= securityCode;
    }

    @Override
    public String toString() {
        return "PremiumUser"+","+ getEmail()+ ","+getName()+","+getUserName()+","+getPassword()+","+creditCardNumber+","+securityCode;

    }

    public String toFileString(){
        return "PremiumUser"+","+ getEmail()+ ","+getName()+","+getUserName()+","+getPassword()+","+creditCardNumber+","+securityCode+",";
    }

    @Override
    public String additionalCapabilites() {
        return null;
    }
    // 11:55 making a unique key

    public double getFee(){
        return 9.99; //constant
    }
}
