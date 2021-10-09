package main.repository;

import main.entity.BoardGameEntity;
import main.entity.PlayerEntity;
import main.entity.VictoryStoryEntity;
import main.model.BoardGame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VictoryStoryRepository extends CrudRepository<VictoryStoryEntity, Integer> {
    List<VictoryStoryEntity> findByBoardGameEntity(BoardGameEntity boardGameEntity);
    List<VictoryStoryEntity> findByPlayer(PlayerEntity playerEntity);
}
