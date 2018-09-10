package monopolybankir.com.tennisscore.mvp;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.orm.query.Select;

import java.util.List;

import monopolybankir.com.tennisscore.game.model.Winner;


@InjectViewState
public class CongratiulationsActivityPresenter extends MvpPresenter<ICongratulationsActivity>
{

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showProgressbar();

        List<Winner> winners = Select.from(Winner.class)
                .orderBy("id desc")
                .list();

        getViewState().hideProgressbar();
        getViewState().showHistory(winners);

    }
}
