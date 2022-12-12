import java.time.LocalDate;
import java.util.List;

public class PartyWithoutRequirements extends Party implements Premium {
    private TypesOfRegParties type;


    public PartyWithoutRequirements(Host host, String eventTitle, int guestLimit, int day, int month, int year, String location, String id, TypesOfRegParties type) {
        super(host, eventTitle, guestLimit, day, month, year, location, id);
        this.type = type;
    }

    public PartyWithoutRequirements(Host host, String eventTitle, int guestLimit, int day, int month, int year, String location, TypesOfRegParties type) {
        super(host, eventTitle, guestLimit, day, month, year, location);
        this.type = type;
    }

    @Override
    public String toString() {
        return "RegParty," + getHost().getEmail() + "," + getEventTitle() + "," + getGuestLimit() + "," + getDateOfParty() + "," + getLocation() + "," + getId() + "," + type + ",";
    }

    public String toFileString() {
        return "RegParty," + getHost().getEmail() + "," + getEventTitle() + "," + getGuestLimit() + "," + getDateOfParty() + "," + getLocation() + "," + getId() + "," + type + ",";
    }

    @Override
    public void showAdditionalInformation(Database database, String partyId) {
        System.out.println("Party item for all RSVPs: good attitude");
    }
}



