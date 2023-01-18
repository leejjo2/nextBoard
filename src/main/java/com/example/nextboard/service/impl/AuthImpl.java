package com.example.nextboard.service.impl;

import com.example.nextboard.entity.member.Member;
import com.example.nextboard.entity.member.vo.Authority;
import com.example.nextboard.service.AuthService;
import com.example.nextboard.store.MemberStore;
import com.example.nextboard.util.jwt.TokenProvider;
import com.example.nextboard.entity.member.dto.MemberRequestDto;
import com.example.nextboard.entity.member.dto.MemberResponseDto;
import com.example.nextboard.util.jwt.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthImpl implements AuthService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final MemberStore memberStore;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Override
    public MemberResponseDto signup(MemberRequestDto requestDto) {
        if (memberStore.existsByMemberId(requestDto.getMemberId())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        Member member = requestDto.toMember(passwordEncoder);
        long sysTime = System.currentTimeMillis();
        Date date = new Date(sysTime);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        member.setId(UUID.randomUUID().toString());
        member.setAuthority(Authority.ROLE_USER);
        member.setRegisterTime(formatter.format(date));
        return MemberResponseDto.of(memberStore.create(member));
    }

    @Override
    public TokenDto login(MemberRequestDto requestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();

        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.generateTokenDto(authentication);
    }

}