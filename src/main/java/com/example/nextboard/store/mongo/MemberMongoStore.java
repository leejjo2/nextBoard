package com.example.nextboard.store.mongo;

import com.example.nextboard.entity.member.Member;
import com.example.nextboard.store.MemberStore;
import com.example.nextboard.store.mongo.odm.MemberDoc;
import com.example.nextboard.store.mongo.repository.MemberMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberMongoStore implements MemberStore {

    private final MemberMongoRepository memberMongoRepository;

//    public MemberMongoStore(MemberMongoRepository memberMongoRepository) {
//        this.memberMongoRepository = memberMongoRepository;
//    }

    @Override
    public Member create(Member member) {
        MemberDoc memberDoc = new MemberDoc(member);
        memberMongoRepository.insert(memberDoc);
        return member;
    }

    @Override
    public void createAll(List<Member> members) {
        if (members == null) {
            return;
        }
        members.forEach(this::create);
    }

    @Override
    public void update(Member member){
        if(member.getId()==null){
            return;
        }
        MemberDoc memberDoc = new MemberDoc(member);
        memberMongoRepository.save(memberDoc);
    }

    @Override
    public void delete(String id){
        if(id == null){
            return;
        }
        memberMongoRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        return memberMongoRepository.existsById(id);
    }

    @Override
    public List<Member> findAll(){
        return MemberDoc.toDomains(memberMongoRepository.findAll());
    }
    @Override
    public Member retrieve (String id) {
        Optional<MemberDoc> memberDoc = memberMongoRepository.findById(id);
        return memberDoc.map(MemberDoc::toDomain).orElse(null);
    }

    @Override
    public Member findByMemberId(String memberId) {
        MemberDoc memberDoc = memberMongoRepository.findByMemberId(memberId);
        return memberDoc.toDomain();
    }

    @Override
    public boolean existsByMemberId(String memberId) {
        return memberMongoRepository.existsByMemberId(memberId);
    }


}
