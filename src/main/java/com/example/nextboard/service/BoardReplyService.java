package com.example.nextboard.service;
import com.example.nextboard.entity.board.Board;
import com.example.nextboard.entity.board.sdo.BoardRdo;
import com.example.nextboard.entity.board.sdo.BoardRequestDto;
import com.example.nextboard.entity.boardReply.board.BoardReply;
import com.example.nextboard.entity.boardReply.board.sdo.BoardReplyRdo;
import org.springframework.web.multipart.MultipartFile;

public interface BoardReplyService {
    void newBoardReply(BoardReply boardReply, long sysTime);
    BoardReplyRdo findAllBoardReplyByBoardId(String boardId);
}