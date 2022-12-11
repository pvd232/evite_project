

public enum Menu {
    CREATE_ACCOUNT("Create an Account"),
    LOGIN("Login"),
    UPGRADEACCOUNT("Upgrade Account"),
    PARTY("Make your own party!"),
    //CREATE_AN_INVITE_CARD("Create an Invitation Card"),
    SENDINVITATIONS("Create Guest List"),
    SEEINVITATIONS("See Your invitations and RSVP Lists for Those Parties"),
    RSVP("RSVP To Parties"),//put all guests as a maybe

    LOGOUT("Logout"),
    QUIT("Quit");
    private String description;
    //private Savior bob;

    private Menu(String description) {
        this.description = description;
    }

    // I got this code from Assignment 7
    public static String displayMenu() {
        String s = "*****   Menu   *****\n";
        for (int i = 0; i < Menu.values().length; i++) {
            s += (i + ": " + Menu.values()[i] + "\n");
        }
        for (int i = 0; i < 50; i++) {
            s += ("*");
        }
        s += "\n";

        return s;
    }

    // I got this code from Assignment 7
    public static Menu getMenusItem(int num) {

        if (num < 0 || num >= Menu.values().length) {
            System.out.println("\"Invalid menu number passed in to SongMenu's get Menu Item\"\n" +
                    "+ \"\\nChoosing last menu item (quit) so program will restart.\"");
            num = Menu.values().length - 1;
        }
        return Menu.values()[num];
    }

    // I got this code from Assignment 7
    public static int getLastIndex1() {
        return Menu.values().length - 1;
    }

    public String toString() {
        return this.description;
    }
}


