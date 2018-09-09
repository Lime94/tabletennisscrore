package monopolybankir.com.tennisscore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import monopolybankir.com.tennisscore.databinding.ActivityGameBinding;
import monopolybankir.com.tennisscore.game.PlayerRange;
import monopolybankir.com.tennisscore.game.statepattern.GameType;
import monopolybankir.com.tennisscore.mvp.GameActivityPresenter;
import monopolybankir.com.tennisscore.mvp.IGameActivity;
import monopolybankir.com.tennisscore.views.MvpAppCompatActivity;
import monopolybankir.com.tennisscore.views.PitcherInfoDialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;


public class GameActivity extends MvpAppCompatActivity implements IGameActivity {

    @InjectPresenter
    GameActivityPresenter gameActivityPresenter;

    ActivityGameBinding bnd;

    public static final String FLAG_FIRST_PLAYER_NAME = "1";
    public static final String FLAG_SECOND_PLAYER_NAME = "2";


    public static void start(Activity activity, String nameFirst, String nameSecond) {
        Intent intent = new Intent(activity, GameActivity.class);
        intent.putExtra(FLAG_FIRST_PLAYER_NAME, nameFirst);
        intent.putExtra(FLAG_SECOND_PLAYER_NAME, nameSecond);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnd = DataBindingUtil.setContentView(this, R.layout.activity_game);
        bnd.firstSide.setOnClickListener(view -> gameActivityPresenter.onIncrementScore(PlayerRange.First));
        bnd.secondSide.setOnClickListener(view -> gameActivityPresenter.onIncrementScore(PlayerRange.Second));
    }


    @Override
    public void onPresenterCreated() {
        String name1 = getIntent().getExtras().getString(FLAG_FIRST_PLAYER_NAME, "Player1");
        String name2 = getIntent().getExtras().getString(FLAG_SECOND_PLAYER_NAME, "Player2");
        gameActivityPresenter.initGame(name1, name2, GameType.LARGE);
    }

    @Override
    public void showPitcher(PlayerRange range) {
        bnd.llSecondPlayerPitcher.setVisibility(range.equals(PlayerRange.Second) ? View.VISIBLE : View.INVISIBLE);
        bnd.llFirstPlayerPitcher.setVisibility(range.equals(PlayerRange.First) ? View.VISIBLE : View.INVISIBLE);

    }

    @Override
    public void showScore(String playerOneScore, String playerTwoScore) {
        bnd.tvScoreFirstPlayer.setText(playerOneScore);
        bnd.tvScoreSecondPlayer.setText(playerTwoScore);
    }

    @Override
    public void showPitcherDialog() {
        new PitcherInfoDialog().show(getSupportFragmentManager(), PitcherInfoDialog.class.getSimpleName());
    }
}
