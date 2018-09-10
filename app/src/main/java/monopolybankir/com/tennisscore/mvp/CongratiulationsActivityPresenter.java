package monopolybankir.com.tennisscore.mvp;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.orm.query.Select;

import java.util.List;

import monopolybankir.com.tennisscore.game.builderPattern.GameStats;


@InjectViewState
public class CongratiulationsActivityPresenter extends MvpPresenter<ICongratulationsActivity>
{

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showProgressbar();

        List<GameStats> gameStats = Select.from(GameStats.class)
                .orderBy("id desc")
                .list();

        getViewState().hideProgressbar();
        getViewState().showHistory(gameStats);

    }
}
