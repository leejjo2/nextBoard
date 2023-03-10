package com.example.nextboard.entity.boardReply.board.sdo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardReplyRequestDto {
    private String id;
    private String boardNo;
    private String writerId;
    private String writerName;
    private String title;
    private String content;
}
