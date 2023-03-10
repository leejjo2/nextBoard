package com.example.nextboard.entity.board.sdo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardRequestDto {
    private String id;
    private String boardId;
    private String writerId;
    private String writerName;
    private int depth;
    private String parentId;
    private String content;
}
