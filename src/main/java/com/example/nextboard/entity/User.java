package com.example.nextboard.entity;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String id;
    private String memberNo;
    private String name;
    private String userId;
    private String passwd;

}
