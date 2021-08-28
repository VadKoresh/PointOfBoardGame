package main.repository;

import main.entity.BoardGameEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardGameRepository extends CrudRepository<BoardGameEntity, Integer> {
    BoardGameEntity findByName(String name);
}
