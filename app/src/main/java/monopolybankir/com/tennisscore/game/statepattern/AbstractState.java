package monopolybankir.com.tennisscore.game.statepattern;

import monopolybankir.com.tennisscore.game.model.ReturnObject;
import monopolybankir.com.tennisscore.game.Pitcher;
import monopolybankir.com.tennisscore.game.PlayerManager;
import monopolybankir.com.tennisscore.game.PlayerRange;

public abstract class AbstractState {

    PlayerManager playerManager;
    Pitcher pitcher;
    CallBack callBack;
    AbstractState nextState;

    AbstractState(CallBack callBack, PlayerManager playerManager, Pitcher pitcher, AbstractState nextState){
        this.playerManager = playerManager;
        this.pitcher = pitcher;
        this.callBack = callBack;
        this.nextState = nextState;
    }

    AbstractState(CallBack callBack, PlayerManager playerManager, Pitcher pitcher){
        this.playerManager = playerManager;
        this.pitcher = pitcher;
        this.callBack = callBack;
    }

    public abstract void incrementScore(PlayerRange playerRange);

    public abstract ReturnObject getStateGame();
}
