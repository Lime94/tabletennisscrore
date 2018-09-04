package monopolybankir.com.tennisscore;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import monopolybankir.com.tennisscore.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding bnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnd = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(bnd.toolbar);
    }
}


