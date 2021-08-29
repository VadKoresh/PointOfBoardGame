package main.repository;

import main.entity.VictoryStoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VictoryStoryRepository extends CrudRepository<VictoryStoryEntity, Integer> {
}
