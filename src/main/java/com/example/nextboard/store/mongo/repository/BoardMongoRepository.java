package com.example.nextboard.store.mongo.repository;

import com.example.nextboard.store.mongo.odm.BoardDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BoardMongoRepository extends MongoRepository<BoardDoc, String> {

}
