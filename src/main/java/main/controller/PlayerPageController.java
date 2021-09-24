package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/player/page")
public class PlayerPageController {

    @RequestMapping("/{id}")
    public String getPagePlayer(@PathVariable int id, Model model){
        return "playerPage";
    }
}
