package monopolybankir.com.tennisscore.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import monopolybankir.com.tennisscore.R;
import monopolybankir.com.tennisscore.databinding.ChooseDurationGameDialogBinding;
import monopolybankir.com.tennisscore.game.statepattern.GameType;


import static androidx.databinding.DataBindingUtil.inflate;

public class ChooseDurationGameDialog extends BottomSheetDialogFragment {



    ChooseDurationGameDialogBinding bnd;
    CallBack callBack;



    public interface CallBack{
       void onUserChoseGame(GameType gameType);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callBack = (CallBack)context;
        }catch (Exception e){
            Toast.makeText(context,"Реализуй интерфейс", Toast.LENGTH_LONG).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bnd = inflate(inflater, R.layout.choose_duration_game_dialog, container, false);

        bnd.largeLabel.setOnClickListener(v-> callBack.onUserChoseGame(GameType.LARGE));
        bnd.llshortlabel.setOnClickListener(v-> callBack.onUserChoseGame(GameType.SHORT));

        return bnd.getRoot();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(requireContext(),getTheme());
    }

    @Override
    public int getTheme() {
        return  R.style.BottomSheetDialogTheme;
    }



}
