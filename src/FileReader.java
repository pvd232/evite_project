import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class FileReader {

    //private Savior helper;
    private FileWriter fileWriter;
    private Outline outline;


    public FileReader() {
        //this.helper = new Savior();
        fileWriter = new FileWriter();
    }

    public void initializeParties(Database database) {
        try (FileInputStream fin = new FileInputStream("parties.txt")) {
            try (Scanner scannedFile = new Scanner(fin)) {
                //  GiftParty,dvdrisco@usc.edu,beb,8,0000-04-02,bye,Birthday,beb1111,
                while (scannedFile.hasNext() && scannedFile.hasNextLine()) {
                    //String testNextLine = sc.nextLine();
                    //System.out.println("Next line: " + sc.nextLine());
                    //  GiftParty,dvdrisco@usc.edu,beb,8,0000-04-02,bye,Birthday,beb1111,

                    Scanner sc = new Scanner(scannedFile.nextLine());
                    sc.useDelimiter(",");
                    String typeOfParty = sc.next().trim().strip();
//                        System.out.println("Type of party: " + typeOfParty);
                    String hostEmail = sc.next().strip().trim();
                    Host host = ((Host) database.getUserByEmail(hostEmail));
//                        System.out.println("email= " + hostEmail);
                    String eventTitle = sc.next().strip().trim();
//                        System.out.println("title= " + eventTitle);
                    int guestLimit = Integer.parseInt(sc.next().strip().trim());
//                        System.out.println("guest limit=" + guestLimit);
                    String date = sc.next().strip().trim();
//                        System.out.println("date = " + date);
                    int year = Integer.parseInt(date.substring(0, 3));
//                        System.out.println("year= " + year);
                    int month = Integer.parseInt(date.substring(5, 7));
//                        System.out.println("month= " + month);
                    int day = Integer.parseInt(date.substring(8));
//                        System.out.println("day= " + day);
                    String location = sc.next();
//                        System.out.println("location= " + location);
                    String id = sc.next().trim().strip();
//                    System.out.println("id= " + id);
                    TypesOfGiftParties chosenGiftParty = null;
                    TypesOfRegParties chosenRegParties = null;


                    String specificPartyEnum = sc.next();
                    // System.out.println("Specific type of party chosen: "+specificPartyEnum);

                    if (typeOfParty.equalsIgnoreCase("GiftParty") || (typeOfParty.equalsIgnoreCase("PartyWithRequirementsPremium"))) {
                        chosenGiftParty = TypesOfGiftParties.match(specificPartyEnum);
                        //System.out.println("ChosenGiftParty in FileReader: "+chosenGiftParty);
//                            System.out.println("Type of party: " + chosenGiftParty);
                    } else {
                        chosenRegParties = TypesOfRegParties.match(specificPartyEnum);
                        //  System.out.println("ChosenRegParty in FileReader: "+chosenRegParties);
//                            System.out.println("Type of party: " + chosenRegParties);
                    }


                    //System.out.println("Type: "+type);

                    //System.out.print("Host email: " + hostEmail);

                    //System.out.print(host);
                    //  System.out.println("Type of party"+typeOfParty);

                    //System.out.print("host= "+ host + "event title= "+eventTitle + "guestLimit= "+guestLimit +"day= "+day +"month= "+month +"year= "+year +"location= "+location +"id= "+id +"Chosen Party= "+specificPartyEnum);

                    if (typeOfParty.equalsIgnoreCase("RegParty")) {
                        //System.out.println("hello my little friend1");

                        database.createRegParty(host, eventTitle, guestLimit, day, month, year, location, id, chosenRegParties);
                    } else {
                        database.createPartyWithRequirements(typeOfParty, host, eventTitle, guestLimit, day, month, year, location, id, chosenGiftParty);
                        //System.out.println("hello my little friend3");
                        //System.out.println("FileReader:" +eventTitle);
                    }
                }


            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found, database will start as empty");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void initializeUsers(Database database) {
        // read a file / make an object
        //FileInputStream fin;
        try (FileInputStream fin = new FileInputStream("users.txt")) {
            try (Scanner sc = new Scanner(fin)) {
                //  GiftParty,dvdrisco@usc.edu,beb,8,0000-04-02,bye,Birthday,beb1111,
                while (sc.hasNext() && sc.hasNextLine()) {
//                    System.out.print(sc.hasNextLine());
                    sc.useDelimiter(",");
                    String userType = sc.next().trim().strip();
//                  System.out.println("filename: " + fileName1);
                    String userEmail = sc.next().trim();
                    //System.out.println("User email: "+userEmail);
                    String name = sc.next().trim();
                    // System.out.println("name= " + name);
                    String userName = sc.next().trim();
                    //System.out.println("User Name: " + userName);
                    String password = sc.next().trim();


                    //System.out.println(email +" "+ name+" " + password+" " + color);


                    if (userType != null && userEmail != null && name != null && userName != null & password != null) {// have a good book to add

                        if (userType.equalsIgnoreCase("PremiumUser")) {
                            String creditCard = sc.next().trim();
                            String securityCode = sc.next().trim();
                            //System.out.println("Type: " + type);
                            //System.out.println("Email:" + count + " " + email);
                            //System.out.println("Type1: "+type);
                            //System.out.println("Name: "+ name);
                            //System.out.println("UserName :"+ userName);
                            //System.out.println("Password :"+ password);
                            //System.out.println("Credit Card #: " + creditCard);
                            //System.out.println("Security Code: " + securityCode);
                            database.createUser(userEmail, name, userName, password, creditCard, securityCode);
                            //System.out.println("User type: " +userType+" User email: "+userEmail+" User name: "+userName+" User Password: "+password+ "credit card: "+creditCard+" Security code: "+ securityCode);
                            //System.out.println("database.createPremiumUser");
                        } else {
                            //System.out.println("Type: " + type);
                            //System.out.println("Email:" + count + " " + email);

                            //System.out.println("Name: "+ name);
                            //System.out.println("UserName :"+ userName);
                            //System.out.println("Password :"+ password);
                            database.createUser(userType, userEmail, name, userName, password);
                            //System.out.print("User type: " +userType+" User email: "+userEmail+" User name: "+userName+" User Password: "+password);
                            //System.out.println("database.createUser");
                            //System.out.println("User type: " +userType+" User email: "+userEmail+" User name: "+userName+" User Password: "+password);
                        }
                        //System.out.println("User added: " + u);
                    }
                    // if the next thing in the file is blank or starts with the file name,
                    // don't read it / set it equal to null


                    //myMap.put(u.getEmail(), u);
                    //System.out.println("map added to");
                }

                //return myMap;
                //myMap
            }
/*
    public Map<String, String> initializeGuestList(String fileName) {
        // read a file / make an object
        Map<String , String> myMap = new HashMap<>();
        User u=null;
        //FileInputStream fin;
        try (FileInputStream fin = new FileInputStream("guestList.txt")) {
            try (Scanner sc = new Scanner(fin)) {
                while(sc.hasNextLine()){
 */
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initializeGuestList(Database database) {
        System.out.println("We have entered the abyss");
        // read a file / make an object
        //FileInputStream fin;
        try (FileInputStream fin = new FileInputStream("invitations.txt")) {
            try (Scanner sc = new Scanner(fin)) {
                while (sc.hasNextLine()) {
                    //  GiftParty,dvdrisco@usc.edu,beb,8,0000-04-02,bye,Birthday,beb1111,
                    System.out.println("is there next? " + sc.hasNextLine() + " " + sc.hasNext());
                    String line = sc.nextLine();
                    if (!line.trim().isEmpty()) {
                        System.out.println("line: " + line);
                        Scanner currentLine = new Scanner(line);
                        currentLine.useDelimiter(",");
                        String partyID = currentLine.next().trim().strip();
                        System.out.println("partyID: " + partyID);
                        while (currentLine.hasNext()) {
                            // System.out.print("Party ID "+partyID);
                            //System.out.println("User type: "+userType);
//                  System.out.println("filename: " + fileName1);
                        /*
                        String textGuestList=currentLine.next();

                        // Found this method on StackOverflow. I will be using to parse a string, which is seperated by commas
                        // in order read from the invitations file and set the guest list.
                        //https://stackoverflow.com/questions/7488643/how-to-convert-comma-separated-string-to-list
                        String [] items = textGuestList.split(",");
                        System.out.println("list of email invited: "+items);
                                // Intelliji recomended that I use List.of method
                        invitedEmails= Arrays.asList(items);

                         */
                            String guest = currentLine.next().trim().strip();
                            System.out.println("Guest: " + guest);

                            //System.out.println("Array List of emails invtied: "+invitedEmails);
                            Party party = database.getPartyByID(partyID);
                            System.out.println("Party: " + party);

                            party.addToGuestList(guest);

                            //System.out.println("party guestlist: "+ party.getGuestList());
                            //invitedEmails.add(reader.next().trim().strip());
                        }
                    }
                }

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void initializeRSVPList(Database database) {
        System.out.println("We have entered the abyss");
        // read a file / make an object
        //FileInputStream fin;
        try (FileInputStream fin = new FileInputStream("rsvp.txt")) {
            try (Scanner sc = new Scanner(fin)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    if (!line.trim().isEmpty()) {
                        //                    System.out.println("line: " +line);
                        Scanner currentLine = new Scanner(line);
                        currentLine.useDelimiter(",");
                        String partyID = currentLine.next().trim().strip();
//                    System.out.println("partyID: " + partyID);
                        String guestEmail = currentLine.next().trim().strip();
//                    System.out.println("Guest Email: "+ guestEmail);
                        String RSVPText = currentLine.next().trim().strip();
                        RSVP rsvp = RSVP.match(RSVPText);
                        //System.out.println("Array List of emails invtied: "+invitedEmails);
                        Party party = database.getPartyByID(partyID);
//                    System.out.println("Party: "+ party);
                        party.addToRSVPList((Guest) (database.getUserByEmail(guestEmail)), rsvp);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void initializePartyRSVPList(Database database) {
        System.out.println("We have entered the abyss");
        // read a file / make an object
        try (FileInputStream fin = new FileInputStream("party_rsvp.txt")) {
            try (Scanner sc = new Scanner(fin)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    if (!line.trim().isEmpty()) {
                        Scanner currentLine = new Scanner(line);
                        currentLine.useDelimiter(",");
                        String guestEmail = currentLine.next().trim().strip();
                        String RSVPText = currentLine.next().trim().strip();
                        RSVP rsvp = RSVP.match(RSVPText);
                        String partyId = currentLine.next().trim().strip();
                        String partyItem = currentLine.next().trim().strip();
                        PartyRSVP newPartyRSVP = new PartyRSVP(rsvp, partyItem, guestEmail, partyId);
                        PartyWithRequirements party = (PartyWithRequirements) database.getPartyByID(partyId);
                        party.addToPartyRSVPList(newPartyRSVP);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}


