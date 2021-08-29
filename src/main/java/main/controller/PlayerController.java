package main.controller;

import main.entity.BoardGameEntity;
import main.entity.PlayerEntity;
import main.entity.VictoryStoryEntity;
import main.entity.WinnerEntity;
import main.exception.PlayerAlreadyExistEx;
import main.exception.PlayerNotFoundException;
import main.model.BoardGame;
import main.model.Player;
import main.model.VictoryStory;
import main.model.Winner;
import main.service.PlayerService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/")
    public ResponseEntity addPlayers(@RequestBody PlayerEntity player) {
        try {
            playerService.addPlayer(player);
            return ResponseEntity.ok("Пользователь сохранился");
        } catch (PlayerAlreadyExistEx existEx){
            return ResponseEntity.badRequest().body(existEx.getMessage());
        }
        catch (Exception exception) {
            return ResponseEntity.badRequest().body("Произошла ошибка #1050");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable int id) {
        try {
            return ResponseEntity.ok(playerService.getOne(id));
        } catch (PlayerNotFoundException notFoundException){
            return ResponseEntity.badRequest().body(notFoundException.getMessage());
        }
        catch (Exception exception) {
            return ResponseEntity.badRequest().body("Произошла ошибка #1051");
        }
    }

    @GetMapping("/")
    public ResponseEntity getAll(){
        try {
            return ResponseEntity.ok(playerService.getAll());
        }
        catch (Exception exception) {
            return ResponseEntity.badRequest().body("Произошла ошибка #1052");
        }
    }

    @PutMapping("/")
    public ResponseEntity updatePlayerNameSurname(@RequestBody PlayerEntity playerEntityUpdate){
        try {
            return ResponseEntity.ok(playerService.updatePlayerNameSurname(playerEntityUpdate));
        }
        catch (Exception exception) {
            return ResponseEntity.badRequest().body("Произошла ошибка #1053");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePlayerFoto(@RequestParam("foto") MultipartFile foto,
                                           @PathVariable int id){
        try {
            return ResponseEntity.ok(playerService.updatePlayerFoto(foto, id));
        }
        catch (Exception exception) {
            return ResponseEntity.badRequest().body("Произошла ошибка #1054");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable int id){
        try {
            return ResponseEntity.ok(playerService.deletePlayer(id));
        }
        catch (Exception exception) {
            return ResponseEntity.badRequest().body("Произошла ошибка #1055");
        }
    }

    @GetMapping("/getall")
    public ResponseEntity gelJson(){
        JSONObject playerEntity = new JSONObject();
        JSONArray array = new JSONArray();
        array.add(new PlayerEntity());
        array.add(new Player());
        array.add(new BoardGameEntity());
        array.add(new BoardGame());
        array.add(new VictoryStoryEntity());
        array.add(new VictoryStory());
        array.add(new WinnerEntity());
        array.add(new Winner());
        return ResponseEntity.ok(array);
    }
}
