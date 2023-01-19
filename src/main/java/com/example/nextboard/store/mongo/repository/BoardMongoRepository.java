package com.example.nextboard.store.mongo.repository;

import com.example.nextboard.store.mongo.odm.BoardDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BoardMongoRepository extends MongoRepository<BoardDoc, String> {
    List<BoardDoc> findAllByOrderByBoardNoDesc();
}
