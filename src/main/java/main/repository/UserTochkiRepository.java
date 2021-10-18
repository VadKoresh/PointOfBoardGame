package main.repository;

import main.entity.UserTochkiEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTochkiRepository extends CrudRepository<UserTochkiEntity, Long> {
}
