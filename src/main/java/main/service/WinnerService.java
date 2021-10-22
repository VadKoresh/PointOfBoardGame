package main.service;

import main.entity.UserTochkiEntity;
import main.entity.WinnerEntity;
import main.model.Winner;
import main.repository.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WinnerService {

    @Autowired
    private WinnerRepository winnerRepository;

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

    public Winner updateWinner(UserTochkiEntity userTochkiEntity, int scoreUpdate){
        /**
         * TODO
         * необходимо посмотреть в скиллбоксе как сразу обновить таблицу
         */
        WinnerEntity winnerEntity = winnerRepository.findByUserTochkiEntity(userTochkiEntity);
        winnerEntity.setScore(winnerEntity.getScore() + scoreUpdate);
        return Winner.toModel(winnerRepository.save(winnerEntity));
    }
}
