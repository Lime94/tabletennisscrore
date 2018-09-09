package monopolybankir.com.tennisscore.game.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import monopolybankir.com.tennisscore.game.Player;

public class Winner {

    Player winnerPlayer;
    Player losePlayer;
    String dateGame;

    public Winner(Player winnerPlayer, Player losePlayer) {
        this.winnerPlayer = winnerPlayer;
        this.losePlayer = losePlayer;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateGame = dateFormat.format(date);

    }

    public String getWinnerName() {
        return winnerPlayer.getName();
    }
    public String getLosePlayerName(){
        return losePlayer.getName();
    }

    public int getWinnerScore(){
        return  winnerPlayer.getScore();
    }

    public int getLoseScore(){
       return losePlayer.getScore();
    }
}
