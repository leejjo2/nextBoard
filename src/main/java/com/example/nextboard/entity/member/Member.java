package com.example.nextboard.entity.member;

import com.example.nextboard.entity.member.vo.Authority;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    private String id;
    private String memberId;
    private String memberPassword;
    private String memberName;
    private Authority authority;
    private String registerTime;
    private String modificationTime;

}
