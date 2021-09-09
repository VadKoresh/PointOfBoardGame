package main.service;

import main.entity.PlayerEntity;
import main.exception.PlayerAlreadyExistEx;
import main.exception.PlayerNotFoundException;
import main.model.Player;
import main.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private WinnerService winnerService;

    public PlayerEntity addPlayer(PlayerEntity player) throws PlayerAlreadyExistEx {
        if (playerRepository.findByLogin(player.getLogin()) != null) {
            throw new PlayerAlreadyExistEx("Пользователь с таким логином существвет!");
        }
        winnerService.addWinner(player);
        PlayerEntity playerEntity = playerRepository.save(player);
        return playerEntity;
    }

    public Player updatePlayerNameSurname(PlayerEntity playerEntityUpdate){
        PlayerEntity playerEntity = playerRepository.findByLogin(playerEntityUpdate.getLogin());
        playerEntity.setName(playerEntityUpdate.getName());
        playerEntity.setSurname(playerEntityUpdate.getSurname());
        return Player.toModelForPagePlayer(playerRepository.save(playerEntity));
    }

    public Player updatePlayerFoto(MultipartFile file, int id) throws IOException {
        PlayerEntity playerEntity = playerRepository.findById(id).get();
        playerEntity.setFoto(file.getBytes());
        return Player.toModelForPagePlayer(playerRepository.save(playerEntity));
    }

    public List<Player> getAll(){
        Iterable<PlayerEntity> all = playerRepository.findAll();
        ArrayList<Player> playerArrayList = new ArrayList<>();
        for (PlayerEntity playerEntity: all){
            playerArrayList.add(Player.toModel(playerEntity));
        }
        return playerArrayList;
    }

    public Player getOne(int id) throws PlayerNotFoundException {
        PlayerEntity playerEntity = null;
        Optional<PlayerEntity> byId = playerRepository.findById(id);
        if (!byId.isPresent()){
            throw new PlayerNotFoundException("Пользователь не найден!");
        }
        playerEntity = byId.get();
        return Player.toModel(playerEntity);
    }

    public int deletePlayer(int id){
        playerRepository.deleteById(id);
        return id;
    }
}
