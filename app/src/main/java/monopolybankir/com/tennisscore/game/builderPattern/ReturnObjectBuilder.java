package monopolybankir.com.tennisscore.game.builderPattern;

import monopolybankir.com.tennisscore.game.PlayerRange;

public class ReturnObjectBuilder {

    PlayerRange pitcher;
    String firstPlayerScore;
    String firstPlayerName;


    String secondPlayerScore;
    String getSecondPlayerName;

    public ReturnObjectBuilder setPitcher(PlayerRange pitcher) {
        this.pitcher = pitcher;
        return this;
    }

    public ReturnObjectBuilder setFirstPlayerScore(String firstPlayerScore) {
        this.firstPlayerScore = firstPlayerScore;
        return this;
    }

    public ReturnObjectBuilder setFirstPlayerName(String firstPlayerName) {
        this.firstPlayerName = firstPlayerName;
        return this;
    }

    public ReturnObjectBuilder setSecondPlayerScore(String secondPlayerScore) {
        this.secondPlayerScore = secondPlayerScore;
        return this;
    }

    public ReturnObjectBuilder setSecondPlayerName(String getSecondPlayerName) {
        this.getSecondPlayerName = getSecondPlayerName;
        return this;
    }

    public ReturnObject build(){
        return  new ReturnObject(pitcher,firstPlayerScore,firstPlayerName,secondPlayerScore,getSecondPlayerName);
    }
}
