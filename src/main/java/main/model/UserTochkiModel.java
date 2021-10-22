package main.model;

import main.entity.UserTochkiEntity;

public class UserTochkiModel {
    private long id;
    private String name;
    private String surname;

    public static UserTochkiModel toModel(UserTochkiEntity userTochkiEntity){
        UserTochkiModel userTochkiModel = new UserTochkiModel();
        userTochkiModel.setId(userTochkiEntity.getId());
        userTochkiModel.setName(userTochkiEntity.getName());
        userTochkiModel.setSurname(userTochkiEntity.getSurname());
        return userTochkiModel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
