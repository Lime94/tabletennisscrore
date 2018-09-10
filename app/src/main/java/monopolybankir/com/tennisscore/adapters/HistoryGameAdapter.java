package monopolybankir.com.tennisscore.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import monopolybankir.com.tennisscore.R;
import monopolybankir.com.tennisscore.databinding.RvHistoryGameDateItemBinding;
import monopolybankir.com.tennisscore.databinding.RvHistoryGameItemBinding;
import monopolybankir.com.tennisscore.game.model.Winner;

public class HistoryGameAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private final int dateType = 0;
    private final int gameType = 1;
    List<Winner> winners;

    public HistoryGameAdapter(List<Winner> winners){
        this.winners = winners;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final  RvHistoryGameItemBinding b = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.rv_history_game_item, parent, false);
        return new GameViewHolder(b);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((GameViewHolder)holder).bind(winners.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return gameType;
    }

    @Override
    public int getItemCount() {
        return winners.size();
    }

    class GameViewHolder extends RecyclerView.ViewHolder {
        RvHistoryGameItemBinding binding;

        GameViewHolder(RvHistoryGameItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Winner winner){
            binding.tvFirstPlayerName.setText(winner.nameWinner);
            binding.tvSecondPlayerName.setText(winner.nameLooser);

            binding.tvFirstPlayerScore.setText(winner.scoreWinner);
            binding.tvSecondPlayerScore.setText(winner.scoreLoser);


        }
    }


    class DateViewHolder extends RecyclerView.ViewHolder {

        RvHistoryGameDateItemBinding binding;

        DateViewHolder(RvHistoryGameDateItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
