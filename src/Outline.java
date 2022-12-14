import java.util.List;

public class Outline {

    // map of all users / parties*
    private final Savior helper;
    private final Database database;
    private final FileWriter FileWriter;
    private final FileReader FileReader;
    private User currentUser;


    public Outline() {
        // Command shift f
        helper = new Savior();
        database = new Database();
        FileWriter = new FileWriter();
        FileReader = new FileReader();

        currentUser = null;
    }


    // I got this code from Assignment 7 in my AccomplishmentProgram file
    public void run() {
        boolean done = false;
        FileReader.initializeUsers(database);
        FileReader.initializeParties(database);
        FileReader.initializeGuestList(database);
        FileReader.initializeRSVPList(database);
        FileReader.initializePartyRSVPList(database);

        displayWelcome();
        do {
            String menu = Menu.displayMenu();
            System.out.println(menu);
            int num = helper.inputInt(">", 0, Menu.getLastIndex1());
            Menu item = Menu.getMenusItem(num);

            switch (item) {
                case CREATE_ACCOUNT -> {
                    //userAccounts+=1;
                    if (currentUser == null) {
                        createAnAccount();
                    } else {
                        System.out.println("You are already logged in. Please choose again");
                    }
                }
                case LOGIN -> {
                    if (currentUser == null) {
                        login();
                    } else {
                        System.out.println("You have already logged in. If you want to logIn to another account, please log out.");
                    }
                }
                case UPGRADEACCOUNT -> {
                    if (currentUser != null) {
                        if (currentUser instanceof PremiumUser) {
                            System.out.println("No more upgrades are available");
                        } else {
                            System.out.println("Hello " + ((Guest) currentUser).getName() + " lets upgrade your account!!!!!");
                            currentUser = upgradeAccount(currentUser);
                        }
                    } else {
                        System.out.println("Please create an account or login to upgrade your account.");
                    }
                }
                case PARTY -> {
                    if (currentUser instanceof Host) {
                        createAParty();
                    } else {
                        System.out.println("Sorry! you can only create a party if you are a host or premium user. Please login or upgrade your account if you want to host a party.");
                    }
                }
                case SENDINVITATIONS -> {
                    if (currentUser != null && checkIfUserHasParty()) {
                        sendInvitations();
                    } else {
                        System.out.println("You must have created a party to send invitations. Please try again later.");
                    }
                }
                case SEEPARTYITEMS -> {
                    if (currentUser != null) {
                        showInvitationPartyItems();
                    } else {
                        System.out.println("You must have logged in to see invitations. Please try again later.");
                    }
                }
                case RSVP -> {
                    if ((currentUser instanceof Guest)) {
                        sendRSVPs((Guest) currentUser);
                    } else {
                        System.out.println("Please create an account or login to RSVP.");
                    }
                }
                case LOGOUT -> {
                    logout();
                    System.out.println("You have been successfully logged out");
                }
                case QUIT -> {
                    FileWriter.saveAllUsers(database);
                    FileWriter.saveAllParties(database);
                    FileWriter.saveAllInvitations(database);
                    FileWriter.saveAllRSVPS(database);
                    FileWriter.saveAllPartyRSVPS(database);
                    done = true;
                }
                default -> System.out.println(item + " option not yet available");
            }
        } while (!done);
        System.out.println("Goodbye.");
    }

    // I got this code from Assignment 7 in my AccomplishmentProgram file
    public void displayWelcome() {
        System.out.print("WELCOME TO EVITE. We are excited to help you attend or plan a party. Please select one of the menu items below");
    }

