package monopolybankir.com.tennisscore.views;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.databinding.DataBindingUtil;
import monopolybankir.com.tennisscore.R;
import monopolybankir.com.tennisscore.databinding.PitcherInfoDialogBinding;
import monopolybankir.com.tennisscore.utils.SharedPrefsUtil;

public class PitcherInfoDialog extends AppCompatDialogFragment {

    PitcherInfoDialogBinding bnd;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        bnd = DataBindingUtil.inflate(
                inflater, R.layout.pitcher_info_dialog, container, false);

        bnd.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefsUtil.setPitcherDialogShowAgain(!bnd.cbDontShowAgain.isChecked());
                dismiss();
            }
        });
        return bnd.getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {

            //TODO сделать ширину 80% от экрана
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

}
