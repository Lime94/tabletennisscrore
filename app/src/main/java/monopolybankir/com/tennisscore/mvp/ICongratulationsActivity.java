package monopolybankir.com.tennisscore.mvp;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import monopolybankir.com.tennisscore.game.builderPattern.GameStats;

public interface ICongratulationsActivity extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showHistory(List<GameStats> gameStats);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgressbar();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideProgressbar();


}



