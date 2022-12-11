import java.time.LocalDate;
import java.util.List;

public class PartyWithRequirementsPremium extends PartyWithRequirements implements Premium{

    // set guest limit to 50 on outline
    // read and write files
    // don't have to combine them together
    // is implementing premium enough


    public PartyWithRequirementsPremium(Host host, String eventTitle, int guestLimit, int day, int month, int year, String location, String id, TypesOfGiftParties type) {
        super(host, eventTitle, guestLimit, day, month, year, location, id, type);
    }

    public PartyWithRequirementsPremium(Host host, List<String> guestList, String eventTitle, int guestLimit, int day, int month, int year, String location, String id, TypesOfGiftParties type) {
        super(host, guestList, eventTitle, guestLimit, day, month, year, location, id, type);
    }

    public PartyWithRequirementsPremium(Host host, String eventTitle, int guestLimit, int day, int month, int year, String location, TypesOfGiftParties type) {
        super(host, eventTitle, guestLimit, day, month, year, location, type);
    }

    public PartyWithRequirementsPremium(Host host, List<String> guestList, String eventTitle, int guestLimit, int day, int month, int year, String location, TypesOfGiftParties type) {
        super(host, guestList, eventTitle, guestLimit, day, month, year, location, type);
    }



    @Override
    public String toString() {
        return getId()+",PartyWithRequirementsPremium," + "," + getType()+ ","+getHost()+ ","+getId()+","+getType()+","+"\nGuestlist: "+getGuestList()+ "\n RSVP List : " +getRSVPList()+",";
    }
    public String toFileString() {
        return "PartyWithRequirementsPremium," + getHost().getEmail()+ "," +  getEventTitle()+ "," + getGuestLimit()+ "," + getDateOfParty()+ "," +getLocation()+ "," + getId()+","+getType()+",";
    }

    @Override
    public String additionalCapabilites() {
        return null;
    }

    public double getFee(){
        if( this.getHost() instanceof Premium){
            return 0;
        }
        else{
            return 9.99;
        }
    }
}
