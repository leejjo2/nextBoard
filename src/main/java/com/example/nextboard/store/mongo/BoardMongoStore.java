package com.example.nextboard.store.mongo;

import com.example.nextboard.entity.board.Board;
import com.example.nextboard.store.BoardStore;
import com.example.nextboard.store.mongo.odm.BoardDoc;
import com.example.nextboard.store.mongo.repository.BoardMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BoardMongoStore implements BoardStore {

    private final BoardMongoRepository boardMongoRepository;

//    public BoardMongoStore(BoardMongoRepository boardMongoRepository) {
//        this.boardMongoRepository = boardMongoRepository;
//    }

    @Override
    public void create(Board board) {
        BoardDoc boardDoc = new BoardDoc(board);
        boardMongoRepository.insert(boardDoc);
    }

    @Override
    public void createAll(List<Board> boards) {
        if (boards == null) {
            return;
        }
        boards.forEach(this::create);
    }

    @Override
    public void update(Board board){
        if(board.getId()==null){
            return;
        }
        BoardDoc boardDoc = new BoardDoc(board);
        boardMongoRepository.save(boardDoc);
    }

    @Override
    public void delete(String id){
        if(id == null){
            return;
        }
        boardMongoRepository.deleteById(id);
    }

    @Override
    public List<Board> findAll(){
        return BoardDoc.toDomains(boardMongoRepository.findAll());
    }
    @Override
    public Board retrieve (String id) {
        Optional<BoardDoc> boardDoc = boardMongoRepository.findById(id);
        return boardDoc.map(BoardDoc::toDomain).orElse(null);
    }
}
