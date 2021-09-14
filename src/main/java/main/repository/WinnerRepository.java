package main.repository;

import main.entity.PlayerEntity;
import main.entity.WinnerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinnerRepository extends CrudRepository<WinnerEntity, Integer> {
    WinnerEntity findByPlayer(PlayerEntity player);
}
