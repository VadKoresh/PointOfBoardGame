package main.controller;

import main.entity.VictoryStoryEntity;
import main.service.BoardGameService;
import main.service.UserTochkiService;
import main.service.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tournament")
public class TournamentTableController {

    @Autowired
    WinnerService winnerService;
    @Autowired
    BoardGameService boardGameService;
    @Autowired
    UserTochkiService playerService;

    @GetMapping("/")
    public String createGamePage(Model model){
        model.addAttribute("victory", new VictoryStoryEntity());

        model.addAttribute("winners", winnerService.getAll());
        model.addAttribute("boardGames", boardGameService.getAllBoardGame());
        model.addAttribute("players",playerService.getAll());
        return "tournamentTable";
    }
}
