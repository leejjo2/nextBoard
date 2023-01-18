package com.example.nextboard.store.mongo.odm;

import com.example.nextboard.entity.member.vo.Authority;
import com.example.nextboard.entity.member.Member;
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
@Document(collection = "Member")
public class MemberDoc {

    @Id
    private String id;
    private String memberId;
    private String memberPassword;
    private String memberName;
    private Authority authority;
    private String registerTime;
    private String modificationTime;

    public MemberDoc(Member member) {
        BeanUtils.copyProperties(member, this);
    }

    public Member toDomain() {
        Member member = new Member();
        BeanUtils.copyProperties(this, member);
        return member;
    }

    public static List<Member> toDomains (List<MemberDoc> memberDocs) {
        return memberDocs.stream().map(MemberDoc::toDomain).collect(Collectors.toList());
    }
}