    public void createAParty() {
        System.out.println("Lets create a party!");
        String eventTitle = helper.inputLine("Event title name: ");
        int guestLimit = 8;
        // each question should be its own method
        int year = helper.inputInt("Year: ");
        int month = helper.inputInt("Month: ");
        int day = helper.inputInt(("Calendar day: "));
        String location = helper.inputLine("Location: ");
        boolean requirements = helper.inputYesNo("Will guests need to wear a costume or bring anything?");

        if (!(currentUser instanceof PremiumUser)) {
            boolean premium = helper.inputYesNo("Do you want to be a premium User for only $10.00 a month? (guest limits will increase from 8 to 15): ");
            if (premium) {
                upgradeAccount(currentUser);
            }
        } else {
            guestLimit = 60;
        }
        if (requirements) {
            System.out.println("Here are the following parties types to choose from:  ");
            System.out.print(TypesOfGiftParties.displayTypesOfGiftParties());
            // Making an enum list for premium users
            int choice = helper.inputInt("> ");
            TypesOfGiftParties type = TypesOfGiftParties.getTypeOfParty(choice);
            database.createPartyWithRequirements((Host) currentUser, eventTitle, guestLimit, day, month, year, location, type);
        } else {
            System.out.println("Here are the following parties to choose from:  ");
            System.out.print(TypesOfRegParties.displayTypesOfRegParties());
            // Making an enum list for premium users
            int choice = helper.inputInt("> ");
            TypesOfRegParties type = TypesOfRegParties.getTypeOfParty(choice);
            database.createRegParty((Host) currentUser, eventTitle, guestLimit, day, month, year, location, type);

        }
    }

    public void createAnAccount() {

        System.out.println("Lets create an account!");
        String typeOfAccount = helper.inputWord("Type of account: (Guest or Host)", "guest", "host").toLowerCase();
        String email = helper.inputWord("Email:");
        String name = helper.inputLine(("Name:"));
        String userName = helper.inputWord("Username: ");
        String password = helper.inputWord("Password: ");
        // user, guest, host, premium guest, premium host
        if (typeOfAccount.equalsIgnoreCase("guest")) {
            System.out.println("no");
            currentUser = database.createUser("Guest", email, name, userName, password);
        } else {
            System.out.println("yes");

            currentUser = database.createUser("Host", email, name, userName, password);
        }
        boolean premium = helper.inputYesNo("Do you want to be a premium User for only $10.00 a month? (Extra functions are included): "); // explain what this is
        if (premium) {
            String creditCardNumber = helper.inputLine("Enter credit card information");
            String securityCode = helper.inputLine("Enter security code number: ");
            currentUser = database.createUser(email, name, userName, password, creditCardNumber, securityCode);
        }
        System.out.println("Your account has been created, and you are now logged in.");
    }


    public void login() {
        String email = helper.inputWord("Please enter your email: ");
        String password = helper.inputWord("Please enter your password: ");
        currentUser = database.authenticateUser(email, password);
        if (currentUser != null) {
            System.out.println("We found your account! You are now logged in.");
        } else {
            System.out.println("We could not find your account. Please try again.");
        }
    }

    public void logout() {
        currentUser = null;
    }


    public static void main(String[] args) {
        Outline todosLosCosas = new Outline();
        todosLosCosas.run();
    }

    public User upgradeAccount(User user) {
        boolean upgraded = false;
        //? would it make sense to have these as independent if statements?
        if (!(user instanceof Host)) {
            String typeOfAccount = helper.inputWord("What type of account do you want (\"Host\" or \"Premium\"): ", "host", "premium").toLowerCase();
            if (typeOfAccount.equalsIgnoreCase("Host")) {
                user = database.createUser("Host", user.getEmail(), ((Guest) user).getName(), ((Guest) user).getUserName(), ((Guest) user).getPassword());
                upgraded = true;
            } else {
                user = premium();
            }
        } else {
            user = premium();
            //FileWriter.saveAll("users.txt", database);

        }
        if (upgraded) {
            System.out.println("Your account has been upgraded!!!!!");
        }
        return user;


    }

    public User premium() {
        boolean premium = helper.inputYesNo("Are you sure you want to be a premium User for only $10.00 a month? (guest limits will increase from 8 to 15 ): ");
        if (premium) {
            String creditCardNumber = helper.inputLine("Enter credit card information");
            String securityCode = helper.inputLine("Enter security code number: ");
            return (currentUser = database.createUser(currentUser.getEmail(), ((Guest) currentUser).getName(), ((Guest) currentUser).getUserName(), ((Guest) currentUser).getPassword(), creditCardNumber, securityCode));
        } else {
            return null;
        }
    }

