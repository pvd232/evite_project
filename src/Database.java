import java.util.*;

public class Database {
    private final Map<String, User> allUsers;
    private final Map<String, Party> allParties;
    private final Map<String, RSVP> allRSVPs;

    public Database() {
        allUsers = new HashMap<>();
        allParties = new HashMap<>();
        allRSVPs = new HashMap<>();
    }

    public List<User> getAllUsers() {
        List<User> getAllUsers;
        getAllUsers = List.copyOf(allUsers.values());
        // I found this online at https://javahungry.blogspot.com/2022/06/collection-to-list.html
        return getAllUsers;
    }

    public List<Party> getAllParties() {
        return List.copyOf(allParties.values());
    }

    public void addNewUser(User u) {
        allUsers.put(u.getEmail(), u);

    }


    public void addNewParty(Party newParty) {
        allParties.put(newParty.getId(), newParty);
    }

    public void createRegParty(Host host, String eventTitle, int guestLimit, int day, int month, int year, String location, String id, TypesOfRegParties type) {
        Party party = new PartyWithoutRequirements(host, eventTitle, guestLimit, day, month, year, location, id, type);
        addNewParty(party);
    }

    public void createRegParty(Host host, String eventTitle, int guestLimit, int day, int month, int year, String location, TypesOfRegParties type) {
        Party newParty = new PartyWithoutRequirements(host, eventTitle, guestLimit, day, month, year, location, type);
        addNewParty(newParty);
    }


    public void createPartyWithRequirements(String typeOfParty, Host host, String eventTitle, int guestLimit, int day, int month, int year, String location, String id, TypesOfGiftParties type) {
        Party newParty;
        newParty = new PartyWithRequirements(host, eventTitle, guestLimit, day, month, year, location, id, type);
        addNewParty(newParty);
    }

    public void createPartyWithRequirements(Host host, String eventTitle, int guestLimit, int day, int month, int year, String location, TypesOfGiftParties type) {
        PartyWithRequirements newParty = new PartyWithRequirements(host, eventTitle, guestLimit, day, month, year, location, type);
        addNewParty(newParty);
    }


    public User createUser(String email, String name, String userName, String password, String creditCardNumber, String securityCode) {
        User newUser = new PremiumUser(email, name, userName, password, creditCardNumber, securityCode);
        addNewUser(newUser);

        return newUser;
    }

    public User createUser(String type, String email, String name, String userName, String password) {
        User newUser;
        if (type.equalsIgnoreCase("Guest")) {
            newUser = new Guest(email, name, userName, password);
            addNewUser(newUser);
        } else {
            newUser = new Host(email, name, userName, password);
            addNewUser(newUser);
        }
        return newUser;
    }

    public User getUserByEmail(String email) {
        for (String userEmail : allUsers.keySet()) {
            if (userEmail.equals(email)) {
                return allUsers.get(userEmail);
            }
        }
        return null;
    }

    public User authenticateUser(String email, String password) {
        User userToAuthenticate;
        for (String userEmail : allUsers.keySet()) {
            if (userEmail.equals(email)) {
                userToAuthenticate = allUsers.get(userEmail);
                if (userToAuthenticate instanceof Guest guestUserToAuthenticate) {
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
        for (String partyId : allParties.keySet()) {
            if (partyId.equalsIgnoreCase(id)) {
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

    public List<Party> getHostParties(String userEmail) {
        List<Party> userParties = new ArrayList<>();
        for (Party party : allParties.values()) {
            if (party.getHost().getEmail() == userEmail) {
                userParties.add(party);
            }
        }
        return userParties;
    }
}

