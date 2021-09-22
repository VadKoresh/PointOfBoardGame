package main.service;

import main.entity.PlayerEntity;
import main.entity.WinnerEntity;
import main.model.Winner;
import main.repository.PlayerRepository;
import main.repository.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WinnerService {

    @Autowired
    private WinnerRepository winnerRepository;
    @Autowired
    private PlayerRepository playerRepository;

    /**
     * ОБЯЗАТЕЛЬНО ПРОВЕРИТЬ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    public Winner addWinner(PlayerEntity playerEntity){
        WinnerEntity winnerEntity = new WinnerEntity();
        winnerEntity.setPlayer(playerEntity);
        return Winner.toModel(winnerRepository.save(winnerEntity));
    }

    /**
     * @return Возвращает отсортированный список по общим очкам
     */
    public List<Winner> getAll(){
        Iterable<WinnerEntity> all = winnerRepository.findAll();
        ArrayList<Winner> winnerArrayList = new ArrayList<>();
        for (WinnerEntity winnerEntity : all){
            winnerArrayList.add(Winner.toModel(winnerEntity));
        }
        return winnerArrayList.stream()
                .filter(winner -> winner.getScore() > 0)
                .sorted(Comparator.comparing(Winner::getScore).reversed())
                .collect(Collectors.toList());
    }

    public Winner updateWinner(PlayerEntity player, int scoreUpdate){
        /**
         * TODO
         * необходимо посмотреть в скиллбоксе как сразу обновить таблицу
         */
        WinnerEntity winnerEntity = winnerRepository.findByPlayer(player);
        winnerEntity.setScore(winnerEntity.getScore() + scoreUpdate);
        return Winner.toModel(winnerRepository.save(winnerEntity));
    }
}
