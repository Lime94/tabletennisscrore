package monopolybankir.com.tennisscore.game;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {

    private List<Player> players;

    public PlayerManager(List<Player> players) {
        this.players = players;
    }


    public void incrementScore(PlayerRange playerRange){
        getPlayerByRange(playerRange).scoreIncrement();
    }


    public Player getPlayerByRange(PlayerRange playerRange){
        for(Player player: players){
            if(player.getRange().equals(playerRange))
                return player;
        }
        return null;
    }

    public int getTotalScore(){
        int totalScore = 0;

        for(Player player: players)
            totalScore += player.getScore();

        return totalScore;
    }



    public void reverseRange(){
        for(Player player: players)
            player.changeRange();
    }


    public Player getOpponent(Player player){
        if (player.getRange().equals(PlayerRange.First)){
            return getPlayerByRange(PlayerRange.Second);
        }else {
            return getPlayerByRange(PlayerRange.First);
        }
    }



}
