
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
        //infinite loop that will break if there is return
        while(true){
            // Check if the next input is an integer
            if(keyboard.hasNextInt()){
                int num = keyboard.nextInt();
                // Check if the number is within the valid range 
                if(!(x<=num && y>=num)){
                    System.out.println("Please type an integer in range "+x+"-"+y+".");//if not prompt the user
                } 
                else{
                    return num;//return valid input
                }
            } 
            else{
                keyboard.nextLine();//clear the invalid input
                System.out.println("Please type an integer");//prompt the user to type number
            }
        }
    }
    //This method will get a positive double
    // It keeps asking until a valid input are entered
    public static double getDouble(){
        Scanner keyboard = new Scanner(System.in);
        //infinite loop that will break if there is return
        while(true){
            // Check if the next input is a valid double
            if(keyboard.hasNextDouble()){
                double num = keyboard.nextDouble();
                //make sure the number is positive
                if(num>=0){
                    return num;//reutrn the number
                }
                else{
                    System.out.println("Please type in a positive amount");//tell the user to type in positive number
                }
            }
            else{
                keyboard.nextLine();// Clear the invalid input
                System.out.println("Please type in a valid amount.");//prompt the user to type number
            }
        }
    }
    //This method will ask the user for their name, address, and the account type 
    //Then create a new account for the user
    public static void makeNewAccount(){
        Scanner keyboard = new Scanner(System.in);
        boolean notProceed = true;// Used to repeat the process if user chooses to redo
        while(notProceed){
            System.out.println("Please enter your name");//aks the user to input their name
            String userName = keyboard.nextLine();
            System.out.println("Please enter your address. Make sure not to put any comma.");//ask user the input their address without any comma
            String address = keyboard.nextLine();
            address = address.replace(",","");//incase user acidently type in comma
            // Ask the user to select an account type by number
            System.out.println("Please choose account type, the option are\n1)Everyday\n2)Saving\n3)Current\nchoose by typing the number accordingly");
            int choice = getIntInRange(1, 3);// Ensure input is within valid range
            String type;
            //convert numeric choice to string
            switch (choice){
                case 1 : type = "Everyday";
                    break;
                case 2 : type = "Savings";
                    break;
                case 3 : type = "Current";
                    break;
                default: type = "Unknown";
            }
            //Display the entered information for confirmation
            System.out.println("Name: "+userName+"\nAddress: "+address+"\nAccount type: "+type);
            //aks if the user want to proceed
            System.out.println("Please check the information above and choose the options\n1) To proceed\n2) To redo.");
            int i = getIntInRange(1, 2);
            //if they want to proceed
            if(i == 1){
                notProceed = false;//to stop the loop
                //call method to genarate account and store the account number that are returned
                String num = Account.createAccount(userName, address, choice);
                //show the account number to the user
                System.out.println("Your account number is "+num+" please write this down for next time.");
                // Write the new account details to the file
                Account.writeToFile();
            }
        }
    }

    public static void main(String args[]){
        Scanner keyboard = new Scanner(System.in);
        //Load all the account data from the csv file into arrayList
        Account.loadAccount();
        
        boolean end = false;//The user can keep using the program until they want to exit
        while(!end){
            //ascii art for "kawaii-banck"
            System.out.println("  _  __                   _ _        ____              _    ");
            System.out.println(" | |/ /                  (_|_)      |  _ \\            | |   ");
            System.out.println(" | ' / __ ___      ____ _ _ _ ______| |_) | __ _ _ __ | | __");
            System.out.println(" |  < / _` \\ \\ /\\ / / _` | | |______|  _ < / _` | '_ \\| |/ /");
            System.out.println(" | . \\ (_| |\\ V  V / (_| | | |      | |_) | (_| | | | |   < ");
            System.out.println(" |_|\\_\\__,_| \\_/\\_/ \\__,_|_|_|      |____/ \\__,_|_| |_|_|\\_\\");
            
            //ask what the user want to do 
            System.out.println("what would you like to do. Please choose the options below.\n1) Acces an existing account or\n2) Create new account");
            int menuChoice = getIntInRange(1, 2);// Ensure input is within valid range
            //if the user want to create account
            if(menuChoice == 2){
                makeNewAccount();//make new account
                System.out.println("Please choose the following options:\n1)Back to home page\n2)Exit program");
                int option = getIntInRange(1,2);// Ensure input is within valid range
                if(option == 2){
                    end = true;//Exit program
                }
                System.out.print('\u000C');//clear screen
            } 
            //if the user want to continue with existing account
            else{
                //Get the iser name
                System.out.println("Please type in your name.");
                String name = keyboard.nextLine();
                //Get the account number
                System.out.println("Please Type in the account number:\nxx-xxxx-xxxxxxx-xx");
                String number = keyboard.nextLine();
                //load user account information by create an account object
                Account userAccount = new Account(name.toLowerCase(), number);
                
                //If the account are not found
                if(userAccount.name() == null){
                    // Offer options to go back, create a new account, or exit
                    System.out.println("There is no account under this name. Please make sure the name and numbers are correct");
                    System.out.println("Please choose the following opption:\n1)Back to home page\n2)Create a new account\n3)Exit program");
                    int x  = getIntInRange(1, 3);// Ensure input is within valid range
                    //Make new account
                    if( x == 2){
                        makeNewAccount();
                        System.out.println("Please choose the following options:\n1)Back to home page\n2)Exit program");
                        int option = getIntInRange(1,2);
                        if(option == 2){
                            end = true;//Exit the program
                        }
                    }
                    //Exit program
                    else if(x == 3){
                        end = true;
                    }
                    System.out.print('\u000C');//clear screen
                }
                else {  
                    boolean finish = false;//The user can keep doing transaction util they satisfy
                    
                    //Display welcome message and account details
                    System.out.println("Hi " + userAccount.name() + "! Welcome to your account.");
                    System.out.println("Name: " + userAccount.name() + "\nAddress: " + userAccount.address());
                    System.out.println("Account number: " + userAccount.accountNum());
                    System.out.println("Account type: " + userAccount.accountType());
                    System.out.println("Balance: " + userAccount.balance());
                    
                    //loop trought transaction mene
                    while(!finish){    
                        System.out.println("What would you like to do.\n1)Deposit\n2)Withdraw");
                        int x = getIntInRange(1, 2);// Ensure input is within valid range
                        //deposite
                        if (x==1){
                            System.out.println("Please type the amount that you wish to deposit.");
                            double amount = getDouble();//method to get a valid double input
                            System.out.println("Your current balance is " + userAccount.deposit(amount));
                            userAccount.flushAccount();//Save updated balance to arrauList
                        } 
                        //withdrawal
                        else{
                            System.out.println("Please type the amount that you wish to withdrawal.");
                            double amount = getDouble();//method to get a valid double input
                            //If the account type is cuurent
                            if(userAccount.accountType().equals( "Current")){
                                //Current accounts allow overdraft up to -1000
                                if(userAccount.balance()-amount<-1000){
                                    System.out.println("Your account does not have sufficient fund, the overdraft limit is 1000$");
                                    System.out.println("You now have " +userAccount.balance()+ "$ in your account");
                                }
                                //other wise withdrawalthe money
                                else{
                                    userAccount.withdrawal(amount);//withdrawal method to withdrawal
                                    System.out.println("You now have " +userAccount.balance()+ "$ remaining in your account");
                                }
                            }
                            // Other accounts cannot be overdrawn
                            else {
                                //If balance after withdrawal is less than 0
                                if(userAccount.balance()-amount<0){
                                    System.out.println("Your account does not have sufficient fund, the account does not alowed overdraft");
                                }
                                //other wise withdrawalthe money
                                else{
                                    userAccount.withdrawal(amount);//withdrawal method to withdrawal
                                    System.out.println("You now have " +userAccount.balance()+ "$ remaining in your account");
                                }
                            }
                            userAccount.flushAccount();//save and update this account to the array list
                        }
                        //Ask if the user wants to do another transaction, go home, or exit
                        System.out.println("Plese choose the following options:\n1)Do more transaction\n2)Back to home page\n3)Exit program");
                        int y = getIntInRange(1, 3);
                        if (y==2){
                            finish = true;//Exit transaction page and go to home page
                            System.out.print('\u000C');//clear screen
                        } else if (y==3){
                            finish = true;//Exit transaction page
                            end = true;//Exit program
                        }
                    }
                }
            }
        }
        System.out.print('\u000C');//clear screen
        Account.writeToFile();//write all the updated account information to the csv file
        System.out.println("Thank you for using  Kawaii bank and we hope to see you again soon!");
    }
}