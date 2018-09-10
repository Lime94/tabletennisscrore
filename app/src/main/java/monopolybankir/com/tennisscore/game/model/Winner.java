package monopolybankir.com.tennisscore.game.model;

import com.orm.SugarRecord;

public class Winner extends SugarRecord<Winner> {

    public String dateGame;

    public String nameWinner;

    public String nameLooser;

    public String scoreWinner;

    public String scoreLoser;

    public Winner(String dateGame, String nameWinner, String nameLooser, String scoreWinner, String scoreLoser) {
        this.dateGame = dateGame;
        this.nameWinner = nameWinner;
        this.nameLooser = nameLooser;
        this.scoreWinner = scoreWinner;
        this.scoreLoser = scoreLoser;
    }

    public Winner(){
        //for SugarORM
    }
}
