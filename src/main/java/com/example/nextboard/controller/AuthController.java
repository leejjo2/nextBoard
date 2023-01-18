package com.example.nextboard.controller;

import com.example.nextboard.entity.member.dto.MemberRequestDto;
import com.example.nextboard.entity.member.dto.MemberResponseDto;
import com.example.nextboard.util.jwt.dto.TokenDto;
import com.example.nextboard.service.impl.AuthImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthImpl authImpl;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(authImpl.signup(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(authImpl.login(requestDto));
    }
}
