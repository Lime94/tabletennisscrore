package monopolybankir.com.tennisscore.mvp;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import monopolybankir.com.tennisscore.game.Game;
import monopolybankir.com.tennisscore.game.PlayerRange;


@InjectViewState
public class GameActivityPresenter extends MvpPresenter<IGameActivity> {

    Game game;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showPitcherDialog();
        getViewState().onPresenterCreated();
    }

    public void initGame(String nameFirst, String nameSecond) {
        game = new Game(nameFirst,nameSecond);
    }


    public void setPitcher(PlayerRange range){
        game.setPitcher(range);
    }
}