    public void sendInvitations() {
        boolean contin = true;
        for (Party parties : database.getAllParties()) {
            int count = parties.getGuestList().size();
            if (parties.getHost().getEmail().equalsIgnoreCase(currentUser.getEmail())) {
                while (parties.getGuestLimit() >= count && contin) {
                    String email = helper.inputWord("Enter the guest's email address that you want to invite to the \"" + parties.getEventTitle() + "\" party");
                    parties.addToGuestList(email);
                    count++;
                    contin = helper.inputYesNo("Do you want to add another guest?");
                }
                contin = true;
            }

        }

    }


    public boolean checkIfUserHasParty() {
        boolean securityCheck = false;
        for (Party parties : database.getAllParties()) {
            System.out.println("parties.getHost().getEmail() = " + parties.getHost().getEmail());
            System.out.println("currentUser.getEmail() = " + currentUser.getEmail());
            if (parties.getHost().getEmail().equalsIgnoreCase(currentUser.getEmail())) {
                securityCheck = true;
            }
        }
        return securityCheck;
    }

    public void showInvitationPartyItems() {
        if (currentUser == null) {
            currentUser = new User(helper.inputLine("Enter your email: "));
        }
        int count = 0;
        System.out.println("Party Items");
        List<Party> relevantParties;
        if (currentUser instanceof Host) {
            relevantParties = database.getHostParties(currentUser.getEmail());
            System.out.println("relevantParties = " + relevantParties);
            if (!(relevantParties.isEmpty())) {
                System.out.println("You are hosting the following parties. Please select a party to view its party items.");
                for (Party party : relevantParties) {
                    System.out.println(count + ":\" " + party.getEventTitle());
                    count += 1;

                }
                int num = helper.inputInt(">", 0, relevantParties.size());
                relevantParties.get(num).showAdditionalInformation(database, relevantParties.get(num).getId());
            } else {
                System.out.println("You have no invitations at the moment. Please check again soon.");
            }
        } else {
            relevantParties = database.getUserParties(currentUser.getEmail());
            if (!(relevantParties.isEmpty())) {
                System.out.println("You have been invited to the following parties. Select a number to view the RSVP list.");
                for (Party party : relevantParties) {
                    System.out.println(count + ":\" " + party.getEventTitle() + "\"" + " Hosted by " + party.getHost().getName());
                    count += 1;
                }
                int num = helper.inputInt(">", 0, relevantParties.size());
                relevantParties.get(num).showAdditionalInformation(database, relevantParties.get(num).getId());
            } else {
                System.out.println("You have no invitations at the moment. Please check again soon.");
            }


        }

    }

    public void sendRSVPs(Guest guest) {
        List<Party> partiesInvitedTo = database.getUserParties(guest.getEmail());
        if (partiesInvitedTo.isEmpty()) {
            System.out.println("You can not RSVP to any parties because you were not invited to any yet. Please check again soon.");
        } else {
            for (Party partyRSVPingTo : partiesInvitedTo) {
                String RSVPMenu = RSVP.displayRSVP();
                System.out.println("Will you attend \"" + partyRSVPingTo.getEventTitle() + "\" party?");
                System.out.println(RSVPMenu);
                int num = helper.inputInt(">", 1, RSVP.getLastIndex1());
                RSVP chosenRSVP = RSVP.getTypeOfRSVP(num);
                partyRSVPingTo.addToRSVPList(guest, chosenRSVP);

                if (partyRSVPingTo instanceof PartyWithRequirements partyWithRequirements) {
                    String partyItem = helper.inputWord("What " + partyWithRequirements.getPartyItem() + " are you bringing?");
                    PartyRSVP userPartyRSVP = new PartyRSVP(chosenRSVP, partyItem, guest.getEmail(), partyWithRequirements.getId());
                    partyWithRequirements.addToPartyRSVPList(userPartyRSVP);
                }
            }
        }

    }


}
