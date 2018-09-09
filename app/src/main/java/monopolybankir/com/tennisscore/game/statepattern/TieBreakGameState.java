package monopolybankir.com.tennisscore.game.statepattern;

import android.util.Log;

import monopolybankir.com.tennisscore.game.Pitcher;
import monopolybankir.com.tennisscore.game.Player;
import monopolybankir.com.tennisscore.game.PlayerManager;
import monopolybankir.com.tennisscore.game.PlayerRange;
import monopolybankir.com.tennisscore.game.model.ReturnObject;
import monopolybankir.com.tennisscore.game.model.ReturnObjectBuilder;

public class TieBreakGameState extends AbstractState {

    String  firstPlayerState;
    String  secondPlayerState;

    public TieBreakGameState(CallBack callBack, PlayerManager playerManager, Pitcher pitcher, AbstractState nextState) {
        super(callBack, playerManager, pitcher, nextState);

        int firstScore  = playerManager.getPlayerByRange(PlayerRange.First).getScore();
        int secondScore = playerManager.getPlayerByRange(PlayerRange.Second).getScore();

        if(firstScore > secondScore){
            firstPlayerState = TieBrakeState.MORE.name();
            secondPlayerState = TieBrakeState.LESS.name();
        }else{
            firstPlayerState = TieBrakeState.LESS.name();
            secondPlayerState = TieBrakeState.MORE.name();
        }
    }

    @Override
    public void incrementScore(PlayerRange playerRange) {
        Player player = playerManager.getPlayerByRange(playerRange);
        isWinner(player);

        Player opponent = playerManager.getOpponent(pitcher.getCurrentPitcher());
        pitcher.setPitcher(opponent);

        isWinner(player);
    }


    private void isWinner(Player player){

        if(player.getRange().equals(PlayerRange.First) && firstPlayerState.equals(TieBrakeState.MORE.name())){
            Log.d("Winner", player.getName());
            return;
        }

        if(player.getRange().equals(PlayerRange.Second) && firstPlayerState.equals(TieBrakeState.MORE.name())){
            Log.d("Winner", player.getName());
            return;
        }
    }

    @Override
    public ReturnObject getStateGame() {
        return new ReturnObjectBuilder()
                .setFirstPlayerName(playerManager.getPlayerByRange(PlayerRange.First).getName())
                .setFirstPlayerScore(firstPlayerState)
                .setSecondPlayerName(playerManager.getPlayerByRange(PlayerRange.Second).getName())
                .setSecondPlayerScore(secondPlayerState)
                .setPitcher(pitcher.getCurrentPitcherRange())
                .build();
    }


    private enum TieBrakeState{
        MORE,
        LESS,
        EQUALY
    }
}
