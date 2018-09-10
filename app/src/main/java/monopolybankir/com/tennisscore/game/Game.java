package monopolybankir.com.tennisscore.game;

import java.util.ArrayList;
import java.util.List;

import monopolybankir.com.tennisscore.game.builderPattern.ReturnObject;
import monopolybankir.com.tennisscore.game.statepattern.AbstractState;
import monopolybankir.com.tennisscore.game.statepattern.CallBack;
import monopolybankir.com.tennisscore.game.statepattern.GameType;
import monopolybankir.com.tennisscore.game.statepattern.LargeGameState;
import monopolybankir.com.tennisscore.game.statepattern.PitcherState;
import monopolybankir.com.tennisscore.game.statepattern.ShortGameState;

public class Game implements CallBack {

    private AbstractState state;
    private PlayerManager playerManager;
    private Pitcher pitcher;

    public Game(String nameFirst, String nameSecond, GameType typeGame){
        List playerList = new ArrayList();
        playerList.add(new Player(nameFirst,PlayerRange.First));
        playerList.add(new Player(nameSecond,PlayerRange.Second));
        playerManager = new PlayerManager(playerList);
        pitcher = new Pitcher();

        AbstractState nextState;
        switch (typeGame) {
            case SHORT:
                nextState = new ShortGameState(this, playerManager, pitcher);
                break;
            case LARGE:
                nextState = new LargeGameState(this, playerManager, pitcher);
                break;
            default:
                nextState = new LargeGameState(this, playerManager, pitcher);
                break;
        }

        state = new PitcherState(this,playerManager,pitcher,nextState);

    }


    public void onReverseRange(){
        playerManager.reverseRange();
    }

    @Override
    public void onSetState(AbstractState state) {
        this.state = state;
    }

    public void incrementScore(PlayerRange range){
        state.incrementScore(range);
    }

    public ReturnObject getStateGame(){
        return state.getStateGame();
    }
}
