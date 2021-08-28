package main.service;

import main.entity.PlayerEntity;
import main.entity.WinnerEntity;
import main.model.Winner;
import main.repository.PlayerRepository;
import main.repository.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WinnerService {

    @Autowired
    private WinnerRepository winnerRepository;
    @Autowired
    private PlayerRepository playerRepository;

    public Winner addWinner(WinnerEntity winnerEntity, int idPlayer){
        PlayerEntity playerEntity = null;
        Optional<PlayerEntity> byId = playerRepository.findById(idPlayer);
        if (byId.isPresent()){
            playerEntity = byId.get();
        }
        winnerEntity.setPlayer(playerEntity);
        return Winner.toModel(winnerRepository.save(winnerEntity));
    }

    public List<Winner> getAll(){
        Iterable<WinnerEntity> all = winnerRepository.findAll();
        ArrayList<Winner> winnerArrayList = new ArrayList<>();
        for (WinnerEntity winnerEntity : all){
            winnerArrayList.add(Winner.toModel(winnerEntity));
        }
        return winnerArrayList;
    }

    public Winner updateWinner(int id, int scoreUpdate){
        /**
         * TODO
         * необходимо посмотреть в скиллбоксе как сразу обновить таблицу
         */
        WinnerEntity winnerEntity = null;
        Optional<WinnerEntity> byId = winnerRepository.findById(id);
        if (byId.isPresent()){
            winnerEntity = byId.get();
        }
        winnerEntity.setScore(winnerEntity.getScore() + scoreUpdate);
        return Winner.toModel(winnerRepository.save(winnerEntity));
    }
}
