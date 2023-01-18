package com.example.nextboard.store.mongo.odm;

import com.example.nextboard.entity.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String writerName;
    private String registerTime;
    private String modificationTime;
    private String title;
    private String content;
    private String filePath;
    private String saveFileName;
    private String originalFileName;

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

