package monopolybankir.com.tennisscore;

import androidx.appcompat.app.AppCompatActivity;
import monopolybankir.com.tennisscore.mvp.GameActivityPresenter;
import monopolybankir.com.tennisscore.mvp.IGameActivity;
import monopolybankir.com.tennisscore.views.MvpAppCompatActivity;
import monopolybankir.com.tennisscore.views.PitcherInfoDialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;


public class GameActivity extends MvpAppCompatActivity implements IGameActivity {

    @InjectPresenter
    GameActivityPresenter gameActivityPresenter;

    public static final String FLAG_FIRST_PLAYER_NAME = "1";
    public static final String FLAG_SECOND_PLAYER_NAME = "2";


    public static void start(Activity activity, String nameFirst, String nameSecond){
        Intent intent = new Intent(activity,GameActivity.class);
        intent.putExtra(FLAG_FIRST_PLAYER_NAME,nameFirst);
        intent.putExtra(FLAG_SECOND_PLAYER_NAME,nameSecond);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }


    @Override
    public void onPresenterCreated() {

        //TODO: можно сделать текст вертикальным при помощи каретки /n
        String name1 = getIntent().getExtras().getString(FLAG_FIRST_PLAYER_NAME,"Player1");
        String name2 = getIntent().getExtras().getString(FLAG_SECOND_PLAYER_NAME, "Player2");
        gameActivityPresenter.initGame(name1,name2);
    }

    @Override
    public void showPitcherDialog() {
        new PitcherInfoDialog().show(getSupportFragmentManager(),PitcherInfoDialog.class.getSimpleName());
    }
}
