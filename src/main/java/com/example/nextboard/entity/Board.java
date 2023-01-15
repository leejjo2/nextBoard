package com.example.nextboard.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Board {
    private String id;
    private String boardNo;
    private String writerId;
    private long registerTime;
    private long modificationTime;
    private String title;
    private String content;
    private String photo;

//    public static Board sample () {
//        return new Board("","1", "id", "", "", "title", "content");
//    }
//
//    public static void main (String[] args) { System.out.println(sample().toString());}

}
