package main.model;

import main.entity.VictoryStoryEntity;

import java.time.LocalDateTime;

public class VictoryStory {

    private int id;
    private BoardGameModel boardGameModel;
    private String date;
    private int score;

    public static VictoryStory toModel(VictoryStoryEntity victoryStoryEntity) {
        VictoryStory victoryStory = new VictoryStory();
        victoryStory.setId(victoryStoryEntity.getId());
        victoryStory.setBoardGame(BoardGameModel.toModel(victoryStoryEntity.getBoardGameEntity()));
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

    public BoardGameModel getBoardGame() {
        return boardGameModel;
    }

    public void setBoardGame(BoardGameModel boardGameModel) {
        this.boardGameModel = boardGameModel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
