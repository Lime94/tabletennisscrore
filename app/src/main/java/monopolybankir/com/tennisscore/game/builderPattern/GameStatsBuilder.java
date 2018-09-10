package monopolybankir.com.tennisscore.game.builderPattern;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import monopolybankir.com.tennisscore.game.Player;

public class GameStatsBuilder {

    Player winnerPlayer;
    Player losePlayer;
    String dateGame;

    public GameStatsBuilder(Player winnerPlayer, Player losePlayer) {
        this.winnerPlayer = winnerPlayer;
        this.losePlayer = losePlayer;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateGame = dateFormat.format(date);

    }


    public GameStats convertToGameStats(){
        return new GameStats(
                dateGame,
                winnerPlayer.getName(),
                losePlayer.getName(),
                String.valueOf(winnerPlayer.getScore()),
                String.valueOf(losePlayer.getScore()));
    }
}
