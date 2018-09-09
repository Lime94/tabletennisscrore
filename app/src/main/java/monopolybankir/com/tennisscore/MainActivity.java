package monopolybankir.com.tennisscore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import monopolybankir.com.tennisscore.databinding.ActivityMainBinding;
import monopolybankir.com.tennisscore.game.statepattern.GameType;
import monopolybankir.com.tennisscore.views.ChooseDurationGameDialog;


public class MainActivity extends AppCompatActivity implements ChooseDurationGameDialog.CallBack{

    ActivityMainBinding bnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnd = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(bnd.toolbar);


        bnd.content.btnStartGame.setOnClickListener(view ->
                new ChooseDurationGameDialog().show(getSupportFragmentManager(),"choose_duration"));
    }

    @Override
    public void onUserChoseGame(GameType gameType) {
                        GameActivity.start(
                        MainActivity.this,
                        bnd.content.etFirstPlayerName.getText().toString(),
                        bnd.content.etSecondPlayerName.getText().toString(),
                        gameType
                );
    }
}


