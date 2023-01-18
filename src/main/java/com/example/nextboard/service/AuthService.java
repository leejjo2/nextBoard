package com.example.nextboard.service;

import com.example.nextboard.entity.member.dto.MemberRequestDto;
import com.example.nextboard.entity.member.dto.MemberResponseDto;
import com.example.nextboard.util.jwt.dto.TokenDto;

public interface AuthService {
    MemberResponseDto signup(MemberRequestDto requestDto);

    TokenDto login(MemberRequestDto requestDto);
}
