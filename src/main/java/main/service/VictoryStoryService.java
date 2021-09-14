package main.service;

import main.entity.VictoryStoryEntity;
import main.repository.VictoryStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VictoryStoryService {

    @Autowired
    private VictoryStoryRepository victoryStoryRepository;
    @Autowired
    private WinnerService winnerService;

    public void addStory(List<VictoryStoryEntity> victoryStoryEntityList){
        for (VictoryStoryEntity victoryStoryEntity : victoryStoryEntityList){
            victoryStoryRepository.save(victoryStoryEntity);
            winnerService.updateWinner(victoryStoryEntity.getPlayer(), victoryStoryEntity.getScore());
        }
    }
}
