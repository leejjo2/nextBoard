package com.example.nextboard.store;


import com.example.nextboard.entity.Board;

import java.util.List;

public interface BoardStore {
    void create (Board board);
    void createAll (List<Board> boards);
    void update(Board board);
    void delete(String id);

    List<Board> findAll();
    Board retrieve(String id);
}
