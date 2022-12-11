import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class PartyUserPractice {
    private Database d;
    private Savior helper1;

    private User currentUser;


    public PartyUserPractice() {
        d = new Database();
        helper1 = new Savior();
    }
    /*
    public void initialRun(){
        bff.printFancy("INITIAL RUN");
        bff.print("Step One: Make up some users");
        enterUserData();

        bff.print("Step Two, show users:");
        showUsers();

        bff.print("Step Three: Find User Objects by Email. Look for someone in database and someone NOT in database");
        String email = bff.inputWord("Please enter email 1: ");
        User u  = db.getUserByEmail(email);
        bff.print(email + " matched to: " + u);
        //second example case:
        email = bff.inputWord("Please enter email 2: ");
        u  = db.getUserByEmail(email);
        bff.print(email + " matched to: " + u);

        bff.print("Step Four: Write users to file");
        String file = bff.inputWord("Please enter file name");
        db.saveAll(file);
    }
     */
    public void createAnAccount() {
        System.out.println("Lets create an account!");
        String typeOfAccount = helper1.inputWord("Type of account:", "Guest", "Host");
        //System.out.print(typeOfAccount);
        String name = helper1.inputLine(("Name:"));
        String email = helper1.inputWord("Email:");
        String userName = helper1.inputWord("Username: ");
        String password = helper1.inputWord("Password: ");
        // user, guest, host, premium guest, premium host
        if (typeOfAccount.equalsIgnoreCase("Guest")) {
            User u = new Guest(email, name, userName, password);
            d.addNewUser(u);
            // userName, password
        }
        else {
            User u = new Host(email, name, userName, password);
        }
        boolean premium= helper1.inputYesNo("Do you want to be a premium "+typeOfAccount+" for only $10.00 a month? (Extra functions are included): ");
        if (premium){
                String creditCardNumber= helper1.inputLine("Enter credit card information");
                String securityCode= helper1.inputLine("Enter security code number: ");
                // add some rules to this (while size!= 8...)*
                User u = new PremiumUser(email, name, userName, password,creditCardNumber, securityCode);
                d.addNewUser(u);
            }
    }
}
