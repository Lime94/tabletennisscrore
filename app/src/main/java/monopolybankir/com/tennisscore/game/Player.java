package monopolybankir.com.tennisscore.game;

import java.io.Serializable;

public class Player implements Serializable {

    private String      name;
    private int         score;
    private PlayerRange range;

    public Player(String name,PlayerRange range) {
        this.name = name;
        this.range = range;
    }
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public PlayerRange getRange() {
        return range;
    }


    public void scoreIncrement(){
        score++;
    }

    public void changeRange() {
        range = (range.equals(PlayerRange.First)) ? PlayerRange.Second : PlayerRange.First;
    }



}
