package com.example.nextboard.service;
import com.example.nextboard.entity.board.Board;
import com.example.nextboard.entity.board.sdo.BoardRdo;
import org.springframework.web.multipart.MultipartFile;

public interface BoardService {
    void newBoard(Board board, long sysTime, String originalFileName, String filePath);
    void editBoard(Board board, long sysTime, String originalFileName, String filePath);

    void saveFile(MultipartFile multipartFile, String filePath);
    void editFile(MultipartFile multipartFile, String filePath, String exFilePath);

    Board findBoard(String id);

    BoardRdo findAllBoard();

    void modifyBoard(Board board);
    void deleteBoard(String id);
}