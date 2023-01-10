package com.example.nextboard.store.mongo.odm;

import com.example.nextboard.entity.Board;
import com.example.nextboard.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "User")
public class UserDoc {

    @Id
    private String id;
    private String memberNo;
    private String name;
    private String userId;
    private String passwd;

    public UserDoc(User user) {
        BeanUtils.copyProperties(user, this);
    }

    public User toDomain() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }

    public static List<User> toDomains (List<UserDoc> userDocs) {
        return userDocs.stream().map(UserDoc::toDomain).collect(Collectors.toList());
    }
}

