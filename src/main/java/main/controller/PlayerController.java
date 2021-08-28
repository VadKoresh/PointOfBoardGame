package main.controller;

import main.entity.PlayerEntity;
import main.exception.PlayerAlreadyExistEx;
import main.exception.PlayerNotFoundException;
import main.model.Player;
import main.service.PlayerService;
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
    public ResponseEntity updatePlayerFoto(@RequestParam("foto") MultipartFile file,
                                           @PathVariable int id){
        try {
            return ResponseEntity.ok(playerService.updatePlayerFoto(file, id));
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
}
