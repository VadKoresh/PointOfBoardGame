package main.controller;

import javassist.NotFoundException;
import main.entity.BoardGameEntity;
import main.exception.BoardGameAlreadyExistEx;
import main.model.BoardGame;
import main.service.BoardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boardgame/page")
public class BoardGameController {

    @Autowired
    private BoardGameService boardGameService;

    @GetMapping("/creategame")
    public String createGamePage(Model model){
        model.addAttribute("boardGameEntity", new BoardGameEntity());
        return "addGame";
    }

    @PostMapping("/boardgame")
    public String addBoardGame(@ModelAttribute("boardGameEntity") BoardGameEntity boardGameEntity){
        try {
            boardGameService.addBoardGame(boardGameEntity);
        } catch (BoardGameAlreadyExistEx e) {
            return e.getMessage();
        }
        return "redirect:/boardgame/";
    }

    @GetMapping("/{id}")
    public String getOneBoardGame(@PathVariable int id, Model model){
        BoardGame oneBoardGame = null;
        try {
            oneBoardGame = boardGameService.getOneBoardGame(id);
        } catch (NotFoundException notFoundException){
            return notFoundException.getMessage();
        }

        model.addAttribute("boardGame", oneBoardGame);
        return "boardGame";
    }

}
