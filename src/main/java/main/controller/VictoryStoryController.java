package main.controller;

import main.entity.VictoryStoryEntity;
import main.exception.BoardGameAlreadyExistEx;
import main.exception.PlayerAlreadyExistEx;
import main.model.VictoryStory;
import main.service.VictoryStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/story")
public class VictoryStoryController {

    @Autowired
    private VictoryStoryService victoryStoryService;

    @PostMapping("/")
    public ResponseEntity addStory(List<VictoryStoryEntity> victoryStoryEntityList){
        try {
            victoryStoryService.addStory(victoryStoryEntityList);
            return ResponseEntity.ok("Победные очки записаны!");
        }
        catch (Exception exception) {
            return ResponseEntity.badRequest().body("Произошла ошибка #1070");
        }
    }
}
