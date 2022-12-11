import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileWriter {


    public FileWriter() {
    }

    public void saveAllUsers(Database database) {
        //writing objects to a file
        PrintWriter out = null;
        // System.out.println("filename:" +fileName);
        try {

            if (database != null) {
                //System.out.print("database != null");
                out = new PrintWriter("users.txt");
                int count = 0;
                for (User user : database.getAllUsers()) {
                    //Why does it only add the emails of the user?
                    if (count == database.getAllUsers().size() - 1) {
                        out.print(user.toFileString());
                    } else {
                        out.println(user.toFileString());
                    }
                    count++;

                    //System.out.println(user);
                }
                out.close();


            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAllParties(Database database) {
        //writing objects to a file
        PrintWriter out = null;
        // System.out.println("filename:" +fileName);
        try {

            if (database != null) {
                //System.out.print("database != null");
                out = new PrintWriter("parties.txt");

                //System.out.print("we got to parties1.txt");
                int count = 0;
                for (Party party : database.getAllParties()) {
                    //System.out.print("Party (in fileWriter): "+party);
                    //Why does it only add the emails of the user?
                    if (count == database.getAllParties().size() - 1) {
                        out.print(party.toFileString());
                    } else {
                        out.println(party.toFileString());
                        //System.out.print("Party (in fileWriter): "+party);
                    }
                    count++;
                    //System.out.print(count);
                    //System.out.println(party);
                    //System.out.println(user);
                }
                out.close();

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAllInvitations(Database database) {
        //writing objects to a file
        PrintWriter out = null;
        // System.out.println("filename:" +fileName);
        try {
            if (database != null) {
                //System.out.print("database != null");
                out = new PrintWriter("invitations.txt");
                for (Party party : database.getAllParties()) {
                    if (!(party.getGuestList().isEmpty())) {
                        out.print(party.getId() + ",");
                        for (String guestInvited : party.getGuestList()) {
                            out.print(guestInvited + ",");
                        }
                        out.println();
                    }
                }
            }
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void saveAllRSVPS(Database database) {
        //writing objects to a file
        PrintWriter out = null;
        // System.out.println("filename:" +fileName);
        try {
            if (database != null) {
                //System.out.print("database != null");
                out = new PrintWriter("rsvp.txt");
                //System.out.print("made it in");

                for (Party party : database.getAllParties()) {
                    if (!(party.getRSVPList().isEmpty())) {
                        List<Guest> RSVPGuests = new ArrayList<>(party.getRSVPList().keySet());
                        List<RSVP> RSVPValues = new ArrayList<>(party.getRSVPList().values());
                        for (int i = 0; i < RSVPGuests.size(); i++) {
                            Guest RSVPGuest = RSVPGuests.get(i);
                            RSVP RSVPValue = RSVPValues.get(i);
                            String toPrint = (party.getId() + "," + RSVPGuest.getEmail() + "," + RSVPValue.toString() + ",");
                            out.println(toPrint);
                        }
                    }

                }
                // is this fine?
            }
            out.close();

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void saveAllPartyRSVPS(Database database) {
        //writing objects to a file
        PrintWriter out = null;
        try {
            if (database != null) {
                out = new PrintWriter("party_rsvp.txt");
                for (Party party : database.getAllParties()) {
                    if (party instanceof PartyWithRequirements) {
                        PartyWithRequirements partyWithRequirements = (PartyWithRequirements) party;
                        if (!partyWithRequirements.getPartyRSVPList().isEmpty()) {
                            for (PartyRSVP partyRSVP : partyWithRequirements.getPartyRSVPList()) {
                                String partyRSVPString = partyRSVP.getGuestEmail() + "," + partyRSVP.getRsvp() + "," + partyRSVP.getPartyId() + "," + partyRSVP.getPartyItem();
                                out.println(partyRSVPString);
                            }
                        }
                    }
                }
                // is this fine?
            }
            out.close();

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }
}




