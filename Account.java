
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
public class Account
{   private String name;
    private String address;
    private String accountNum;
    private String accountType;
    private double balance;

    int lineCount = 0;
    int accountIndex = -1;
    int sameName = 0; //This variable will keep track on how many account is under the name 
    //so I can assign account number correctly
    ArrayList<String> accountArray = new ArrayList<String>();
    //this constructure methode will take in a name and then find the account info under that given name.
    public Account (String name, String num){
        File accountFile = new File("AccountData.csv");
        try{
            Scanner readFile = new Scanner(accountFile);
            while(readFile.hasNextLine()){
                accountArray.add(readFile.nextLine());
                lineCount++;
            }

            boolean keepCounting = true;
            for (String account : accountArray){
                String[] value = account.split(",");
                if(keepCounting){
                    accountIndex++;
                }
                
                //check for same name. if there is no account under this name last digit is 00 if there 
                //is already 1 account, last digit will be 01, if theres 2 = 02 and so on.
                if(value[0].equals(name)){
                    sameName++;
                }
                
                //if the name is the same and the account number are the same.
                if(value[0].equals(name) && value[2].equals(num)){
                    keepCounting = false;
                    this.name = value[0];
                    this.address = value[1];
                    this.accountNum = value[2];
                    this.accountType = value[3];
                    this.balance = Double.valueOf(value[4]); 
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }   
    
    
    //This method takes in name, address, account type in int e.g.1 for Everyday 2 for Savings and 3 for Current, and boolean to check if there already existing account.
    //It will genarate account number and save the new data to the arraylist.
    public String createAccount(String name, String address, int type, boolean alreadyHaveAccount){
        Random random = new Random();
        String accountType;
        switch (type){
            case 1 : accountType = "Everyday";
                break;
            case 2 : accountType = "Savings";
                break;
            case 3 : accountType = "Current";
                break;
            default: accountType = "Unknown";
        }
        String accountNumber = "08-0101-";
        for (int i=0; i<7; i++){
            int j = random.nextInt(9);
            accountNumber = accountNumber+j;
        }
        
        if(alreadyHaveAccount){
            int x = 0;
            //A loop to check account that have highest last digit and that is under the same name.
            for (String account : accountArray){
                String[] split = account.split(",");
                //If the account is under this name
                if(split[0].equals(name)){
                    String num = split[2];
                    String[] value = num.split("-");
                    String i = value[3];
                    int lastNum = Integer.parseInt(i);
                    if(lastNum>x){
                        x = lastNum;
                    }
                }
            }
            x++;
            if(x>9){
                accountNumber = accountNumber+"-"+x;
            } else{
                accountNumber =accountNumber+"-0"+x;
            }
        } else{
            accountNumber = accountNumber + "-00";
        }
        String data = (name+","+address+","+accountNumber+","+accountType+","+"0.00"); 
        accountArray.add(data);
        return accountNumber;
    }
    
    //If I call this method it will rewrite all the updated data to the account .csv file
    public void writeToFile(){
        File accountFile = new File("AccountData.csv");
        // String variable called newLine, which will allow me to create new without using \n
        String newLine = System.getProperty("line.separator");
        try{
            FileWriter writer = new FileWriter(accountFile);
            for(String element : accountArray){
                writer.write(element+newLine);
            }
            writer.flush();
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void flushAccount(){
        String data = name+","+address+","+accountNum+","+accountType+","+balance+"";
        accountArray.set(accountIndex, data);
    }
    
    public Double withdrawal(Double amount){
        balance = balance-amount;
        return balance;
    }
    
    public Double deposit(Double amount){
        balance = balance+amount;
        return balance;
    }
    
    public String name(){
        return name;
    }
    
    public String address(){
        return address;
    }
    
    public String accountNum(){
        return accountNum;
    }
    
    public String accountType(){
        return accountType;
    }
    
    public Double balance(){
        return balance;
    }
}


