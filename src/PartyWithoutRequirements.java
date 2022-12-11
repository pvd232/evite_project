import java.time.LocalDate;
import java.util.List;

public class PartyWithoutRequirements extends Party {
    private TypesOfRegParties type;


    public PartyWithoutRequirements(Host host, String eventTitle, int guestLimit, int day, int month, int year, String location, String id, TypesOfRegParties type) {
        super(host, eventTitle, guestLimit, day, month, year, location, id);
        this.type = type;
    }

    public PartyWithoutRequirements(Host host, List<String> guestList, String eventTitle, int guestLimit, int day, int month, int year, String location, String id, TypesOfRegParties type) {
        super(host, guestList, eventTitle, guestLimit, LocalDate.of(year, month, day), location, id);
        this.type = type;
    }

    public PartyWithoutRequirements(Host host, String eventTitle, int guestLimit, int day, int month, int year, String location, TypesOfRegParties type) {
        super(host, eventTitle, guestLimit, day, month, year, location);
        this.type = type;
    }

    public PartyWithoutRequirements(Host host, List<String> guestList, String eventTitle, int guestLimit, int day, int month, int year, String location, TypesOfRegParties type) {
        super(host, guestList, eventTitle, guestLimit, LocalDate.of(year, month, day), location);
        this.type = type;
    }


    public void setType(TypesOfRegParties type) {
        this.type = type;
    }

    public TypesOfRegParties getType() {
        return type;
    }

    @Override
    public String toString() {
        return "RegParty," + getHost().getEmail() + "," + getEventTitle() + "," + getGuestLimit() + "," + getDateOfParty() + "," + getLocation() + "," + getId() + "," + type + ",";
    }

    public String toFileString() {
        return "RegParty," + getHost().getEmail() + "," + getEventTitle() + "," + getGuestLimit() + "," + getDateOfParty() + "," + getLocation() + "," + getId() + "," + type + ",";
    }

}



