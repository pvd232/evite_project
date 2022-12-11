import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PartyWithRequirements extends Party {

    private TypesOfGiftParties type;

    //based on type or something -- registryList...
    private String partyItem;
    private List<PartyRSVP> partyRSVPList = new ArrayList();

    public PartyWithRequirements(Host host, String eventTitle, int guestLimit, int day, int month, int year, String location, String id, TypesOfGiftParties type) {
        super(host, eventTitle, guestLimit, day, month, year, location, id);
        // ? no way to override these constructors?
        this.type = type;
        switch (type) {
            case HALLOWEEN -> this.partyItem = "Costume";
            case POTLUCK -> this.partyItem = "Meal";
            case WEDDING -> this.partyItem = "Wedding Gift";
            case BIRTHDAY -> this.partyItem = "Birthday Present";
            case BYOBPARTY -> this.partyItem = "Beer";
            case CHRISTMAS -> this.partyItem = "Secret Santa";
            case ANNIVERSARY -> this.partyItem = "Bottle of Wine";
            case VALENTINESDAY -> this.partyItem = "Valentine";
        }
    }

    public PartyWithRequirements(Host host, List<String> guestList, String eventTitle, int guestLimit, int day, int month, int year, String location, String id, TypesOfGiftParties type) {
        super(host, guestList, eventTitle, guestLimit, LocalDate.of(year, month, day), location, id);
        this.type = type;
        switch (type) {
            case HALLOWEEN -> this.partyItem = "Costume";
            case POTLUCK -> this.partyItem = "Meal";
            case WEDDING -> this.partyItem = "Wedding Gift";
            case BIRTHDAY -> this.partyItem = "Birthday Present";
            case BYOBPARTY -> this.partyItem = "Beer";
            case CHRISTMAS -> this.partyItem = "Secret Santa";
            case ANNIVERSARY -> this.partyItem = "Bottle of Wine";
            case VALENTINESDAY -> this.partyItem = "Valentine";
        }
    }

    public PartyWithRequirements(Host host, String eventTitle, int guestLimit, int day, int month, int year, String location, TypesOfGiftParties type) {
        super(host, eventTitle, guestLimit, day, month, year, location);
        this.type = type;
        switch (type) {
            case HALLOWEEN -> this.partyItem = "Costume";
            case POTLUCK -> this.partyItem = "Meal";
            case WEDDING -> this.partyItem = "Wedding Gift";
            case BIRTHDAY -> this.partyItem = "Birthday Present";
            case BYOBPARTY -> this.partyItem = "Beer";
            case CHRISTMAS -> this.partyItem = "Secret Santa";
            case ANNIVERSARY -> this.partyItem = "Bottle of Wine";
            case VALENTINESDAY -> this.partyItem = "Valentine";
        }
    }

    public PartyWithRequirements(Host host, List<String> guestList, String eventTitle, int guestLimit, int day, int month, int year, String location, TypesOfGiftParties type) {
        super(host, guestList, eventTitle, guestLimit, LocalDate.of(year, month, day), location);
        this.type = type;
        switch (type) {
            case HALLOWEEN -> this.partyItem = "Costume";
            case POTLUCK -> this.partyItem = "Meal";
            case WEDDING -> this.partyItem = "Wedding Gift";
            case BIRTHDAY -> this.partyItem = "Birthday Present";
            case BYOBPARTY -> this.partyItem = "Beer";
            case CHRISTMAS -> this.partyItem = "Secret Santa";
            case ANNIVERSARY -> this.partyItem = "Bottle of Wine";
            case VALENTINESDAY -> this.partyItem = "Valentine";
        }
    }

    public void setType(TypesOfGiftParties type) {
        this.type = type;
    }

    public TypesOfGiftParties getType() {
        return type;
    }

    public String getPartyItem() {
        return this.partyItem;
    }

    public List<PartyRSVP> getPartyRSVPList() {
        return this.partyRSVPList;
    }

    public void addToPartyRSVPList(PartyRSVP partyRSVP) {
        this.partyRSVPList.add(partyRSVP);
    }

    @Override
    public String toString() {
        return "GiftParty," + getHost().getEmail() + "," + getEventTitle() + "," + getGuestLimit() + "," + getDateOfParty() + "," + getLocation() + "," + getId() + "," + type + ",";
    }

    public String toFileString() {
        return "GiftParty," + getHost().getEmail() + "," + getEventTitle() + "," + getGuestLimit() + "," + getDateOfParty() + "," + getLocation() + "," + getId() + "," + type + ",";
    }
}


