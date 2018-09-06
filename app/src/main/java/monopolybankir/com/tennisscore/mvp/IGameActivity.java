package monopolybankir.com.tennisscore.mvp;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


public interface IGameActivity extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showPitcherDialog();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onPresenterCreated();

}
