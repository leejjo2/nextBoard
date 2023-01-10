package com.example.nextboard.store;


import com.example.nextboard.entity.Board;

import java.util.List;

public interface BoardStore {
    void create (Board board);
    void createAll (List<Board> boards);

    List<Board> findAll();
    Board retrieve(String id);
}
