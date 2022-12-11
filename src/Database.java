import java.util.*;

public class Database {
    private Map<String, User> allUsers;
    private Map<String, Party> allParties;


    // get user by email()
    // get user by partyId() *need to create


    public Database() {
        allUsers = new HashMap<>();
        allParties = new HashMap<>();
    }

    /*
    public Map<String,List<String>> getGuestListMap(){
        return guestListMap;
    }

    public void setGuestListMap(String partyID, List<String> guestsInvited) {
        guestListMap.put(partyID,guestsInvited);
    }

     */
//$
    public List<User> getAllUsers() {
        List<User> getAllUsers = new ArrayList<>();
        getAllUsers = List.copyOf(allUsers.values());

        // I found this online at https://javahungry.blogspot.com/2022/06/collection-to-list.html
        return getAllUsers;
    }

    public List<Party> getAllParties() {
        List<Party> getAllparties = List.copyOf(allParties.values());
//        System.out.println(getAllParties);
//        System.out.println(allParties);
        // I found this online at https://javahungry.blogspot.com/2022/06/collection-to-list.html
        return getAllparties;
    }

    public void addNewUser(User u) {
        //System.out.println("HELLLLLLOOOOOOO3");
        allUsers.put(u.getEmail(), u);

    }


    public void addNewParty(Party u) {
//        System.out.println(u.getId());
//        System.out.println("\n");
//        System.out.println("Before: (addNewParty) "+allParties.values());
//        System.out.println("\n");
        allParties.put(u.getId(), u);
//        System.out.println("After: (addNewParty) "+allParties.values());


        //System.out.print("Sucess4");
    }

    public Party createRegParty(Host host, String eventTitle, int guestLimit, int day, int month, int year, String location, String id, TypesOfRegParties type) {
        Party party = new PartyWithoutRequirements(host, eventTitle, guestLimit, day, month, year, location, id, type);
        addNewParty(party);
//        System.out.println("party47: "+party);
        return party;
    }

    public Party createRegParty(Host host, String eventTitle, int guestLimit, int day, int month, int year, String location, TypesOfRegParties type) {
        Party f = new PartyWithoutRequirements(host, eventTitle, guestLimit, day, month, year, location, type);
        addNewParty(f);
//        System.out.println("party54: "+f);
        return f;
    }


    public Party createPartyWithRequirements(String typeOfParty, Host host, String eventTitle, int guestLimit, int day, int month, int year, String location, String id, TypesOfGiftParties type) {
        Party f = null;
        if (typeOfParty.equalsIgnoreCase("PartyWithRequirementsPremium")) {
            f = new PartyWithRequirementsPremium(host, eventTitle, guestLimit, day, month, year, location, id, type);
        } else {
            f = new PartyWithRequirements(host, eventTitle, guestLimit, day, month, year, location, id, type);
        }

        addNewParty(f);
        return f;
    }

    public Party createPartyWithRequirements(Host host, String eventTitle, int guestLimit, int day, int month, int year, String location, TypesOfGiftParties type) {
        Party f = null;
        if (guestLimit > 10) {
            f = new PartyWithRequirementsPremium(host, eventTitle, guestLimit, day, month, year, location, type);
        } else {
            f = new PartyWithRequirements(host, eventTitle, guestLimit, day, month, year, location, type);
        }

        addNewParty(f);
        //System.out.println("party95: "+f);
        return f;
    }


    public User createUser(String email) {
        User u = new User(email);
        addNewUser(u);
        // System.out.print("Premium account sucess");
        return u;
    }

    public User createUser(String email, String name, String userName, String password, String creditCardNumber, String securityCode) {
        User u = new PremiumUser(email, name, userName, password, creditCardNumber, securityCode);
        addNewUser(u);
        // System.out.print("Premium account sucess");
        return u;
    }

    public User createUser(String type, String email, String name, String userName, String password) {
        User u = null;
        if (type.equalsIgnoreCase("Guest")) {
            u = new Guest(email, name, userName, password);
            addNewUser(u);
        } else {
            u = new Host(email, name, userName, password);
            addNewUser(u);
        }
        return u;
    }

    public User getUserByEmail(String email) {
        boolean found = false;
        User emailf = null;
        for (String a : allUsers.keySet()) {
            //System.out.println(a);
            // this makes it so that its not case sensitive
            if (a.equals(email)) {
                //System.out.print("found user " + a);
                found = true;
                emailf = allUsers.get(a);
            }
        }
        if (found) {
            return emailf;

        } else {
            return null;
        }
    }

    public User authenticateUser(String email, String password) {
        User userToAuthenticate = null;
        for (String a : allUsers.keySet()) {
            if (a.equals(email)) {
                userToAuthenticate = allUsers.get(a);
                if (userToAuthenticate instanceof Guest) {
                    Guest guestUserToAuthenticate = (Guest) userToAuthenticate;
                    if (
                            guestUserToAuthenticate.checkPassword(password)
                    ) {
                        return guestUserToAuthenticate;
                    }

                }
            }
        }
        return null;
    }

    public Party getPartyByID(String id) {
        System.out.println("id we are looking for: " + id);
        for (String partyId : allParties.keySet()) {
            System.out.println("partyId: " + partyId);

            // this makes it so that its not case sensitive
            if (partyId.equalsIgnoreCase(id)) {
                System.out.print("Party id =" + partyId + "String Id " + id);
                //System.out.print("found user " + a);

                return allParties.get(partyId);
            }
        }
        return null;
    }


    public List<Party> getUserParties(String userEmail) {
        List<Party> userParties = new ArrayList<>();
        for (Party party : allParties.values()) {
            List<String> guestList = party.getGuestList();
            for (String partyEmail : guestList) {
                if (partyEmail.equalsIgnoreCase(userEmail)) {
                    userParties.add(party);
                }
            }
        }
        return userParties;
    }

}

