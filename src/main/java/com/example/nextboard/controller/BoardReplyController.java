package com.example.nextboard.controller;

import com.example.nextboard.entity.board.sdo.BoardRdo;
import com.example.nextboard.entity.board.sdo.BoardRequestDto;
import com.example.nextboard.entity.boardReply.board.BoardReply;
import com.example.nextboard.entity.boardReply.board.sdo.BoardReplyRdo;
import com.example.nextboard.service.BoardReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/boardReply")
public class BoardReplyController {

    private final BoardReplyService boardReplyService;

    @PostMapping("/save-boardReply")
    public String saveBoard(@RequestBody BoardReply boardReply) {
        long sysTime = System.currentTimeMillis();
        boardReplyService.newBoardReply(boardReply, sysTime);
        return "success";
    }

    @PostMapping("/find-boardReplyList-byBoardId")
    public BoardReplyRdo allBoardList(@RequestBody BoardRequestDto boardRequestDto) {
        String boardId = boardRequestDto.getBoardId();
        BoardReplyRdo boardReplyRdo = boardReplyService.findAllBoardReplyByBoardId(boardId);
        return boardReplyRdo;
    }
}
