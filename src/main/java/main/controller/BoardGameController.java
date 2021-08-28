package main.controller;

import javassist.NotFoundException;
import main.entity.BoardGameEntity;
import main.exception.BoardGameAlreadyExistEx;
import main.service.BoardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boardgame")
public class BoardGameController {

    @Autowired
    private BoardGameService boardGameService;

    @PutMapping("/")
    public ResponseEntity addBoardGame(@RequestBody BoardGameEntity boardGameEntity){
        try {
            boardGameService.addBoardGame(boardGameEntity);
            return ResponseEntity.ok("Настольная игра сохранилась");
        } catch (BoardGameAlreadyExistEx existEx){
            return ResponseEntity.badRequest().body(existEx.getMessage());
        }
        catch (Exception exception) {
            return ResponseEntity.badRequest().body("Произошла ошибка #1057");
        }
    }

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

    /**
     * @param boardGameEntity
     * необходимо найти по имени и изменить только очки
     * @return
     */
    @PutMapping("/")
    public ResponseEntity updatePointPlace(@RequestBody BoardGameEntity boardGameEntity){
        try {
            return ResponseEntity.ok(boardGameService.updatePointPlace(boardGameEntity));
        }
        catch (Exception exception) {
            return ResponseEntity.badRequest().body("Произошла ошибка #1060");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBoardGame(@PathVariable int id){
        try {
            boardGameService.deleteBoardGame(id);
            return ResponseEntity.ok("Игра успешно удалена!");
        }
        catch (Exception exception) {
            return ResponseEntity.badRequest().body("Произошла ошибка #1061");
        }
    }
}
