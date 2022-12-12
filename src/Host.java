import java.util.ArrayList;
import java.util.List;

public class Host extends Guest {

    // make budget a whole new function later on

    private List<Party> partyList;

    public Host(String email, String name, String userName, String password) {
        super(email, name, userName, password);
        partyList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Host," + getEmail() + "," + getName() + "," + getUserName() + "," + getPassword();
    }

    public String toFileString() {
        return "Host," + getEmail() + "," + getName() + "," + getUserName() + "," + getPassword() + ",";
    }

    @Override
    public void showAdditionalInformation(Database database, String partyId) {
        Party party = database.getPartyByID(partyId);
        List<Guest> guestList = (List<Guest>) party.getRSVPList().keySet();
        List<RSVP> rsvpList = (List<RSVP>) party.getRSVPList().values();
        System.out.println("Please upgrade to PremiumUser to see the full RSVP list for your your parties. Below are the YES RSVPs:");
        for (int i = 0; i < guestList.size(); i++) {
            if (rsvpList.get(i) == RSVP.YES) {
                System.out.println("Guest name: " + guestList.get(i).getName() + " " + "RSVP: " + rsvpList.get(i).toString());

            }
        }
    }


    //host would not extend premium right?


    // add new party
    // get
    // sending invitations = in the evite system


}
