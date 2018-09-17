package monopolybankir.com.tennisscore.game.saverPattern;

import android.util.Log;

import java.util.ArrayList;

import monopolybankir.com.tennisscore.game.Game;

public class HistoryGameSaver {

    ArrayList<Game> gameState;


    public void saveStateGameHistory(Game game){
        if(gameState == null){
            gameState = new ArrayList<>();
        }


        Log.d("HistoryGameSaver" ,"Player1 :"+
                game.getStateGame().getFirstPlayerScore()+
                "Player2"+ game.getStateGame().getSecondPlayerScore()
        );


        gameState.add(game.deepClone(game));
    }

    public Game getLastGameHistoryState() {
        if(gameState != null && gameState.size()>0){
            Game lastState = gameState.get(gameState.size()-1);
            gameState.remove(lastState);
            return lastState;
        }
        return null;
    }
}
