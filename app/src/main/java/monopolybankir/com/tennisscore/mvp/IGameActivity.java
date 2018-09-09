package monopolybankir.com.tennisscore.mvp;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import monopolybankir.com.tennisscore.game.PlayerRange;


public interface IGameActivity extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showPitcherDialog();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onPresenterCreated();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showPitcher(PlayerRange range);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showScore(String playerOneScore, String playerTwoScore);

}
