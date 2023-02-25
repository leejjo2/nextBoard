package com.example.nextboard.store.mongo.odm;

import com.example.nextboard.entity.board.Board;
import com.example.nextboard.entity.boardReply.board.BoardReply;
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
@Document(collection = "BoardReply")
public class BoardReplyDoc {

    @Id
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

    public BoardReplyDoc(BoardReply boardReply) {
        BeanUtils.copyProperties(boardReply, this);
    }

    public BoardReply toDomain() {
        BoardReply boardReply = new BoardReply();
        BeanUtils.copyProperties(this, boardReply);
        return boardReply;
    }

    public static List<BoardReply> toDomains (List<BoardReplyDoc> boardReplyDocs) {
        return boardReplyDocs.stream().map(BoardReplyDoc::toDomain).collect(Collectors.toList());
    }
}

