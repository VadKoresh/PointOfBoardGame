package main.model;

import main.entity.VictoryStoryEntity;

import java.time.LocalDateTime;

public class VictoryStory {

    private int id;
    private Player player;
    private BoardGame boardGame;
    private LocalDateTime date;
    private int score;

    public static VictoryStory toModel(VictoryStoryEntity victoryStoryEntity){
        VictoryStory victoryStory = new VictoryStory();
        victoryStory.setId(victoryStoryEntity.getId());
        victoryStory.setBoardGame(BoardGame.toModel(victoryStoryEntity.getBoardGameEntity()));
        victoryStory.setPlayer(Player.toModel(victoryStoryEntity.getPlayer()));
        victoryStory.setDate(victoryStoryEntity.getDate());
        victoryStory.setScore(victoryStoryEntity.getScore());
        return victoryStory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public BoardGame getBoardGame() {
        return boardGame;
    }

    public void setBoardGame(BoardGame boardGame) {
        this.boardGame = boardGame;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
