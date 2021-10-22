package main.entity;

import javax.persistence.*;

@Entity
@Table(name = "winner")
public class WinnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_winner")
    private int id;
    private int score;
    @Column(name = "season")
    private int numberSeason;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user_tochki")
    private UserTochkiEntity userTochkiEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setNumberSeason(int numberSeason) {
        this.numberSeason = numberSeason;
    }

    public UserTochkiEntity getUserTochkiEntity() {
        return userTochkiEntity;
    }

    public void setUserTochkiEntity(UserTochkiEntity userTochkiEntity) {
        this.userTochkiEntity = userTochkiEntity;
    }
}
