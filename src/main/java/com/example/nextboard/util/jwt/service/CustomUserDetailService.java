package com.example.nextboard.util.jwt.service;

import com.example.nextboard.entity.member.Member;
import com.example.nextboard.store.MemberStore;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberStore memberStore;

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        try {
            return createUserDetails(memberStore.findByMemberId(userId));
        }catch (Exception e){
            throw new UsernameNotFoundException(userId + " 을 DB에서 찾을 수 없습니다");
        }
    }

    private UserDetails createUserDetails(Member member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getAuthority().toString());

        return new User(
                String.valueOf(member.getMemberId()),
                member.getMemberPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}
