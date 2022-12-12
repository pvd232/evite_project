import java.util.*;

public class PremiumUser extends Host implements Premium {
    String creditCardNumber;
    String securityCode;

    public PremiumUser(String email, String name, String userName, String password, String creditCardNumber, String securityCode) {
        super(email, name, userName, password);
        this.creditCardNumber = creditCardNumber;
        this.securityCode = securityCode;
    }

    @Override
    public String toString() {
        return "PremiumUser" + "," + getEmail() + "," + getName() + "," + getUserName() + "," + getPassword() + "," + creditCardNumber + "," + securityCode;

    }

    public String toFileString() {
        return "PremiumUser" + "," + getEmail() + "," + getName() + "," + getUserName() + "," + getPassword() + "," + creditCardNumber + "," + securityCode + ",";
    }

    @Override
    public void showAdditionalInformation(Database database, String partyId) {
        Party party = database.getPartyByID(partyId);
        List<Guest> guestList = (List<Guest>) party.getRSVPList().keySet();
        List<RSVP> rsvpList = (List<RSVP>) party.getRSVPList().values();
        for (int i = 0; i < guestList.size(); i++) {
            System.out.println("Guest name: " + guestList.get(i).getName() + " " + "RSVP: " + rsvpList.get(i).toString());
        }
    }

    public double getFee() {
        return 9.99; //constant
    }
}
