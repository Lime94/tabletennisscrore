package monopolybankir.com.tennisscore.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import monopolybankir.com.tennisscore.game.Game;
import monopolybankir.com.tennisscore.game.Player;
import monopolybankir.com.tennisscore.game.PlayerRange;
import monopolybankir.com.tennisscore.game.builderPattern.ReturnObject;
import monopolybankir.com.tennisscore.game.saverPattern.HistoryGameSaver;
import monopolybankir.com.tennisscore.game.statepattern.GameType;
import monopolybankir.com.tennisscore.utils.SharedPrefsUtil;


@InjectViewState
public class GameActivityPresenter extends MvpPresenter<IGameActivity> {

    Game game;
    Activity activity;
    HistoryGameSaver historyGame;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().onPresenterCreated();
    }

    public void initGame(Activity activity, String nameFirst, String nameSecond, GameType gameType) {
        game = new Game(nameFirst, nameSecond, gameType);
        this.activity = activity;
        getViewState().showPlayersName(
                game.getStateGame().getFirstPlayerName(),
                game.getStateGame().getGetSecondPlayerName());
        historyGame = new HistoryGameSaver();

        if (SharedPrefsUtil.getInstance(
                activity.getSharedPreferences(SharedPrefsUtil.class.getSimpleName(), Context.MODE_PRIVATE))
                .getPitcherDialogShowAgain()) {
            getViewState().showPitcherDialog();
        }
    }

    public void onIncrementScore(PlayerRange playerRange) {
        historyGame.saveStateGameHistory(game);
        game.incrementScore(playerRange);
        sendResult();
    }


    public void cancelLastAction() {
        Game lastStateGame = historyGame.getLastGameHistoryState();
        if (lastStateGame != null) {
            this.game = lastStateGame;
            sendResult();
        }
    }

    private void sendResult() {
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
