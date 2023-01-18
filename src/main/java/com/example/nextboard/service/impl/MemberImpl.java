package com.example.nextboard.service.impl;

import com.example.nextboard.entity.member.vo.Authority;
import com.example.nextboard.entity.member.Member;
import com.example.nextboard.service.MemberService;
import com.example.nextboard.store.MemberStore;
import com.example.nextboard.util.jwt.config.SecurityUtil;
import com.example.nextboard.entity.member.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberImpl implements MemberService {

    private final MemberStore memberStore;
    private final PasswordEncoder passwordEncoder;

//    @Autowired
//    public void setMemberStore(MemberStore memberStore) {
//        this.memberStore = memberStore;
//    }

    public MemberResponseDto getMyInfoBySecurity() {
        try {
            return MemberResponseDto.of(memberStore.findByMemberId(SecurityUtil.getCurrentMemberId()));
        } catch (Exception e) {
            throw new RuntimeException("로그인 유저 정보가 없습니다");
        }
    }

    @Transactional
    public MemberResponseDto changeMemberNickname(String useId, String memberName) {
        try {
            Member member = memberStore.findByMemberId(useId);
            member.setMemberName(memberName);
            return MemberResponseDto.of(memberStore.create(member));
        } catch (Exception e) {
            throw new RuntimeException("로그인 유저 정보가 없습니다");
        }
    }

    @Transactional
    public MemberResponseDto changeMemberPassword(String email, String exPassword, String newPassword) {

        try {
            Member member = memberStore.findByMemberId(SecurityUtil.getCurrentMemberId());
            if (!passwordEncoder.matches(exPassword, member.getMemberPassword())) {
                throw new RuntimeException("비밀번호가 맞지 않습니다");
            }
            member.setMemberPassword(passwordEncoder.encode((newPassword)));
            return MemberResponseDto.of(memberStore.create(member));
        } catch (Exception e) {
            throw new RuntimeException("로그인 유저 정보가 없습니다");
        }
    }
}
