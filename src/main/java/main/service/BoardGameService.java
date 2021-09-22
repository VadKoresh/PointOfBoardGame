package main.service;

import javassist.NotFoundException;
import main.entity.BoardGameEntity;
import main.entity.PlayerEntity;
import main.exception.BoardGameAlreadyExistEx;
import main.exception.PlayerAlreadyExistEx;
import main.model.BoardGame;
import main.model.BoardGameListPoint;
import main.repository.BoardGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardGameService {

    @Autowired
    private BoardGameRepository boardGameRepository;

    public BoardGameEntity addBoardGame(BoardGameEntity boardGameEntity) throws BoardGameAlreadyExistEx {
        if (boardGameRepository.findByName(boardGameEntity.getName()) != null) {
            throw new BoardGameAlreadyExistEx("Настольная игра с таким именем существвет!");
        }
        BoardGameEntity boardGameEntity1 = boardGameRepository.save(boardGameEntity);
        return boardGameEntity1;
    }

    public BoardGameEntity getOneBoardGame(int id) throws NotFoundException {
        Optional<BoardGameEntity> byId = boardGameRepository.findById(id);
        BoardGameEntity boardGameEntity = null;
        if (!byId.isPresent()){
            throw new NotFoundException("Игра не найдена");
        }
        return boardGameEntity = byId.get();
    }

    public List<BoardGame> getAllBoardGame(){
        Iterable<BoardGameEntity> all = boardGameRepository.findAll();
        ArrayList<BoardGame> boardGameArrayList = new ArrayList<>();
        for (BoardGameEntity boardGameEntity : all){
            boardGameArrayList.add(BoardGame.toModel(boardGameEntity));
        }
        return boardGameArrayList;
    }

    /**
     * Изменяем только победные очки
     */
    public BoardGame updatePointPlace(BoardGameEntity boardGameEntityUpdate){
        BoardGameEntity boardGameEntity1 = boardGameRepository.findByName(boardGameEntityUpdate.getName());
        boardGameEntity1.setFirstPlace(boardGameEntityUpdate.getFirstPlace());
        boardGameEntity1.setSecondPlace(boardGameEntityUpdate.getSecondPlace());
        boardGameEntity1.setThirdPlace(boardGameEntityUpdate.getThirdPlace());
        return BoardGame.toModel(boardGameRepository.save(boardGameEntity1));
    }

    public int deleteBoardGame(int id){
        boardGameRepository.deleteById(id);
        return id;
    }

    /**
     * @return Возвращает список настольных игр со списком доступных победных очков
     */
    public List<BoardGameListPoint> getAllBoardGameWithListScore(){
        Iterable<BoardGameEntity> boardGameRepositoryAll = boardGameRepository.findAll();
        ArrayList<BoardGameListPoint> boardGameListPointArrayList = new ArrayList<>();
        for (BoardGameEntity boardGameEntity : boardGameRepositoryAll){
            boardGameListPointArrayList.add(BoardGameListPoint.toModelWithListPoint(boardGameEntity));
        }
        return boardGameListPointArrayList;
    }
}
