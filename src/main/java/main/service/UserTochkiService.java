package main.service;

import main.entity.UserTochkiEntity;
import main.exception.PlayerNotFoundException;
import main.model.UserTochkiModel;
import main.repository.UserTochkiRepository;
import main.repository.VictoryStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserTochkiService {

    @Autowired
    private UserTochkiRepository userTochkiRepository;

    public List<UserTochkiModel> getAll() {
        Iterable<UserTochkiEntity> all = userTochkiRepository.findAll();
        ArrayList<UserTochkiModel> userTochkiModelArrayList = new ArrayList<>();
        for (UserTochkiEntity userTochkiEntity : all) {
            userTochkiModelArrayList.add(UserTochkiModel.toModel(userTochkiEntity));
        }
        return userTochkiModelArrayList;
    }

    public UserTochkiEntity getOneForPage(long id) throws PlayerNotFoundException {
        UserTochkiEntity userTochkiEntity = null;
        Optional<UserTochkiEntity> byId = userTochkiRepository.findById(id);
        if (!byId.isPresent()) {
            throw new PlayerNotFoundException("Пользователь не найден!");
        }
        userTochkiEntity = byId.get();
        return userTochkiEntity;
    }

    public UserTochkiModel getOne(long id) throws PlayerNotFoundException {
        UserTochkiEntity userTochkiEntity = null;
        Optional<UserTochkiEntity> byId = userTochkiRepository.findById(id);
        if (!byId.isPresent()) {
            throw new PlayerNotFoundException("Пользователь не найден!");
        }
        userTochkiEntity = byId.get();
        return UserTochkiModel.toModel(userTochkiEntity);
    }
}
