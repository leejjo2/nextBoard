package com.example.nextboard.store.mongo.repository;

import com.example.nextboard.store.mongo.odm.MemberDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberMongoRepository extends MongoRepository<MemberDoc, String> {

    MemberDoc findByMemberId(String memberId);

    boolean existsByMemberId(String memberId);
}
