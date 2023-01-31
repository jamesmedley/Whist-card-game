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
public class HandAnalyse {
    String[] hand,trick;
    String trumps;
    int[] illegalCards = new int[7];
    
    HandAnalyse(String[] INhand, String INtrump,String[] INtrick){
        hand = INhand;
        trumps = INtrump;
        trick = INtrick;
        
    }
    
   
    public int[] analyse(){
        if(trick[0]==null){
            return illegalCards;
        }
        String currentsuit ;
        char[] firstcard = trick[0].toCharArray();
        if(firstcard.length == 3){
            currentsuit = Character.toString(firstcard[2]);
        }else{
            currentsuit = Character.toString(firstcard[1]);
        }
        for(int i=0;i<hand.length;i++){
            if("".equals(hand[i])){
                continue;
            }
            String itemsuit;
            
            char[] item = hand[i].toCharArray();
            if(item.length == 3){
                itemsuit = Character.toString(item[2]);
            }else{
                itemsuit = Character.toString(item[1]);
            }
            int x=0;
            while((illegalCards[x]!=0)&&(x<=7)){
                x++;
            }        
            if((!itemsuit.equals(currentsuit))){
                if(i==0){
                    illegalCards[x] = 999;
                }else{
                    illegalCards[x] = i;
                }
            }
        }
        int x=0;
        for(int i=0;i<illegalCards.length;i++){
            if(illegalCards[x]!=0){
                x++;
            }
        }
        /*
        while((illegalCards[x]!=0)&&(x<6)){
            x++;
        }
        */
        int y=0;
        for(int i=0;i<hand.length;i++){
           if(!"".equals(hand[i])){
               y++;
           } 
        }
        if(x==y){
            for(int i=0;i<illegalCards.length;i++){
                illegalCards[i]=0;
            }
        }
        return illegalCards;
    }
}
