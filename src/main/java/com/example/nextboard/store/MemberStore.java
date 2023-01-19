package com.example.nextboard.store;


import com.example.nextboard.entity.member.Member;

import java.util.List;

public interface MemberStore {
    Member create (Member member);
    void createAll (List<Member> members);
    void update(Member member);
    void delete(String id);
    boolean exists(String id);

    List<Member> findAll();
    Member retrieve(String id);
    Member findByMemberId(String memberId);
    boolean existsByMemberId(String memberId);


}
