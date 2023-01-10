package com.example.nextboard.store.mongo.odm;

import com.example.nextboard.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Board")
public class BoardDoc {

    @Id
    private String id;
    private String boardNo;
    private String writerId;
    private long registerTime;
    private long modificationTime;
    private String title;
    private String content;

    public BoardDoc (Board board) {
        BeanUtils.copyProperties(board, this);
    }

    public Board toDomain() {
        Board board = new Board();
        BeanUtils.copyProperties(this, board);
        return board;
    }

    public static List<Board> toDomains (List<BoardDoc> boardDocs) {
        return boardDocs.stream().map(BoardDoc::toDomain).collect(Collectors.toList());
    }
}

