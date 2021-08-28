package main.repository;

import main.entity.WinnerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinnerRepository extends CrudRepository<WinnerEntity, Integer> {
}
