
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
            Account userAccount = new Account(name, "08-0101-0423012-04");
            
            
            if(userAccount.name() == null){
                System.out.println("There is no account under this name. Please choose your opption\n1) to retype the name\n2)Create a new account under this name");
                int x  = getIntInRange(1, 2);
                if( x == 2){
                    boolean notProceed = true;
                    while(notProceed){
                        System.out.println("Please enter your name");
                        String userName = keyboard.nextLine();
                        System.out.println("Please enter your address. Make sure not to put any comma.");
                        String address = keyboard.nextLine();
                        System.out.println("Please choose account type, the option are\n1)Everyday\n2)Saving\n3)Current\nchoose by typing the number accordingly");
                        int choice = getIntInRange(1, 3);
                        String type;
                        switch (choice){
                            case 1 : type = "Everyday";
                                break;
                            case 2 : type = "Savings";
                                break;
                            case 3 : type = "Current";
                                break;
                            default: type = "Unknown";
                        }
                        System.out.println(userName+", "+address+", "+type);
                        System.out.println("If the information above is correct please type 1 to proceed, or 2 to redo.");
                        int i = getIntInRange(1, 2);
                        if(i == 1){
                            notProceed = false;
                            String num = userAccount.createAccount(userName, address, choice, false);
                            System.out.println("Your account number is "+num+" please write this down for next time.");
                        }
                    }
                    done = true;  
                } 
            } 
            else {  
                boolean finish = false;
                System.out.println("Hi "+name+"! Welcome to your account.\nname: "+userAccount.name()+"\naddres: "+userAccount.address());
                System.out.println("Account number: "+userAccount.accountNum()+"\nAccount type; "+userAccount.accountType()+"\nBalance: "+userAccount.balance());
                while(!finish){    
                    System.out.println("\nWhat would you like to do.\n1)Create new account under your name.\n2)Deposit\n3)Withdraw");
                    int x = getIntInRange(1, 3);
                    if(x==1){
                        System.out.println("Please choose account type, the option are\n1)Everyday\n2)Saving\n3)Current\nchoose by typing the number accordingly");
                        int choice = getIntInRange(1, 3);
                        String num = userAccount.createAccount(userAccount.name(), userAccount.address(), choice, true);
                        System.out.println("Your account number is "+num+" please write this down for next time."); 
                    } 
                    else if (x==2){
                        System.out.println("Please type the amount that you wish to deposit.");
                        double amount = getDouble();
                        System.out.println("Your current balance is " + userAccount.deposit(amount));
                        userAccount.flushAccount();
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
                        userAccount.flushAccount();
                    }
                    
                    System.out.println("\nPlese type\n1)If you wich to do more transaction\n2)Back to home page\n3)If you are done");
                    int y = getIntInRange(1, 3);
                    if (y==2){
                        finish = true;
                        //System.out.print('\u000C');//clear screen
                    } else if (y==3){
                        finish = true;
                        done = true;
                        System.out.println(userAccount.name());
                        System.out.println(userAccount.address());
                        System.out.println(userAccount.balance());
                    }
                }
            }
            userAccount.writeToFile();
        }
        System.out.println("\nThank you for using  Kawaii bank and we hope to see you again soon!");
    }
}