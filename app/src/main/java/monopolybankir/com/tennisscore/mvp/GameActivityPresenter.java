package monopolybankir.com.tennisscore.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import monopolybankir.com.tennisscore.game.Game;
import monopolybankir.com.tennisscore.game.PlayerRange;
import monopolybankir.com.tennisscore.game.model.ReturnObject;
import monopolybankir.com.tennisscore.game.statepattern.GameType;


@InjectViewState
public class GameActivityPresenter extends MvpPresenter<IGameActivity>  {

    Game game;
    Activity activity;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showPitcherDialog();
        getViewState().onPresenterCreated();
    }

    public void initGame(Activity activity, String nameFirst, String nameSecond, GameType gameType) {
        game = new Game(nameFirst,nameSecond,gameType);
        this.activity = activity;
        getViewState().showPlayersName(
                game.getStateGame().getFirstPlayerName(),
                game.getStateGame().getGetSecondPlayerName());
    }

    public  void onIncrementScore(PlayerRange playerRange){
        game.incrementScore(playerRange);


        Vibrator v = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        if (v.hasVibrator())
            v.vibrate(100);


        ReturnObject stateGame = game.getStateGame();
        getViewState().showPitcher(stateGame.getPitcherRange());
        String scorePlayerOne = stateGame.getFirstPlayerScore();
        String scorePlayerTwo = stateGame.getSecondPlayerScore();
        getViewState().showScore(scorePlayerOne, scorePlayerTwo);
    }
}
