package monopolybankir.com.tennisscore.game.statepattern;

import org.greenrobot.eventbus.EventBus;

import monopolybankir.com.tennisscore.game.Pitcher;
import monopolybankir.com.tennisscore.game.Player;
import monopolybankir.com.tennisscore.game.PlayerManager;
import monopolybankir.com.tennisscore.game.PlayerRange;
import monopolybankir.com.tennisscore.game.builderPattern.GameStatsBuilder;
import monopolybankir.com.tennisscore.game.builderPattern.ReturnObject;
import monopolybankir.com.tennisscore.game.builderPattern.ReturnObjectBuilder;

public class LargeGameState extends AbstractState {

    public LargeGameState(CallBack callBack, PlayerManager playerManager, Pitcher pitcher) {
        super(callBack, playerManager, pitcher);
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

        int goalDiffenece = Math.abs(player.getScore() - opponent.getScore());

        if(player.getScore()>= 21){
            if (goalDiffenece >= 2) {
                EventBus.getDefault().post(new GameStatsBuilder(player,opponent));
            }
            else
                callBack.onSetState(new TieBreakGameState( callBack,  playerManager,  pitcher)); //tieBreak
        }
    }

}
