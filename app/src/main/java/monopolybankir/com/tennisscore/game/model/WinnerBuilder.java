package monopolybankir.com.tennisscore.game.model;

import com.orm.SugarRecord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import monopolybankir.com.tennisscore.game.Player;

public class WinnerBuilder  {

    Player winnerPlayer;
    Player losePlayer;
    String dateGame;

    public WinnerBuilder(Player winnerPlayer, Player losePlayer) {
        this.winnerPlayer = winnerPlayer;
        this.losePlayer = losePlayer;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateGame = dateFormat.format(date);

    }


    public Winner convertToWinner(){
        return new Winner(
                dateGame,
                winnerPlayer.getName(),
                losePlayer.getName(),
                String.valueOf(winnerPlayer.getScore()),
                String.valueOf(losePlayer.getScore()));
    }
}
