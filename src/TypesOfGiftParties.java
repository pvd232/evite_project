public enum TypesOfGiftParties {
    HALLOWEEN ("Halloween"),
    POTLUCK("Potluck"),
    BYOBPARTY("Byob party"),
    BIRTHDAY("Birthday"), // make a premium event
    WEDDING("Wedding"),
    ANNIVERSARY("Anniversary"),
    CHRISTMAS("Christmas"),
    VALENTINESDAY("Valentines Day");

    // does it make sense to have enums for the different party types
    // wouldn't it makes sense to give them the option to choose these
    // and if this is not on the list then allow them ot make their own
    // so constructor would have two types
    // one with the enums and one without?

    private String description;
    private TypesOfGiftParties(String description){
        this.description= description;
    }

    public static String displayTypesOfGiftParties() {
        String s="";
        for (int i = 0; i < TypesOfGiftParties.values().length; i++) {
            s += (i + ": " + TypesOfGiftParties.values()[i] + "\n");
        }
        return s;
    }

    public static TypesOfGiftParties getTypeOfParty(int num)
    {
        //* make this keep repeating until it chooses the right one.
        if (num < 0 || num>= TypesOfGiftParties.values().length){
            System.out.println("Invalid menu number selected.\n" +
                    "+ \"\\nChoosing last menu item (other).\"");
            num= Menu.values().length-1;
        }
        return TypesOfGiftParties.values()[num];
    }

    public String toString() {
        return this.description;
    }

    public static TypesOfGiftParties match(String description) {
        TypesOfGiftParties s=HALLOWEEN; //default

        for (int i = 0; i < TypesOfGiftParties.values().length; i++) {
//            System.out.print(description+" = "+ TypesOfGiftParties.values()[i].description);
            if(TypesOfGiftParties.values()[i].description.equalsIgnoreCase(description)){
                //System.out.println("found: "+ s);
                s=TypesOfGiftParties.values()[i];
            }
        }
        System.out.print(s);
        return s;
    }



}
