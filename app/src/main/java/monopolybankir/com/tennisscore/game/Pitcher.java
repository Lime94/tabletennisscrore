package monopolybankir.com.tennisscore.game;

public class Pitcher {

    private Player pitcherPlayer;

    public void setPitcher(Player pitcher){
        this.pitcherPlayer = pitcher;
    }

    public Player getCurrentPitcher(){
        return pitcherPlayer;
    }

    public PlayerRange getCurrentPitcherRange(){
        return pitcherPlayer.getRange();
    }
}
