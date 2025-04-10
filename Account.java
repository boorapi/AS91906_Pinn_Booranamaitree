
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
    public Account (String name){
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
    
    public void flushAccount(){
        accountArray.set(accountIndex, name+", "+addres+", "+accountNum+", "+accountType+", "+balance+"");
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


