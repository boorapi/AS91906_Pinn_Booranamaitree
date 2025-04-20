
/**
 * Write a description of class bankMain here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class bankMain{
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

    public static void makeNewAccount(){
        Scanner keyboard = new Scanner(System.in);
        boolean notProceed = true;
        while(notProceed){
            System.out.println("Please enter your name");
            String userName = keyboard.nextLine();
            System.out.println("Please enter your address. Make sure not to put any comma.");
            String address = keyboard.nextLine();
            address = address.replace(",","");//incase suser acidently type in comma
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
            System.out.println("Name: "+userName+"\nAddress: "+address+"\nAccount type: "+type);
            System.out.println("Please check the information above and choose the options\n1) To proceed\n2) To redo.");
            int i = getIntInRange(1, 2);
            if(i == 1){
                notProceed = false;
                String num = Account.createAccount(userName, address, choice);
                System.out.println("Your account number is "+num+" please write this down for next time.");
                Account.writeToFile();
            }
        }
    }

    public static void main(String args[]){
        Scanner keyboard = new Scanner(System.in);
        Account.loadAccount();
        
        boolean end = false;
        while(!end){
            System.out.println("  _  __                   _ _        ____              _    ");
            System.out.println(" | |/ /                  (_|_)      |  _ \\            | |   ");
            System.out.println(" | ' / __ ___      ____ _ _ _ ______| |_) | __ _ _ __ | | __");
            System.out.println(" |  < / _` \\ \\ /\\ / / _` | | |______|  _ < / _` | '_ \\| |/ /");
            System.out.println(" | . \\ (_| |\\ V  V / (_| | | |      | |_) | (_| | | | |   < ");
            System.out.println(" |_|\\_\\__,_| \\_/\\_/ \\__,_|_|_|      |____/ \\__,_|_| |_|_|\\_\\");
            
            System.out.println("what would you like to do. Please choose the options below.\n1) Acces an existing account or\n2) Create new account");
            int menuChoice = getIntInRange(1, 2);
            if(menuChoice == 2){
                makeNewAccount();
                System.out.println("Please choose the following options:\n1)Back to home page\n2)Exit program");
                int option = getIntInRange(1,2);
                if(option == 2){
                    end = true;
                }
                System.out.print('\u000C');//clear screen
            } 
            else{
                System.out.println("Please type in your name.");
                String name = keyboard.nextLine();
                System.out.println("Please Type in the account number:\nxx-xxxx-xxxxxxx-xx");
                String number = keyboard.nextLine();
                Account userAccount = new Account(name.toLowerCase(), number);
                if(userAccount.name() == null){
                    System.out.println("There is no account under this name. Please choose the following opption:\n1)Back to home page\n2)Create a new account\n3)Exit program");
                    int x  = getIntInRange(1, 3);
                    if( x == 2){
                        makeNewAccount();
                        System.out.println("Please choose the following options:\n1)Back to home page\n2)Exit program");
                        int option = getIntInRange(1,2);
                        if(option == 2){
                            end = true;
                        }
                        System.out.print('\u000C');//clear screen
                    } else if(x == 3){
                        end = true;
                    }
                }
                else {  
                    boolean finish = false;
                    System.out.println("Hi "+name+"! Welcome to your account.\nname: "+userAccount.name()+"\naddres: "+userAccount.address());
                    System.out.println("Account number: "+userAccount.accountNum()+"\nAccount type: "+userAccount.accountType()+"\nBalance: "+userAccount.balance());
                    while(!finish){    
                        System.out.println("What would you like to do.\n1)Deposit\n2)Withdraw");
                        int x = getIntInRange(1, 3);
                        if (x==1){
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
    
                        System.out.println("Plese choose the following options:\n1)Do more transaction\n2)Back to home page\n3)Exit program");
                        int y = getIntInRange(1, 3);
                        if (y==2){
                            finish = true;
                            System.out.print('\u000C');//clear screen
                        } else if (y==3){
                            finish = true;
                            end = true;
                        }
                    }
                }
            }
        }
        Account.writeToFile();
        System.out.println("Thank you for using  Kawaii bank and we hope to see you again soon!");
    }
}