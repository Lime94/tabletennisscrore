package monopolybankir.com.tennisscore.game.statepattern;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import monopolybankir.com.tennisscore.game.Pitcher;
import monopolybankir.com.tennisscore.game.Player;
import monopolybankir.com.tennisscore.game.PlayerManager;
import monopolybankir.com.tennisscore.game.PlayerRange;
import monopolybankir.com.tennisscore.game.model.ReturnObject;
import monopolybankir.com.tennisscore.game.model.ReturnObjectBuilder;
import monopolybankir.com.tennisscore.game.model.Winner;

public class TieBreakGameState extends AbstractState {

    int firstScore;
    int secondScore;

    String tieBreakLabelFirst;
    String tieBreakLabelSecond;


    public TieBreakGameState(CallBack callBack, PlayerManager playerManager, Pitcher pitcher) {
        super(callBack, playerManager, pitcher);

        firstScore = playerManager.getPlayerByRange(PlayerRange.First).getScore();
        secondScore = playerManager.getPlayerByRange(PlayerRange.Second).getScore();

        setScoreLabel();
    }

    @Override
    public void incrementScore(PlayerRange playerRange) {
        Player player = playerManager.getPlayerByRange(playerRange);
        Player opponent = playerManager.getOpponent(pitcher.getCurrentPitcher());
        pitcher.setPitcher(opponent);

        if (playerRange.equals(PlayerRange.First))
            firstScore++;
        else
            secondScore++;

        setScoreLabel();

        isWinner(player);
    }


    private void isWinner(Player player) {

        int goalDiffenece = Math.abs(secondScore - firstScore);


        if (goalDiffenece >= 2)
            EventBus.getDefault().post(new Winner(player,playerManager.getOpponent(player)));
    }


    private void setScoreLabel() {
        if (firstScore > secondScore) {
            tieBreakLabelFirst = TieBrakeState.MORE.name();
            tieBreakLabelSecond = TieBrakeState.LESS.name();

        } else if (firstScore < secondScore) {
            tieBreakLabelFirst = TieBrakeState.LESS.name();
            tieBreakLabelSecond = TieBrakeState.MORE.name();

        } else if (firstScore == secondScore) {
            tieBreakLabelSecond = TieBrakeState.EQUALY.name();
            tieBreakLabelFirst = TieBrakeState.EQUALY.name();
        }
    }

    @Override
    public ReturnObject getStateGame() {
        return new ReturnObjectBuilder()
                .setFirstPlayerName(playerManager.getPlayerByRange(PlayerRange.First).getName())
                .setFirstPlayerScore(tieBreakLabelFirst)
                .setSecondPlayerName(playerManager.getPlayerByRange(PlayerRange.Second).getName())
                .setSecondPlayerScore(tieBreakLabelSecond)
                .setPitcher(pitcher.getCurrentPitcherRange())
                .build();
    }


    private enum TieBrakeState {
        MORE,
        LESS,
        EQUALY
    }
}
