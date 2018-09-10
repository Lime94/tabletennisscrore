package monopolybankir.com.tennisscore;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import monopolybankir.com.tennisscore.adapters.HistoryGameAdapter;
import monopolybankir.com.tennisscore.databinding.ActivityCongratulationsBinding;
import monopolybankir.com.tennisscore.game.model.Winner;
import monopolybankir.com.tennisscore.mvp.CongratiulationsActivityPresenter;
import monopolybankir.com.tennisscore.mvp.ICongratulationsActivity;
import monopolybankir.com.tennisscore.views.MvpAppCompatActivity;

import android.util.Log;
import android.view.View;

import java.util.List;

public class CongratulationsActivity extends MvpAppCompatActivity implements ICongratulationsActivity {

    @InjectPresenter
    CongratiulationsActivityPresenter congratiulationsActivityPresenter;

    ActivityCongratulationsBinding bnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnd = DataBindingUtil.setContentView(this, R.layout.activity_congratulations);
        setSupportActionBar(bnd.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void showHistory(List<Winner> winners) {
        bnd.content.rvHistory.setVisibility(View.VISIBLE);
        bnd.content.rvHistory.setAdapter( new HistoryGameAdapter(winners));
    }

    @Override
    public void showProgressbar() {
        bnd.content.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        bnd.content.progressBar.setVisibility(View.GONE);
    }
}
