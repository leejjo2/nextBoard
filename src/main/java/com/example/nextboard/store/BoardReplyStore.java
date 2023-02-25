package com.example.nextboard.store;


import com.example.nextboard.entity.board.Board;
import com.example.nextboard.entity.boardReply.board.BoardReply;

import java.util.List;

public interface BoardReplyStore {
    void create (BoardReply boardReply);
    List<BoardReply> findAllByOrderByReplyNoDesc();
    List<BoardReply> findAllByBoardId(String boardId);
}
