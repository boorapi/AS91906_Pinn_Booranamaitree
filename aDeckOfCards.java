
/**
 * Write a description of class aDeckOfCards here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.ArrayList;
public class aDeckOfCards
{
    ArrayList<String> Adeck = new ArrayList<String>();
        public String makeSuit(int i){
        switch (i){
            case 1 : return " spades";            
            case 2 : return " hearts";                
            case 3 : return " dimonds";              
            case 4 : return " clubs";  
        }
        return  "null";
    }
    
    public String faceValue(int i){
        switch(i){
           case 11 : return "Jack";
           case 12 : return "Queen";
           case 13 : return "King";
           case 1 : return "Ace";
        }
        return " " + i;
            }
    
    aDeckOfCards(){
        for(int i=1; i<5; i++){
            for(int j=1; j<14; j++){
                String Acard = makeSuit(i) + " of" + faceValue(j);
                Adeck.add(Acard);
            }
        }      
    }
}

