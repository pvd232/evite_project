public enum RSVP {
    YES("Yes"),
    NO("No"),
    MAYBE("Maybe"),
    UNKNOWN("Unknown");


    private String description;

    private RSVP(String description) {
        this.description = description;
    }


    public static String displayRSVP() {
        String s = "";
        for (int i = 0; i < RSVP.values().length - 1; i++) {
            s += (i + 1 + ": " + RSVP.values()[i] + "\n");
        }
        return s;
    }

    public static RSVP getTypeOfRSVP(int num) {
        //* make this keep repeating until it chooses the right one.
        if (num < 1 || num >= RSVP.values().length) {
            System.out.println("Invalid menu number selected.\n" +
                    "+ \"\\nChoosing last menu item (other).\"");
            num = RSVP.values().length - 1;
        }
        return RSVP.values()[num - 1];
    }


    public static int getLastIndex1() {
        return RSVP.values().length - 1;
    }

    public static RSVP match(String description) {
        RSVP rsvp = UNKNOWN; //default

        for (int i = 0; i < RSVP.values().length; i++) {
            if (RSVP.values()[i].description.equalsIgnoreCase(description)) {
                //System.out.println("found: "+ s);
                rsvp = RSVP.values()[i];
            }
        }
        System.out.print(rsvp);
        return rsvp;
    }

    public String toString() {
        return this.description;
    }

    public String toFileString() {
        return this.description;
    }


    // PROBABLY make another enum type
    // have maybe be the standard until the users login and choose one
}
