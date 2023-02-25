package com.example.nextboard.store.mongo.repository;

import com.example.nextboard.store.mongo.odm.BoardDoc;
import com.example.nextboard.store.mongo.odm.BoardReplyDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BoardReplyMongoRepository extends MongoRepository<BoardReplyDoc, String> {
    List<BoardReplyDoc> findAllByOrderByReplyNoDesc();
    List<BoardReplyDoc> findAllByBoardId(String boardId);
}
