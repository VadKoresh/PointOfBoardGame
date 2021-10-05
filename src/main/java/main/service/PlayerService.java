package main.service;

import main.entity.BoardGameEntity;
import main.entity.PlayerEntity;
import main.entity.VictoryStoryEntity;
import main.exception.PlayerAlreadyExistEx;
import main.exception.PlayerNotFoundException;
import main.model.BoardGame;
import main.model.Player;
import main.repository.PlayerRepository;
import main.repository.VictoryStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private WinnerService winnerService;
    @Autowired
    private VictoryStoryRepository victoryStoryRepository;

    public PlayerEntity addPlayer(PlayerEntity player) throws PlayerAlreadyExistEx {
        if (playerRepository.findByLogin(player.getLogin()) != null) {
            throw new PlayerAlreadyExistEx("Пользователь с таким логином существвет!");
        }
        winnerService.addWinner(player);
        PlayerEntity playerEntity = playerRepository.save(player);
        return playerEntity;
    }

    public Player updatePlayer(PlayerEntity playerEntityUpdate) {
        PlayerEntity playerEntity = playerRepository.findByLogin(playerEntityUpdate.getLogin());
        playerEntity.setName(playerEntityUpdate.getName());
        playerEntity.setSurname(playerEntityUpdate.getSurname());
        playerEntity.setEmailAddress(playerEntityUpdate.getEmailAddress());
        playerEntity.setPhoneNumber(playerEntityUpdate.getPhoneNumber());
        playerEntity.setPassword(playerEntityUpdate.getPassword());
        return Player.toModelForPagePlayer(playerRepository.save(playerEntity));
    }

    public Player updatePlayerFoto(MultipartFile file, int id) throws IOException {
        PlayerEntity playerEntity = playerRepository.findById(id).get();
        playerEntity.setFoto(file.getBytes());
        return Player.toModelForPagePlayer(playerRepository.save(playerEntity));
    }

    public List<Player> getAll() {
        Iterable<PlayerEntity> all = playerRepository.findAll();
        ArrayList<Player> playerArrayList = new ArrayList<>();
        for (PlayerEntity playerEntity : all) {
            playerArrayList.add(Player.toModel(playerEntity));
        }
        return playerArrayList;
    }

    public Player getOneForPage(int id) throws PlayerNotFoundException {
        PlayerEntity playerEntity = null;
        Optional<PlayerEntity> byId = playerRepository.findById(id);
        if (!byId.isPresent()) {
            throw new PlayerNotFoundException("Пользователь не найден!");
        }
        playerEntity = byId.get();
        return Player.toModelForPagePlayer(playerEntity);
    }

    public Player getOne(int id) throws PlayerNotFoundException {
        PlayerEntity playerEntity = null;
        Optional<PlayerEntity> byId = playerRepository.findById(id);
        if (!byId.isPresent()) {
            throw new PlayerNotFoundException("Пользователь не найден!");
        }
        playerEntity = byId.get();
        return Player.toModel(playerEntity);
    }

    public int deletePlayer(int id) {
        playerRepository.deleteById(id);
        return id;
    }

    public List<Player> getPlayersToStory(BoardGameEntity boardGame) {
        List<VictoryStoryEntity> byBoardGameEntity = victoryStoryRepository.findByBoardGameEntity(boardGame);
        TreeMap<Player, Integer> playerIntegerTreeMap = new TreeMap<>();

        for (VictoryStoryEntity victoryStoryEntity : byBoardGameEntity) {
            Player player = Player.toModel(victoryStoryEntity.getPlayer());
            playerIntegerTreeMap.merge(player, victoryStoryEntity.getScore(), Integer::sum);
        }
        return playerIntegerTreeMap.keySet().stream().limit(5).collect(Collectors.toList());
    }
}
