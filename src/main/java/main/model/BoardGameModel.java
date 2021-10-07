package main.model;

import main.entity.BoardGameEntity;

public class BoardGameModel {

    private int id;
    private String name;

    public static BoardGameModel toModel(BoardGameEntity boardGameEntity){
        BoardGameModel boardGameModel = new BoardGameModel();
        boardGameModel.setId(boardGameEntity.getId());
        boardGameModel.setName(boardGameEntity.getName());
        return boardGameModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
