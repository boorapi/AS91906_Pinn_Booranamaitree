
/**
 * Write a description of class bankMain here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;


public class bankMain
{
    //This method will take in two number x and y. The mathod will loop through until it get a valid input in the given range (x - y)
    //and then return that input.
    public static int getNum(){
        Scanner keyboard =  new Scanner(System.in);
        while(!keyboard.hasNextInt()){
            keyboard.nextLine();
            System.out.println("Please type an integer");    
        }
        return keyboard.nextInt();
    }

    public static int checkRange(int x, int y){
        int num = getNum();
        while(!(x<num && y>num)){
            System.out.println("Please type an integer from "+x+"-"+y+".");
            num = getNum();
        }
        return num;
    }
    
    public static void main(String args[]){
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("Wellcome to Kawaii bank,\nplease type your name.");
        String name = keyboard.nextLine();
        Account userAccount = new Account(name);
        boolean createAccount = false;
        if(userAccount.name() == null){
            System.out.println("There is no account under this name. Please choose your opption\n1) to retype the name\n2)Create a new account under this name");
            int x  = keyboard.nextInt();
            if( x == 1){
                name = keyboard.nextLine();
            } else{
                createAccount = false;
                //genarate new account
            }
        } else{
            System.out.println("Hi "+name+"! What would you like to do today.\n1)Create new account under your name.\n2)Deposit\n3)Withdraw");
            int x = checkRange(1, 3);
            if(x==1){
                //genarate new account
            } else if (x==2){
                System.out.println("Please type the amount that you wish to deposit.");
                double amount = (double)getNum();
                while(amount<0){
                    System.out.println("Please type in a positive amount");
                    amount = (double)getNum();
                }
                System.out.println("Your current balance is " + userAccount.deposit(amount));
            } else if (x==3){
                System.out.println("Please type the amount that you wish to withdraw.");
                double amount = (double)getNum();
                while(amount<0){
                    System.out.println("Please type in a positive amount");
                    amount = (double)getNum();
                }
                if(userAccount.accountType().equals( "Current")){
                    if(userAccount.withdrawal(amount)<1000){
                        System.out.println("Your account does not have sufficient fund, the overdraft limit is 1000$");
                        System.out.println("You Currently have " +userAccount.balance()+ "$ in your account");
                    }
                    else{
                        userAccount.withdrawal(amount);
                    }
                }else if(userAccount.withdrawal(amount)<0){
                    System.out.println("Your account does not have sufficient fund, the account does not alowed overdraft");
                }
            }
        }
        
        //System.out.println("Please put in the account number,");
        String accountNum = keyboard.nextLine();
        System.out.println(userAccount.name());
        System.out.println(userAccount.addres());
        
        System.out.println(userAccount.balance());
        
    }
}
