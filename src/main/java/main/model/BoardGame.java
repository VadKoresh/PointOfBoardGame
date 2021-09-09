package main.model;

import main.entity.BoardGameEntity;

public class BoardGame {

    private int id;
    private String info;
    private byte[] foto;
    private int firstPlace;
    private int secondPlace;
    private int thirdPlace;
    private String name;

    public static BoardGame toModel(BoardGameEntity boardGameEntity){
        BoardGame boardGame = new BoardGame();
        boardGame.setFirstPlace(boardGameEntity.getFirstPlace());
        boardGame.setFoto(boardGameEntity.getFoto());
        boardGame.setId(boardGameEntity.getId());
        boardGame.setInfo(boardGameEntity.getInfo());
        boardGame.setSecondPlace(boardGameEntity.getSecondPlace());
        boardGame.setThirdPlace(boardGameEntity.getThirdPlace());
        boardGame.setName(boardGameEntity.getName());
        return boardGame;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public int getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(int firstPlace) {
        this.firstPlace = firstPlace;
    }

    public int getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(int secondPlace) {
        this.secondPlace = secondPlace;
    }

    public int getThirdPlace() {
        return thirdPlace;
    }

    public void setThirdPlace(int thirdPlace) {
        this.thirdPlace = thirdPlace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
