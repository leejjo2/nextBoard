package com.example.nextboard.service;

import com.example.nextboard.entity.Board;
import com.example.nextboard.impl.BoardImpl;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    public void newBoard (Board board) {
        new BoardImpl().newBoard(board);
    }
}
