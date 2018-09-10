package monopolybankir.com.tennisscore.game.statepattern;

import monopolybankir.com.tennisscore.game.Pitcher;
import monopolybankir.com.tennisscore.game.Player;
import monopolybankir.com.tennisscore.game.PlayerManager;
import monopolybankir.com.tennisscore.game.PlayerRange;
import monopolybankir.com.tennisscore.game.builderPattern.ReturnObject;
import monopolybankir.com.tennisscore.game.builderPattern.ReturnObjectBuilder;

public class PitcherState extends AbstractState {


    public PitcherState(CallBack callBack,
                 PlayerManager playerManager,
                 Pitcher pitcher,
                 AbstractState nextState) {

        super(callBack,playerManager,pitcher,nextState);
    }

    @Override
    public void incrementScore(PlayerRange playerRange) {
        Player player = playerManager.getPlayerByRange(playerRange);
        pitcher.setPitcher(player);
        callBack.onSetState(nextState);
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
}
