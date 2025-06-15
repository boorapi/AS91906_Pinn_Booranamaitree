
/**
 * Write a description of class Load_flie here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class Load_flie {
    
    public static void load_date(){
        File dataFile = new File("simulation_dta.csv"); // create new file object called data file.
        try{
            Scanner readFile = new Scanner(dataFile);// use Scanner to read the data file
            //while the file hase next line
            while(readFile.hasNextLine()){
                
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
