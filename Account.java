
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
public class Account
{   private String name;
    private String addres;
    private String accountNum;
    private String accountType;
    private double balance;

    int lineCount = 0;
    int accountIndex = -1;
    ArrayList<String> accountArray = new ArrayList<String>();
    //this constructure methode will take in a name and then find the account info under that given name.
    public Account (String name, String accountNume){
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
            
                if(value[0].equals(name)){
                    keepCounting = false;
                    this.name = value[0];
                    this.addres = value[1];
                    this.accountNum = value[2];
                    this.accountType = value[3];
                    this.balance = Double.valueOf(value[4]); 
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }   
    
    
    //This method takes in name, addres, account type in int e.g.1 for Everyday 2 for Savings and 3 for Current.
    //It will genarate account number and safe the new data to the arraylist.
    public static String createAccount(String name, String adress, int type, int numOfAccount){
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
        String accountNum = "08-0101-";
        for (int i=0; i<7; i++){
            int j = random.nextInt(9);
            accountNum = accountNum+j;
        }
        accountNum = accountNum + "-00";
        String data = (name+","+adress+","+accountNum+","+accountType+","+"0.00"); 
        return data;
    }
    
    public void flushAccount(){
        String data = name+","+addres+","+accountNum+","+accountType+","+balance+"";
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
    
    public String addres(){
        return addres;
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


