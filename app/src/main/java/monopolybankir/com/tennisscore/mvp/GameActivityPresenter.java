package monopolybankir.com.tennisscore.mvp;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import monopolybankir.com.tennisscore.game.Game;
import monopolybankir.com.tennisscore.game.PlayerRange;
import monopolybankir.com.tennisscore.game.model.ReturnObject;
import monopolybankir.com.tennisscore.game.statepattern.GameType;


@InjectViewState
public class GameActivityPresenter extends MvpPresenter<IGameActivity>  {

    Game game;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showPitcherDialog();
        getViewState().onPresenterCreated();
    }

    public void initGame(String nameFirst, String nameSecond, GameType gameType) {
        game = new Game(nameFirst,nameSecond,gameType);
    }

    public  void onIncrementScore(PlayerRange playerRange){
        game.incrementScore(playerRange);

        ReturnObject stateGame = game.getStateGame();

        getViewState().showPitcher(stateGame.getPitcherRange());

        String scorePlayerOne = stateGame.getFirstPlayerScore();
        String scorePlayerTwo = stateGame.getSecondPlayerScore();
        getViewState().showScore(scorePlayerOne, scorePlayerTwo);
    }
}
