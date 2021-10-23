package main.controller.rest;

import javassist.NotFoundException;
import main.entity.BoardGameEntity;
import main.exception.BoardGameAlreadyExistEx;
import main.service.BoardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boardgame")
public class BoardGameControllerRest {

    @Autowired
    private BoardGameService boardGameService;

    @GetMapping("/{id}")
    public ResponseEntity getOneBoardGame(@PathVariable int id){
        try {
            return ResponseEntity.ok(boardGameService.getOneBoardGame(id));
        } catch (NotFoundException notFoundException){
            return ResponseEntity.badRequest().body(notFoundException.getMessage());
        }
        catch (Exception exception) {
            return ResponseEntity.badRequest().body("Произошла ошибка #1058");
        }
    }

    @GetMapping("/")
    public ResponseEntity getAllBoardGame(){
        try {
            return ResponseEntity.ok(boardGameService.getAllBoardGame());
        }
        catch (Exception exception) {
            return ResponseEntity.badRequest().body("Произошла ошибка #1059");
        }
    }


    @GetMapping("/listpointgame")
    public ResponseEntity getAllBoardGameWithListPoint(){
        try {
            return ResponseEntity.ok(boardGameService.getAllBoardGameWithListScore());
        }
        catch (Exception exception) {
            return ResponseEntity.badRequest().body("Произошла ошибка #1160");
        }
    }
}
