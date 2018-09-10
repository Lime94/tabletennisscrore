package monopolybankir.com.tennisscore.game.statepattern;

import org.greenrobot.eventbus.EventBus;

import monopolybankir.com.tennisscore.game.Pitcher;
import monopolybankir.com.tennisscore.game.Player;
import monopolybankir.com.tennisscore.game.PlayerManager;
import monopolybankir.com.tennisscore.game.PlayerRange;
import monopolybankir.com.tennisscore.game.builderPattern.GameStatsBuilder;
import monopolybankir.com.tennisscore.game.builderPattern.ReturnObject;
import monopolybankir.com.tennisscore.game.builderPattern.ReturnObjectBuilder;

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
            EventBus.getDefault().post(new GameStatsBuilder(player,playerManager.getOpponent(player)));
    }


    private void setScoreLabel() {
        if (firstScore > secondScore) {
            tieBreakLabelFirst = TieBrakeState.MORE.getLabel();
            tieBreakLabelSecond = TieBrakeState.LESS.getLabel();

        } else if (firstScore < secondScore) {
            tieBreakLabelFirst = TieBrakeState.LESS.getLabel();
            tieBreakLabelSecond = TieBrakeState.MORE.getLabel();

        } else if (firstScore == secondScore) {
            tieBreakLabelSecond = TieBrakeState.EQUALY.getLabel();
            tieBreakLabelFirst = TieBrakeState.EQUALY.getLabel();
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
        MORE("AD"),
        LESS(" "),
        EQUALY("DEUS");

        String state;
        TieBrakeState(String  state){
            this.state = state;
        }

        String getLabel(){
            return state;
        }

    }
}
