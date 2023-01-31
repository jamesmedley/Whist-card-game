/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whist;

import java.util.Random;

/**
 *
 * @author james
 */
public class ScoreAnalyse {
    int [] score;
    int[] winners,losers;
    ScoreAnalyse(int[] scoreinput){
        score = scoreinput;
    }
    
    public int analyse(){
        int winner=0;
        for(int i=0;i<score.length;i++){
            if(score[i]>=winner){
                winner=score[i];
            }
        }
        int winnercount=0;
        for(int i=0;i<score.length;i++){
            if(score[i]==winner){
                winnercount++;
            }
        }
        int y=0;
        winners = new int[winnercount];
        for(int x=0;x<winnercount;x++){
            for(int i=y;i<score.length;i++){
                if(score[i]==winner){
                    winners[x] = i;
                    y=i+1;
                    break;
                }
            }
        }
        int losercount=0;
        for(int i=0;i<score.length;i++){
            if(score[i]==0){
                losercount++;
            }
        }
        y=0;
        losers = new int[losercount];
        for(int x=0;x<losercount;x++){
            for(int i=y;i<score.length;i++){
                if(score[i]==0){
                    losers[x] = i;
                    y=i+1;
                    break;
                }
            }
        }
        Random rand = new Random();
        int r = rand.nextInt(winnercount);
        return winners[r];
    }
}


