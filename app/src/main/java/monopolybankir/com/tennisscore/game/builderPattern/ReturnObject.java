package monopolybankir.com.tennisscore.game.builderPattern;

import monopolybankir.com.tennisscore.game.PlayerRange;

public class ReturnObject {

    PlayerRange pitcherRange;
    String firstPlayerScore;
    String firstPlayerName;


    String secondPlayerScore;
    String getSecondPlayerName;

    public ReturnObject(PlayerRange pitcher, String firstPlayerScore, String firstPlayerName, String secondPlayerScore, String getSecondPlayerName) {
        this.pitcherRange = pitcher;
        this.firstPlayerScore = firstPlayerScore;
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerScore = secondPlayerScore;
        this.getSecondPlayerName = getSecondPlayerName;
    }

    public PlayerRange getPitcherRange() {
        return pitcherRange;
    }

    public String getFirstPlayerScore() {
        return firstPlayerScore;
    }

    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    public String getSecondPlayerScore() {
        return secondPlayerScore;
    }

    public String getGetSecondPlayerName() {
        return getSecondPlayerName;
    }
}
