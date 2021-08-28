package main.controller;

import main.entity.WinnerEntity;
import main.service.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/winners")
public class WinnerController {

    @Autowired
    private WinnerService winnerService;

    @GetMapping("/")
    public ResponseEntity getAll(){
        try{
            return ResponseEntity.ok(winnerService.getAll());
        }catch (Exception exception){
            return ResponseEntity.badRequest().body("Произошла ошибка #1056");
        }
    }
}
