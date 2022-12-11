import java.util.UUID;

public class PartyRSVP {
    private RSVP rsvp;
    private String partyItem;
    private String guestEmail;
    private String partyId;

    public RSVP getRsvp() {
        return rsvp;
    }

    public String getPartyId() {
        return partyId;
    }

    public String getPartyItem() {
        return partyItem;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public PartyRSVP(RSVP rsvpParam, String partyItemParam, String guestEmail, String partyId) {
        this.rsvp = rsvpParam;
        this.partyItem = partyItemParam;
        this.guestEmail = guestEmail;
        this.partyId = partyId;
    }
}
