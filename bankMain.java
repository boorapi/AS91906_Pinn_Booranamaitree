
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
    public static int getIntInRange(int x, int y){
        Scanner keyboard =  new Scanner(System.in);
        while(true){
            if(keyboard.hasNextInt()){
                int num = keyboard.nextInt();
                if(!(x<=num && y>=num)){
                    System.out.println("Please type an integer in range "+x+"-"+y+".");
                } 
                else{
                    return num;
                }
            } 
            else{
                keyboard.nextLine();
                System.out.println("Please type an integer");    
            }
        }
    }
    
    public static double getDouble(){
        Scanner keyboard = new Scanner(System.in);
        while(true){
            if(keyboard.hasNextDouble()){
                double num = keyboard.nextDouble();
                if(num>=0){
                    return num;
                }
                else{
                    System.out.println("Please type in a positive amount");
                }
            }
            else{
                keyboard.nextLine();// Clear the invalid input
                System.out.println("Please type in a valid amount.");
            }
        }
    }

    public static void main(String args[]){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Wellcome to Kawaii bank");
        
        boolean done = false;
        while(!done){
            System.out.println("Please type in your name.");
            String name = keyboard.nextLine();
            Account userAccount = new Account(name, "accountNum");
            
            
            if(userAccount.name() == null){
                System.out.println("There is no account under this name. Please choose your opption\n1) to retype the name\n2)Create a new account under this name");
                int x  = getIntInRange(1, 2);
                if( x == 2){
                    //make new account
                } 
                
            } 
            else {  
                boolean finish = false;
                System.out.println("Hi "+name+"! Welcome to your account.\nname: "+userAccount.name()+"\naddres: "+userAccount.addres());
                System.out.println("Account number: "+userAccount.accountNum()+"\nAccount type; "+userAccount.accountType()+"\nBalance: "+userAccount.balance());
                while(!finish){    
                    System.out.println("\nWhat would you like to do.\n1)Create new account under your name.\n2)Deposit\n3)Withdraw");
                    int x = getIntInRange(1, 3);
                    if(x==1){
                        //genarate new account
                    } 
                    else if (x==2){
                        System.out.println("Please type the amount that you wish to deposit.");
                        double amount = getDouble();
                        System.out.println("Your current balance is " + userAccount.deposit(amount));
                        //type 1 if you are done or 2 to bla bla bla.
                    } 
                    else{
                        System.out.println("Please type the amount that you wish to withdraw.");
                        double amount = getDouble();
                        if(userAccount.accountType().equals( "Current")){
                            if(userAccount.balance()-amount<-1000){
                                System.out.println("Your account does not have sufficient fund, the overdraft limit is 1000$");
                                System.out.println("You now have " +userAccount.balance()+ "$ in your account");
                            }
                            else{
                                userAccount.withdrawal(amount);
                                System.out.println("You now have " +userAccount.balance()+ "$ remaining in your account");
                            }
                        }
                        else {
                            if(userAccount.balance()-amount<0){
                                System.out.println("Your account does not have sufficient fund, the account does not alowed overdraft");
                            }
                            else{
                                userAccount.withdrawal(amount);
                                System.out.println("You now have " +userAccount.balance()+ "$ remaining in your account");
                            }
                        }
                    }
                    
                    System.out.println("\nPlese type\n1) If you wich to do more transaction\n2) If you are done\n3)Back to home page");
                    int y = getIntInRange(1, 3);
                    if (y==2){
                        finish = true;
                        done = true;
                        System.out.println(userAccount.name());
                        System.out.println(userAccount.addres());
                        System.out.println(userAccount.balance());
                    } else if (y==3){
                        finish = true;
                        //clear screen;
                    }
                }
            }
            //userAccount.flushAccount();
        }
    }
}