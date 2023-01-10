package com.example.nextboard.impl;

import com.example.nextboard.entity.Board;
import com.example.nextboard.entity.sdo.BoardRdo;
import com.example.nextboard.store.BoardStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BoardImpl {

    BoardStore boardStore;

    @Autowired
    public void setBoardStore(BoardStore boardStore) {
        this.boardStore = boardStore;
    }

    public void newBoard(Board board) {

        board.setId(UUID.randomUUID().toString());
        board.setRegisterTime(System.currentTimeMillis());
        boardStore.create(board);
    }

    public Board findBoardList(String id) {
        Board boardList = boardStore.retrieve(id);
        return boardList;
    }
    public BoardRdo findAllBoard() {
        List<Board> boardList = boardStore.findAll();
        BoardRdo boardRdo = new BoardRdo();
        boardRdo.setBoards(boardList);
        return boardRdo;
    }

}
