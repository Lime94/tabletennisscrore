package monopolybankir.com.tennisscore.game.statepattern;

import android.util.Log;

import monopolybankir.com.tennisscore.game.Pitcher;
import monopolybankir.com.tennisscore.game.Player;
import monopolybankir.com.tennisscore.game.PlayerManager;
import monopolybankir.com.tennisscore.game.PlayerRange;
import monopolybankir.com.tennisscore.game.model.ReturnObject;
import monopolybankir.com.tennisscore.game.model.ReturnObjectBuilder;

public class LargeGameState extends AbstractState {

    public LargeGameState(CallBack callBack, PlayerManager playerManager, Pitcher pitcher, AbstractState nextState) {
        super(callBack, playerManager, pitcher, nextState);
    }

    @Override
    public void incrementScore(PlayerRange playerRange) {
        Player player = playerManager.getPlayerByRange(playerRange);
        player.scoreIncrement();

        if(isNeedChangePitcher()){
            Player opponent = playerManager.getOpponent(pitcher.getCurrentPitcher());
            pitcher.setPitcher(opponent);
        }

        isWinner(player);
    }

    @Override
    public ReturnObject getStateGame() {
        return new ReturnObjectBuilder()
                .setFirstPlayerName(playerManager.getPlayerByRange(PlayerRange.First).getName())
                .setFirstPlayerScore(String.valueOf(playerManager.getPlayerByRange(PlayerRange.First).getScore()))
                .setSecondPlayerName(playerManager.getPlayerByRange(PlayerRange.Second).getName())
                .setSecondPlayerScore(String.valueOf(playerManager.getPlayerByRange(PlayerRange.Second).getScore()))
                .setPitcher(pitcher.getCurrentPitcherRange())
                .build();
    }

    public boolean isNeedChangePitcher(){
        int totalScore = playerManager.getTotalScore();
        if(totalScore % 5==0)
            return true;

        return false;
    }


    private void isWinner(Player player){
        Player opponent = playerManager.getOpponent(player);

        int goalDiffenece = player.getScore() - opponent.getScore();

        if(player.getScore()>= 21){
            if (goalDiffenece >= 2)
                Log.d("Winner", player.getName());
            else
                callBack.onSetState(nextState); //tieBreak
        }
    }

}
