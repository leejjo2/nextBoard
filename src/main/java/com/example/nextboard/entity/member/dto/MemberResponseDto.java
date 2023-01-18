package com.example.nextboard.entity.member.dto;

import com.example.nextboard.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDto {
    private String memberName;
    private String memberId;

    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .memberName(member.getMemberName())
                .memberId(member.getMemberId())
                .build();
    }
}