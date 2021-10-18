package main.model;

import main.entity.WinnerEntity;

public class Winner {

    private int score;
    private int numberSeason;
    private UserTochkiModel player;

    public static Winner toModel(WinnerEntity winnerEntity){
        Winner winner = new Winner();
        winner.setScore(winnerEntity.getScore());
        winner.setNumberSeason(winnerEntity.getNumberSeason());
        winner.setPlayer(UserTochkiModel.toModel(winnerEntity.getUserTochkiEntity()));
        return winner;
    }

    public UserTochkiModel getPlayer() {
        return player;
    }

    public void setPlayer(UserTochkiModel player) {
        this.player = player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumberSeason() {
        return numberSeason;
    }

    public void setNumberSeason(int season) {
        this.numberSeason = season;
    }
}
