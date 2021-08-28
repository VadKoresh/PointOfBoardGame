package main.model;

import main.entity.WinnerEntity;

public class Winner {

    private int score;
    private int numberSeason;
    private Player player;

    public static Winner toModel(WinnerEntity winnerEntity){
        Winner winner = new Winner();
        winner.setScore(winnerEntity.getScore());
        winner.setNumberSeason(winnerEntity.getNumberSeason());
        winner.setPlayer(Player.toModel(winnerEntity.getPlayer()));
        return winner;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
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
