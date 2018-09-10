package monopolybankir.com.tennisscore;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import monopolybankir.com.tennisscore.databinding.ActivityGameBinding;
import monopolybankir.com.tennisscore.game.PlayerRange;
import monopolybankir.com.tennisscore.game.builderPattern.GameStatsBuilder;
import monopolybankir.com.tennisscore.game.statepattern.GameType;
import monopolybankir.com.tennisscore.mvp.GameActivityPresenter;
import monopolybankir.com.tennisscore.mvp.IGameActivity;
import monopolybankir.com.tennisscore.views.MvpAppCompatActivity;
import monopolybankir.com.tennisscore.views.PitcherInfoDialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class GameActivity extends MvpAppCompatActivity implements IGameActivity {

    @InjectPresenter
    GameActivityPresenter gameActivityPresenter;

    ActivityGameBinding bnd;

    public static final String FLAG_FIRST_PLAYER_NAME = "1";
    public static final String FLAG_SECOND_PLAYER_NAME = "2";
    public static final String FLAG_GAME_TYPE = "3";


    public static void start(Activity activity, String nameFirst, String nameSecond,GameType gameType) {

        Intent intent = new Intent(activity, GameActivity.class);
        if(!nameFirst.isEmpty())
        intent.putExtra(FLAG_FIRST_PLAYER_NAME, nameFirst);

        if(!nameSecond.isEmpty())
        intent.putExtra(FLAG_SECOND_PLAYER_NAME, nameSecond);

        intent.putExtra(FLAG_GAME_TYPE,gameType);
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
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPresenterCreated() {
        String name1 = getIntent().getExtras().getString(FLAG_FIRST_PLAYER_NAME, "Player1");
        String name2 = getIntent().getExtras().getString(FLAG_SECOND_PLAYER_NAME, "Player2");
        GameType gameType = (GameType) getIntent().getExtras().getSerializable(FLAG_GAME_TYPE);
        gameActivityPresenter.initGame(this, name1, name2, gameType);
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
    public void showPlayersName(String playerOneName, String playerTwoName) {
        bnd.tvPlayerOneName.setText(playerOneName);
        bnd.tvPlayerTwoName.setText(playerTwoName);
    }


    @Override
    public void showPitcherDialog() {
        new PitcherInfoDialog().show(getSupportFragmentManager(), PitcherInfoDialog.class.getSimpleName());
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWinnerDetermined(GameStatsBuilder gameStatsBuilder){
        gameStatsBuilder.convertToGameStats().save();
        Intent intent = new Intent(this, CongratulationsActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.really_want_to_close_game)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GameActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       dialog.dismiss();
                    }
                });
        builder.show();
    }
}
