package com.example.nextboard.store.mongo;

import com.example.nextboard.entity.board.Board;
import com.example.nextboard.entity.boardReply.board.BoardReply;
import com.example.nextboard.store.BoardReplyStore;
import com.example.nextboard.store.mongo.odm.BoardDoc;
import com.example.nextboard.store.mongo.odm.BoardReplyDoc;
import com.example.nextboard.store.mongo.repository.BoardReplyMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardReplyMongoStore implements BoardReplyStore {

    private final BoardReplyMongoRepository boardReplyMongoRepository;

    @Override
    public void create(BoardReply boardReply) {
        BoardReplyDoc boardReplyDoc = new BoardReplyDoc(boardReply);
        boardReplyMongoRepository.insert(boardReplyDoc);
    }

    @Override
    public List<BoardReply> findAllByOrderByReplyNoDesc() {
        return BoardReplyDoc.toDomains(boardReplyMongoRepository.findAllByOrderByReplyNoDesc());
    }

    @Override
    public List<BoardReply> findAllByBoardId(String boardId) {
        return BoardReplyDoc.toDomains(boardReplyMongoRepository.findAllByBoardId(boardId));
    }
}
