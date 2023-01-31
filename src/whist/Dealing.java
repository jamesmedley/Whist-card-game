/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author james
 */
public class Dealing {
    final String[] cardsValues = new String[]{"AC","AD","AH","AS","2C","2D","2H","2S","3C","3D","3H","3S","4C","4D","4H","4S","5C","5D","5H","5S","6C","6D","6H","6S","7C","7D","7H","7S","8C","8D","8H","8S","9C","9D","9H","9S","10C","10D","10H","10S","JC","JD","JH","JS","QC","QD","QH","QS","KC","KD","KH","KS"};//backup array used to reset Cards array
    String[] cards = new String[]{"AC","AD","AH","AS","2C","2D","2H","2S","3C","3D","3H","3S","4C","4D","4H","4S","5C","5D","5H","5S","6C","6D","6H","6S","7C","7D","7H","7S","8C","8D","8H","8S","9C","9D","9H","9S","10C","10D","10H","10S","JC","JD","JH","JS","QC","QD","QH","QS","KC","KD","KH","KS"};//array used in play
    String[] shuffled = new String[52];//temp array for shuffles
    int totalDealt=0;
    static String trump,trumpWord;
    String[] p1;
    String[] p2;
    String[] p3;
    String[] p4;
    String[] p5;
    public void resetCards(){
        System.arraycopy(cardsValues, 0, cards, 0, 52);
    }
    
    
    public String[] randomShuffle(String[] inputArray){
        Random r = new Random();
        int random;
        random = r.nextInt(52);
        for(int i=0;i<52;i++){
            if(!"".equals(inputArray[random])){
            shuffled[i] = inputArray[random];
            inputArray[random] = "";
            random = r.nextInt(51);
            }else{
                random = r.nextInt(52);
                i--;
            }
        }
        System.arraycopy(cardsValues, 0, cards, 0, 52);
        System.arraycopy(shuffled, 0, cards, 0, 52);
        return cards;
    }
    
    
    public String[] riffle(String[] inputArray){
        Random r = new Random();
        int random;
        random = r.nextInt(10)+21;
        ArrayList<String> one = new ArrayList<>(0); 
        for(int i =0;i<random;i++){
            one.add(inputArray[i]);
        }
        ArrayList<String> two = new ArrayList<>(0);
        for(int i =random;i<52;i++){
            two.add(inputArray[i]);
        }
        for(int i=0; i<52;i++){
                shuffled[i] = "";
        }
        boolean oneOrtwo = true;
        int ShuffledIndex =0;
        while((!one.isEmpty())|(!two.isEmpty())){
            int onesize = one.size();
            int twosize = two.size();
            random = r.nextInt(100);
            if(random<=70){
                random = 1;
            }
            if(random>70&random<=95){
                random = 2;
            }
            if(random>95){
                random = 3;
            }
            if(oneOrtwo==true&onesize!=0){
                for(int i=onesize-1;(i>=onesize-random)&(i>=0);i--){
                    shuffled[ShuffledIndex]=one.get(i);
                    one.remove(i);
                    ShuffledIndex++;
                }
                oneOrtwo=false;
                continue;
            }else{
                 oneOrtwo=false; 
                }
                
            if(oneOrtwo==false&twosize!=0){
                for(int i=twosize-1;(i>=twosize-random)&(i>=0);i--){
                    shuffled[ShuffledIndex]=two.get(i);
                    two.remove(i);
                    ShuffledIndex++;
                }
                oneOrtwo=true;
            }else{
                oneOrtwo=true;
            }   
        }
        System.arraycopy(shuffled, 0, cards, 0, 52);
        for(int i=0; i<52;i++){
                shuffled[i] = "";
        }
        return cards;
    }
    
    
    public String[] overhand(String[] inputArray){
        for(int i=0; i<52;i++){
                shuffled[i] = "";
        }
        int random;
        Random r = new Random();
        //L 45 maximum,  31 minimum
        //R 7 minimum, 21 maximum
        random = r.nextInt(14)+31;
        ArrayList<String> Left = new ArrayList<>(0); 
        for(int i =0;i<random;i++){
            Left.add(inputArray[i]);
        }
        ArrayList<String> Right = new ArrayList<>(0);
        for(int i =random;i<52;i++){
            Right.add(inputArray[i]);
        }
        while(!Left.isEmpty()){
            random = r.nextInt(6)+1;
            if(random>Left.size()){random=Left.size();}
            for(int i=Left.size()-random;i<Left.size();i++){
                Right.add(Left.get(i)); 
                 
            }
            int size = Left.size();
            for(int i=Left.size()-1;i>=size-random;i--){
                Left.remove(i);
            }
            
        }
        for(int i =0;i<52;i++){
            shuffled[i]=Right.get(i);
        }
        System.arraycopy(shuffled, 0, cards, 0, 52);
        for(int i=0; i<52;i++){
                shuffled[i] = "";
        }
        return cards;
    }
    
    
    public void deal(int numberOfCards, int numberOfplayers, String[] Cards){
        p1 = new String[numberOfCards];
        p2 = new String[numberOfCards];
        p3 = new String[numberOfCards];
        p4 = new String[numberOfCards];
        p5 = new String[numberOfCards];
        totalDealt=0;
        if(numberOfplayers ==2){
            int x=0;
            for(int i =Cards.length-1;i>=Cards.length-(numberOfCards*2);i--){
                p1[x] = Cards[i];
                i--;
                p2[x]=Cards[i];  
                x++;
                totalDealt=totalDealt+2;
            }
        }
        if(numberOfplayers ==3){
            int x=0;
            for(int i =Cards.length-1;i>=Cards.length-(numberOfCards*3);i--){
                p1[x] = Cards[i];
                i--;
                p2[x]=Cards[i];
                i--;
                p3[x]=Cards[i];
                x++;
                totalDealt=totalDealt+3;
            }   
        }
        if(numberOfplayers ==4){
            int x=0;
            for(int i =Cards.length-1;i>=Cards.length-(numberOfCards*4);i--){
                p1[x] = Cards[i];
                i--;
                p2[x]=Cards[i];
                i--;
                p3[x]=Cards[i];
                i--;
                p4[x]=Cards[i];
                x++;
                totalDealt=totalDealt+4;
            }   
        }
        if(numberOfplayers ==5){
            int x=0;
            for(int i =Cards.length-1;i>=Cards.length-(numberOfCards*5);i--){
                p1[x] = Cards[i];
                i--;
                p2[x]=Cards[i];
                i--;
                p3[x]=Cards[i];
                i--;
                p4[x]=Cards[i];
                i--;
                p5[x]=Cards[i];
                x++;
                totalDealt=totalDealt+5;
            }   
        }
        System.arraycopy(Cards, 0, cards, 0, 52);
        trump = cards[52-totalDealt];
        if(trump.toCharArray().length==3){
            char[] trumparray = new char[3];
            trumparray = trump.toCharArray();
            trump = Character.toString(trumparray[2]);
        }else{
            char[] trumparray = new char[2];
            trumparray = trump.toCharArray();
            trump = Character.toString(trumparray[1]);
        }
        switch(trump){
            case "H":
                trumpWord = "Hearts";
                break;
            case "S":
                trumpWord = "Spades";
                break;
            case "D":
                trumpWord = "Diamonds";
                break;
            case "C":
                trumpWord = "Clubs";
                break;
        }
    }
}


