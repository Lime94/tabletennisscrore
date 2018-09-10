package monopolybankir.com.tennisscore.game.builderPattern;

import com.orm.SugarRecord;

public class GameStats extends SugarRecord<GameStats> {

    public String dateGame;

    public String nameWinner;

    public String nameLooser;

    public String scoreWinner;

    public String scoreLoser;

    public GameStats(String dateGame, String nameWinner, String nameLooser, String scoreWinner, String scoreLoser) {
        this.dateGame = dateGame;
        this.nameWinner = nameWinner;
        this.nameLooser = nameLooser;
        this.scoreWinner = scoreWinner;
        this.scoreLoser = scoreLoser;
    }

    public GameStats(){
        //for SugarORM
    }
}
