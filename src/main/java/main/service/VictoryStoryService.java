package main.service;

import main.entity.VictoryStoryEntity;
import main.exception.PlayerNotFoundException;
import main.model.VictoryStory;
import main.repository.VictoryStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VictoryStoryService {

    @Autowired
    private VictoryStoryRepository victoryStoryRepository;
    @Autowired
    private WinnerService winnerService;
    @Autowired
    private UserTochkiService userTochkiService;

    public void addStory(List<VictoryStoryEntity> victoryStoryEntityList, LocalDateTime dateTime){
        for (VictoryStoryEntity victoryStoryEntity : victoryStoryEntityList){
            victoryStoryEntity.setDate(dateTime);
            victoryStoryRepository.save(victoryStoryEntity);
            winnerService.updateWinner(victoryStoryEntity.getUserTochkiEntity(), victoryStoryEntity.getScore());
        }
    }

    public List<VictoryStory> getStoryPlayer(long idUserTochki) throws PlayerNotFoundException {
        ArrayList<VictoryStory> victoryStoryArrayList = new ArrayList<>();
        for (VictoryStoryEntity victoryStoryEntity : victoryStoryRepository.findByUserTochkiEntity(userTochkiService.getOneForPage(idUserTochki))){
            victoryStoryArrayList.add(VictoryStory.toModel(victoryStoryEntity));
        }
        return victoryStoryArrayList;
    }
}
