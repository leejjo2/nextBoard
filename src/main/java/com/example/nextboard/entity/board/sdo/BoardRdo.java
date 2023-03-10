package com.example.nextboard.entity.board.sdo;

import com.example.nextboard.entity.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardRdo {
    private List<Board> boards;
}
