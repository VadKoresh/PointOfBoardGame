package main.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "victory_story")
public class VictoryStoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_victory_story")
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_user_tochki")
    private UserTochkiEntity userTochkiEntity;
    @ManyToOne
    @JoinColumn(name = "id_board_game")
    private BoardGameEntity boardGameEntity;
    private LocalDateTime date;
    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserTochkiEntity getUserTochkiEntity() {
        return userTochkiEntity;
    }

    public void setUserTochkiEntity(UserTochkiEntity userTochkiEntity) {
        this.userTochkiEntity = userTochkiEntity;
    }

    public BoardGameEntity getBoardGameEntity() {
        return boardGameEntity;
    }

    public void setBoardGameEntity(BoardGameEntity boardGameEntity) {
        this.boardGameEntity = boardGameEntity;
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
