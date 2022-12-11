public enum TypesOfRegParties {

    COLLEGEPARTY("College Party"),
    HIGHSCHOOLPARTY("Highschool Party"),
    GRADSCHOOLPARTY("Gradschool Party"),
    AFTERPARTY("After Party"),
    INDEPENDENCEDAY("Independence Day"),
    CINCODEMAYO("Cinco De Mayo"),
    TEAPARTY("Tea Party"),
    VIEWINGPARTY("Viewing Party"),
    GAMENIGHT("Game Night"),
    BACHELORPARTY("Bachelor Party"),
    BACHELORETTEPARTY("Bachelorette Party"),
    OTHER("Other");

    private String description;

    private TypesOfRegParties(String description){
        this.description= description;
    }

    public static String displayTypesOfRegParties() {
        String s = "";
        for (int i = 0; i < TypesOfRegParties.values().length; i++) {
            s += (i + ": " + TypesOfRegParties.values()[i]+"\n");
        }
        return s;
    }

    public static TypesOfRegParties getTypeOfParty(int num)
    {
        //* make this keep repeating until it chooses the right one.
        if (num < 0 || num>= TypesOfRegParties.values().length){
            System.out.println("Invalid menu number selected.\n" +
                    "+ \"\\nChoosing last menu item (other).\"");
            num= Menu.values().length-1;
        }
        return TypesOfRegParties.values()[num];
    }
    public static TypesOfRegParties match(String description) {
        TypesOfRegParties s=INDEPENDENCEDAY; //default

        for (int i = 0; i < TypesOfRegParties.values().length; i++) {
//            System.out.print(description+" = "+ TypesOfRegParties.values()[i].description);
            if(TypesOfRegParties.values()[i].description.equalsIgnoreCase(description)){
                s=TypesOfRegParties.values()[i];
                //System.out.println("found: "+ s);
            }
        }
        return s;
    }

    public String toString() {
        return this.description;
    }


}
