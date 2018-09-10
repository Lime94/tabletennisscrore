package monopolybankir.com.tennisscore.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import monopolybankir.com.tennisscore.R;
import monopolybankir.com.tennisscore.databinding.RvHistoryGameDateItemBinding;
import monopolybankir.com.tennisscore.databinding.RvHistoryGameItemBinding;
import monopolybankir.com.tennisscore.game.builderPattern.GameStats;

public class HistoryGameAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private final int dateType = 0;
    private final int gameType = 1;
    List<GameStats> gameStats;

    public HistoryGameAdapter(List<GameStats> gameStats){
        this.gameStats = gameStats;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final  RvHistoryGameItemBinding b = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.rv_history_game_item, parent, false);
        return new GameViewHolder(b);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((GameViewHolder)holder).bind(gameStats.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return gameType;
    }

    @Override
    public int getItemCount() {
        return gameStats.size();
    }

    class GameViewHolder extends RecyclerView.ViewHolder {
        RvHistoryGameItemBinding binding;

        GameViewHolder(RvHistoryGameItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(GameStats gameStats){
            binding.tvFirstPlayerName.setText(gameStats.nameWinner);
            binding.tvSecondPlayerName.setText(gameStats.nameLooser);

            binding.tvFirstPlayerScore.setText(gameStats.scoreWinner);
            binding.tvSecondPlayerScore.setText(gameStats.scoreLoser);


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
