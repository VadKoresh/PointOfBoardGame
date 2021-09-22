package main.model;

import main.entity.BoardGameEntity;

import java.util.ArrayList;

public class BoardGameListPoint {

    private int id;
    private String name;
    private ArrayList<Integer> points = new ArrayList<>();

    public static BoardGameListPoint toModelWithListPoint(BoardGameEntity boardGameEntity){
        BoardGameListPoint boardGameListPoint = new BoardGameListPoint();
        boardGameListPoint.setId(boardGameEntity.getId());
        boardGameListPoint.setName(boardGameEntity.getName());
        boardGameListPoint.getPoints().add(boardGameEntity.getFirstPlace());
        if (boardGameEntity.getSecondPlace() != 0){
            boardGameListPoint.getPoints().add(boardGameEntity.getSecondPlace());
        }
        if (boardGameEntity.getThirdPlace() != 0){
            boardGameListPoint.getPoints().add(boardGameEntity.getThirdPlace());
        }
        return boardGameListPoint;
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

    public ArrayList<Integer> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Integer> points) {
        this.points = points;
    }
}
