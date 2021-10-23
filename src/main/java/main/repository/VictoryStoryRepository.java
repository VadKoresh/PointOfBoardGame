package main.repository;

import main.entity.BoardGameEntity;
import main.entity.UserTochkiEntity;
import main.entity.VictoryStoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VictoryStoryRepository extends CrudRepository<VictoryStoryEntity, Integer> {
    List<VictoryStoryEntity> findByBoardGameEntity(BoardGameEntity boardGameEntity);
    List<VictoryStoryEntity> findByUserTochkiEntity(UserTochkiEntity userTochkiEntity);
}
