package com.example.nextboard.service.impl;

import com.example.nextboard.entity.board.sdo.BoardRdo;
import com.example.nextboard.entity.boardReply.board.BoardReply;
import com.example.nextboard.entity.boardReply.board.sdo.BoardReplyRdo;
import com.example.nextboard.service.BoardReplyService;
import com.example.nextboard.store.BoardReplyStore;
import com.example.nextboard.store.MemberStore;
import com.example.nextboard.util.jwt.config.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BoardReplyImpl implements BoardReplyService {

    private final BoardReplyStore boardReplyStore;
    private final MemberStore memberStore;


    public void newBoardReply(BoardReply boardReply, long sysTime) {

        Date date = new Date(sysTime);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        boardReply.setId(UUID.randomUUID().toString());
        boardReply.setReplyNo(boardReplyStore.findAllByOrderByReplyNoDesc().stream().findFirst().isPresent() ? boardReplyStore.findAllByOrderByReplyNoDesc().stream().findFirst().get().getReplyNo() + 1 : 1);
        boardReply.setMemberId(SecurityUtil.getCurrentMemberId());
        boardReply.setWriterName(memberStore.findByMemberId(SecurityUtil.getCurrentMemberId()).getMemberName());
        boardReply.setRegisterTime(formatter.format(date));
//        board.setFilePath(filePath);
//        board.setSaveFileName(sysTime + originalFileName.substring(originalFileName.lastIndexOf(".")));
//        board.setOriginalFileName(originalFileName);
        boardReplyStore.create(boardReply);
    }

    @Override
    public BoardReplyRdo findAllBoardReplyByBoardId(String boardId) {
        List<BoardReply> boardReplyList = boardReplyStore.findAllByBoardId(boardId);
        BoardReplyRdo boardReplyRdo = new BoardReplyRdo();
        boardReplyRdo.setBoardReplyList(boardReplyList);
        return boardReplyRdo;
    }
}
