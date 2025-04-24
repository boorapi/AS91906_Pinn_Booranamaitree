
/**
 * Write a description of class Account here.
 *
 * @author (your name)
 * @version (a version number or a date)
 * This class will read the user acount, create user account, and  return all the data.
 */
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter; //to write file
public class Account {
    private static ArrayList<String> accountArray = new ArrayList<String>();//this array lit will store all the user information.
    //This method will load all the data from csv file to "accountArray" ArrayList.
    //It would only run once when the program start.
    public static void loadAccount(){
        File accountFile = new File("AccountData.csv");//create new file object called accountFile. 
        try{
            Scanner readFile = new Scanner(accountFile);//use Scanner to read the file
            //while the file has next line
            while(readFile.hasNextLine()){
                accountArray.add(readFile.nextLine());//add that line to accountArray
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private String name;
    private String address;
    private String accountNum;
    private String accountType;
    private double balance;
    private int accountIndex = -1;//This value will keep track of which index that particular account element is in.
    //this constructure methode will take in a name and account number and then get the account information under the name.
    public Account (String name, String num){
        boolean keepCounting = true;
        //loop through every element in accountArray
        for (String account : accountArray){
            String[] value = account.split(",");//separate the account info by comma and store it in array called value.
            
            //keep counting the index of accountArray until keepCounting is false.
            if(keepCounting){
                accountIndex++;//increment by 1 every times
            }
                
            //if the name (in lowercase so if the user type wrong case its still work)is the same and the account number are the same.
            if(value[0].toLowerCase().equals(name) && value[2].equals(num)){
                keepCounting = false;//stop counting the index
                this.name = value[0];//first value is name
                this.address = value[1];//second value is the address
                this.accountNum = value[2];//third one is the account number
                this.accountType = value[3];//fourth one is the account type
                this.balance = Double.valueOf(value[4]);//last one is balance which are convert from string to double 
            }
        }
    }   
    
    //This method will create account and save the new data to the arraylist.
    //The method takes in name, address, account type in int e.g.1 for Everyday 2 for Savings and 3 for Current.
    //It also genarate accoun number for you. If there is already an account under user name it increment the lase suffix by 1 
    public static String createAccount(String name, String address, int type){
        Random random = new Random();//create random odject to create random number.
        // Determine the account type based on the integer input
        String accountType;
        switch (type){
            case 1 : accountType = "Everyday";
                break;
            case 2 : accountType = "Savings";
                break;
            case 3 : accountType = "Current";
                break;
            default: accountType = "Unknown";//// Fallback in case of invalid type
        }
        //A fixed prefix of account number
        String accountNumber = "08-0101-";
        //addpend 7 random digit to account number.
        for (int i=0; i<7; i++){
            int j = random.nextInt(9);
            accountNumber = accountNumber+j;
        }
        
        int x = 0;//Used to track the highest "last digit" suffix for accounts with the same name
        // Loop through all existing accounts to find the highest suffix for accounts with the same name
        for (String account : accountArray){
            String[] split = account.split(",");
            //If the account name match (case does not matter)
            if(split[0].toLowerCase().equals(name.toLowerCase())){
                String num = split[2];//get the full account number
                String[] value = num.split("-");// Split the account number by dashes
                String i = value[3];// Get the suffix after the final dash
                int lastNum = Integer.parseInt(i);// Convert suffix to integer
                // Update x if this suffix is higher than the current max
                if(lastNum>x){
                    x = lastNum;
                }
            }
        }
        //If there is account found, increment x to get to next suffix
        if(x>0){
            x++;
        }
        // Append the new suffix to the account number with proper formatting (e.g. "-01")
        if(x>9){
            accountNumber = accountNumber+"-"+x;
        } else{
            accountNumber =accountNumber+"-0"+x;
        }
        // Combine all account details into a single csv formatt
        String data = (name+","+address+","+accountNumber+","+accountType+","+"0.00"); 
        accountArray.add(data);//add it to the accountArray
        return accountNumber;//return account number so I can printed out to the user
    }
    
    //If I call this method it will write all the updated data from accountArray to the account csv file
    public static void writeToFile(){
        File accountFile = new File("AccountData.csv");//create "AccountData" object
        // String variable called newLine, which will allow me to create new without using \n
        String newLine = System.getProperty("line.separator");
        try{
            FileWriter writer = new FileWriter(accountFile); // Create a FileWriter object to write data to the file
            //loop through each account data and write them to the file
            for(String element : accountArray){
                writer.write(element+newLine);//write data and new line at the end
            }
            writer.flush();// Ensure all data is written from the buffer
            writer.close();//close file writer
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    // Updates the accountArray using its account index with the latest data from the object
    public void flushAccount(){
        String data = name+","+address+","+accountNum+","+accountType+","+balance+"";
        accountArray.set(accountIndex, data);
    }
    
    // Subtracts the withdrawal amount from the account balance and returns the new balance
    public Double withdrawal(Double amount){
        balance = balance-amount;
        return balance;
    }
    
    // adds the deposit amount to the account balance and returns the new balance
    public Double deposit(Double amount){
        balance = balance+amount;
        return balance;
    }
    //method to get account holder's name
    public String name(){
        return name;
    }
    //method to get account holder's address
    public String address(){
        return address;
    }
    //method to get account holder's account number
    public String accountNum(){
        return accountNum;
    }
    //method to get account holder's account type
    public String accountType(){
        return accountType;
    }
    //method to get account holder's balance
    public Double balance(){
        return balance;
    }
}


