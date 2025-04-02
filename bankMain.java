
/**
 * Write a description of class bankMain here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;
 
public class bankMain
{
    public static void main(String args[]){
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("Wellcome to Kawaii bank,\nplease type your name.");
        String name = keyboard.nextLine();
        System.out.println("Please put in the account number,");
        String accountNum = keyboard.nextLine();
        Account userAccount = new Account(name);
        System.out.println(userAccount.name());
        System.out.println(userAccount.addres());
        System.out.println(userAccount.balance());
        
    }
}
