package com.example.nextboard.entity.boardReply.board.sdo;

import com.example.nextboard.entity.boardReply.board.BoardReply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardReplyRdo {
    private List<BoardReply> boardReplyList;
}
