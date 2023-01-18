package com.example.nextboard.service;

import com.example.nextboard.entity.member.Member;
import com.example.nextboard.entity.member.dto.MemberResponseDto;

public interface MemberService {

    MemberResponseDto getMyInfoBySecurity();

    MemberResponseDto changeMemberNickname(String useId, String memberName);

    MemberResponseDto changeMemberPassword(String email, String exPassword, String newPassword);
}