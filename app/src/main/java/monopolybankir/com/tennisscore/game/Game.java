package monopolybankir.com.tennisscore.game;

import java.util.ArrayList;
import java.util.List;

public class Game {


    PlayerManager playerManager;
    Pitcher pitcher;

    public Game(String nameFirst, String nameSecond){
        List playerList = new ArrayList();
        playerList.add(new Player(nameFirst,PlayerRange.First));
        playerList.add(new Player(nameSecond,PlayerRange.Second));

        playerManager = new PlayerManager(playerList);
    }

    public void setPitcher(PlayerRange playerRange){
        Player player = playerManager.getPlayerByRange(playerRange);
        pitcher.setPitcher(player);
    }


    public void incrementScore(PlayerRange range){
        playerManager.incrementScore(range);

        if(isNeedChangePitcher()){
            Player candidatToPitcher = playerManager.getOpponent(pitcher.getCurrentPitcher());

            if(candidatToPitcher != null)
                pitcher.setPitcher(candidatToPitcher);
        }
    }

    public boolean isNeedChangePitcher(){
        int totalScore = playerManager.getTotalScore();
        if(totalScore % 5==0)
            return true;

        return false;
    }


    public void onReverseRange(){
        playerManager.reverseRange();
    }
}
