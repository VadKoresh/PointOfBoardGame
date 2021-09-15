package main.model;

import main.entity.PlayerEntity;

import java.util.Arrays;
import java.util.Objects;

public class Player implements Comparable<Player>{
    private int id;
    private String name;
    private String surname;
    private byte[] foto;

    public static Player toModelForPagePlayer(PlayerEntity playerEntity){
        Player player = new Player();
        player.setId(playerEntity.getId());
        player.setName(playerEntity.getName());
        player.setSurname(playerEntity.getSurname());
        player.setFoto(playerEntity.getFoto());
        return player;
    }

    public static Player toModel(PlayerEntity playerEntity){
        Player player = new Player();
        player.setId(playerEntity.getId());
        player.setName(playerEntity.getName());
        player.setSurname(playerEntity.getSurname());
        return player;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public int compareTo(Player o) {
        return Integer.compare(id, o.id);
    }
}
