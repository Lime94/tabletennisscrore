package monopolybankir.com.tennisscore.game;

import java.io.Serializable;


public class Pitcher implements  Serializable {

    private Player pitcherPlayer;

    public void setPitcher(Player pitcher){
        this.pitcherPlayer = pitcher;
    }

    public Player getCurrentPitcher(){
        return pitcherPlayer;
    }

    public PlayerRange getCurrentPitcherRange(){
        if(pitcherPlayer != null){
            return pitcherPlayer.getRange();
        }
        return null;
    }
}
