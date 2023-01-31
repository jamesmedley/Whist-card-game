/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whist;
/**
 *
 * @author james
 */
public class TrickAnalyse {
    String[] trick;
    String trump,value,winningCard;
    String suitInPlay,suit;
    char[] card;
    String[] mainCards1, mainCards2, cardValues, PicCardsOrdered, finalSorted;
    int[] NumberCardsOrdered;
    boolean trumpPresent = false;
    TrickAnalyse(String[] trickIn, String trumpIn){
        trick = trickIn;
        trump = trumpIn;
        mainCards1 = new String[trick.length];
    }
    
    private void sortRelavence(){
        card = trick[0].toCharArray();
        if(card.length==3){
            suitInPlay = Character.toString(card[2]);
        }else{
            suitInPlay = Character.toString(card[1]);
        }
        for(int i =0;i<trick.length;i++){
            card = trick[i].toCharArray();
            if(card.length==3){
                suit =Character.toString(card[2]);
            }else{
                suit = Character.toString(card[1]);
                
            }
            int count=0;
            for(int x=0;x<mainCards1.length;x++) {
                if (mainCards1[x] != null) {
                    count++;
                }
            }
            if(suit.equals(suitInPlay)||suit.equals(trump)){
                mainCards1[count] = trick[i];
            }
        }
        
    }
    
    private void trumpSort(){
        sortRelavence(); 
        int trumpCount=0;
        for(int i=0;i<mainCards1.length;i++){
            try{
                card = mainCards1[i].toCharArray();
            }catch(Exception e){
                continue;
            }
            if(card.length==3){
                suit =Character.toString(card[2]);   
            }else{
                suit = Character.toString(card[1]);
            }
            if(suit.equals(trump)){
                trumpCount++;   
            }
        }
        if(trumpCount>0){
            trumpPresent = true;
            mainCards2 = new String[trumpCount];
            for(int i=0;i<mainCards1.length;i++){
                try{
                    card = mainCards1[i].toCharArray();
                }catch(Exception e){
                    continue;
                }
                if(card.length==3){
                    suit =Character.toString(card[2]);
                }else{
                    suit = Character.toString(card[1]);
                }


                if(suit.equals(trump)){
                    int count=0;
                    for(int x=0;x<mainCards2.length;x++) {
                        if (mainCards2[x] != null) {
                            count++;
                        }
                    }  
                    mainCards2[count]=mainCards1[i];

                }
            }
        }else{
            mainCards2 = new String[mainCards1.length];
            System.arraycopy(mainCards1, 0, mainCards2, 0, mainCards1.length);
        }
    }
    
    public int analyse(){
        trumpSort();
        int count=0;
        for(int x=0;x<mainCards2.length;x++) {
            if (mainCards2[x] != null) {
                count++;
            }
        } 
        cardValues = new String[count];
        for(int i=0;i<count;i++){
            card = mainCards2[i].toCharArray(); 
            if(card.length==3){
                    value =Character.toString(card[0])+Character.toString(card[1]);
            }else{
                value = Character.toString(card[0]);
            }
            cardValues[i]=value;
        }
        PicCardsOrdered = new String[cardValues.length];
        for(int i=0;i<cardValues.length;i++){
            if("A".equals(cardValues[i])){
                PicCardsOrdered[0]=cardValues[i];
                break;
            }    
        }
        count=0;
        for(int x=0;x<PicCardsOrdered.length;x++) {
            if (PicCardsOrdered[x] != null) {
                count++;
            }
        }
        for(int i=0;i<cardValues.length;i++){
            if("K".equals(cardValues[i])){
                PicCardsOrdered[count]=cardValues[i];
                break;
            }    
        }
        count=0;
        for(int x=0;x<PicCardsOrdered.length;x++) {
            if (PicCardsOrdered[x] != null) {
                count++;
            }
        }
        for(int i=0;i<cardValues.length;i++){
            if("Q".equals(cardValues[i])){
                PicCardsOrdered[count]=cardValues[i];
                break;
            }    
        }
        count=0;
        for(int x=0;x<PicCardsOrdered.length;x++) {
            if (PicCardsOrdered[x] != null) {
                count++;
            }
        }
        for(int i=0;i<cardValues.length;i++){
            if("J".equals(cardValues[i])){
                PicCardsOrdered[count]=cardValues[i];
                break;
            }    
        }
        count=0;
        for(int x=0;x<PicCardsOrdered.length;x++) {
            if (PicCardsOrdered[x] != null) {
                count++;
            }
        }
        int totalPics = count;
        NumberCardsOrdered = new int[cardValues.length-count];
        for(int i=0;i<cardValues.length;i++){
            count=0;
            for(int x=0;x<NumberCardsOrdered.length;x++) {
                if (NumberCardsOrdered[x] != 0) {
                    count++;
                }
            }   
            if(!"A".equals(cardValues[i])&&!"K".equals(cardValues[i])&&!"Q".equals(cardValues[i])&&!"J".equals(cardValues[i])){
                NumberCardsOrdered[count] = Integer.parseInt(cardValues[i]);
            }
        } 
        for(int i=0;i<NumberCardsOrdered.length;i++){
            int temp;
            int n = NumberCardsOrdered.length;
            while(n>0){
                for(int x=0;x<NumberCardsOrdered.length-1;x++){
                    if(NumberCardsOrdered[x]<NumberCardsOrdered[x+1]){
                        temp = NumberCardsOrdered[x+1];
                        NumberCardsOrdered[x+1]  = NumberCardsOrdered[x];
                        NumberCardsOrdered[x] = temp;
                    }
                } 
                n--;
            }
        }        
        finalSorted= new String[totalPics+NumberCardsOrdered.length];
        for(int i=0;i<totalPics;i++){
            finalSorted[i] = PicCardsOrdered[i];
        }
        for(int i=0;i<NumberCardsOrdered.length;i++){
            finalSorted[i+totalPics] = Integer.toString(NumberCardsOrdered[i]);
        }
        winningCard = finalSorted[0]; 
        if(trumpPresent==true){
            winningCard = winningCard+trump;
        }else{
            winningCard = winningCard+suitInPlay;
        }
        int position=0;
        for(int i=0;i<trick.length;i++){
            if(trick[i].equals(winningCard)){
                position = i;
                break;
            }
        }  
        return position;
    }
}