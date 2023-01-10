package com.example.nextboard.controller;

import com.example.nextboard.entity.Board;
import com.example.nextboard.entity.sdo.BoardRdo;
import com.example.nextboard.impl.BoardImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
public class BoardController {

    private BoardImpl boardImpl;

    @Autowired
    public void setBoardImpl(BoardImpl boardImpl) {
        this.boardImpl = boardImpl;
    }

    @PostMapping(value = "/new")
    public void newBoard (@RequestBody Board board) {
        log.debug("userId: " + board);
        boardImpl.newBoard(board);
    }

    @PostMapping("/find-board")
    public Board boardList (@RequestBody String id) {
        Board boardList = boardImpl.findBoardList(id);
        return boardList;
    };
    @PostMapping("/find-all-board-list")
    public BoardRdo allBoardList () {
        BoardRdo boardRdo = boardImpl.findAllBoard();
        return boardRdo;
    };

}
