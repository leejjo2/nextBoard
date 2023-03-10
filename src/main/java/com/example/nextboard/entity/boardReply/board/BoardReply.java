package com.example.nextboard.entity.boardReply.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class BoardReply {
    private String id;
    private int replyNo;
    private String boardId;
    private String memberId;
    private String writerName;
    private int depth;
    private String parentId;
    private String registerTime;
    private String modificationTime;
    private String modified;
    private String content;
}
