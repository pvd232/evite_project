import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.UUID;
// I imported this because my unique ID was not working
// I got this code from ___ website *(do later)

public abstract class Party implements Premium {
    private Host host;
    private List<String> guestList;
    private String eventTitle;
    private int guestLimit;
    private LocalDate dateOfParty;
    private String location;
    private String id;
    private Map<Guest, RSVP> RSVPList;


    // For creating a new Party
    public Party(Host host, List<String> guestList, String eventTitle, int guestLimit, LocalDate dateOfParty, String location) {
        this.host = host;
        this.guestList = guestList;
        this.eventTitle = eventTitle;
        this.guestLimit = guestLimit;
        this.dateOfParty = dateOfParty;
        this.location = location;
        //update Counter to start after this number
        this.id = UUID.randomUUID().toString();
        this.RSVPList = new HashMap<>();
    }

    // putting existing parties into memory
    public Party(Host host, List<String> guestList, String eventTitle, int guestLimit, LocalDate dateOfParty, String location, String id) {
        this.host = host;
        this.guestList = guestList;
        this.eventTitle = eventTitle;
        this.guestLimit = guestLimit;
        this.dateOfParty = dateOfParty;
        this.location = location;
        //update Counter to start after this number
        this.id = id;
        this.RSVPList = new HashMap<>();
    }

    public Party(Host host, String eventTitle, int guestLimit, int day, int month, int year, String location, String id) {
        this(host, new ArrayList<>(), eventTitle, guestLimit, LocalDate.of(year, month, day), location, id);
        // ? no way to override these constructors?
    }

    public Party(Host host, List<String> guestList, String eventTitle, int guestLimit, int day, int month, int year, String location, String id) {
        this(host, guestList, eventTitle, guestLimit, LocalDate.of(year, month, day), location, id);
    }

    public Party(Host host, String eventTitle, int guestLimit, int day, int month, int year, String location) {
        this(host, new ArrayList<>(), eventTitle, guestLimit, LocalDate.of(year, month, day), location);
        // ? no way to override these constructors?
    }

    public Party(Host host, List<String> guestList, String eventTitle, int guestLimit, int day, int month, int year, String location) {
        this(host, guestList, eventTitle, guestLimit, LocalDate.of(year, month, day), location);
    }


    public void setIdNumber(int idNumber) {
        this.id = id;
    }

    public String getId() {
        //System.out.print(id);
        return this.id;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Host getHost() {
        return host;
    }

    public void addToGuestList(String guest) {
        guestList.add(guest);
    }

    public void setGuestList(List<String> guestList) {
        this.guestList = guestList;
    }

    public String getEventTitle() {
        return this.eventTitle;
    }

    public int getGuestLimit() {
        return this.guestLimit;
    }

    public LocalDate getDateOfParty() {
        return this.dateOfParty;
    }

    public String getLocation() {
        return this.location;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public void setDateOfParty(LocalDate dateOfParty) {
        this.dateOfParty = dateOfParty;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getGuestList() {

        return this.guestList;
    }

    public Map<Guest, RSVP> getRSVPList() {
        return this.RSVPList;
    }

    public void setRSVPList(Map<Guest, RSVP> RSVPList) {
        this.RSVPList = RSVPList;
    }

    public void addToRSVPList(Guest userInvited, RSVP userRSVP) {
        this.RSVPList.put(userInvited, userRSVP);
    }

    public String toFileString() {
        return "";
    }

    @Override
    public void showAdditionalInformation(Database database, String partyId) {

    }
}


