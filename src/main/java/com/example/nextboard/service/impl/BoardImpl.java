package com.example.nextboard.service.impl;

import com.example.nextboard.entity.board.Board;
import com.example.nextboard.entity.board.sdo.BoardRdo;
import com.example.nextboard.service.BoardService;
import com.example.nextboard.store.BoardStore;
import com.example.nextboard.store.MemberStore;
import com.example.nextboard.util.jwt.config.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BoardImpl implements BoardService {

    private final BoardStore boardStore;
    private final MemberStore memberStore;

//    @Autowired
//    public void setBoardStore(BoardStore boardStore) {
//        this.boardStore = boardStore;
//    }

    public void newBoard(Board board, long sysTime, String originalFileName, String filePath) {

        Date date = new Date(sysTime);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        board.setId(UUID.randomUUID().toString());
        board.setBoardNo(boardStore.findAllByOrderByBoardNoDesc().stream().findFirst().isPresent() ? boardStore.findAllByOrderByBoardNoDesc().stream().findFirst().get().getBoardNo() + 1 : 1);
        board.setWriterId(SecurityUtil.getCurrentMemberId());
        board.setWriterName(memberStore.findByMemberId(SecurityUtil.getCurrentMemberId()).getMemberName());
        board.setRegisterTime(formatter.format(date));
        board.setFilePath(filePath);
        board.setSaveFileName(sysTime + originalFileName.substring(originalFileName.lastIndexOf(".")));
        board.setOriginalFileName(originalFileName);
        boardStore.create(board);
    }

    @Override
    public void editBoard(Board board, long sysTime, String originalFileName, String filePath) {
        Date date = new Date(sysTime);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        board.setModificationTime(formatter.format(date));
        board.setFilePath(filePath);
        board.setSaveFileName(sysTime + originalFileName.substring(originalFileName.lastIndexOf(".")));
        board.setOriginalFileName(originalFileName);
        boardStore.update(board);
    }

    public void saveFile(MultipartFile multipartFile, String filePath) {
        File destFile = new File(filePath);
        if (destFile.exists()) {
            destFile.delete();
        }
        try {
            multipartFile.transferTo(destFile);
        } catch (Exception e) {
        }

    }

    @Override
    public void editFile(MultipartFile multipartFile, String filePath, String exFilePath) {
        File destFile = new File(filePath);
        File exFile = new File(exFilePath);
        if (destFile.exists()) {
            destFile.delete();
        }
        if (exFile.exists()) {
            exFile.delete();
        }
        try {
            multipartFile.transferTo(destFile);
        } catch (Exception e) {
        }
    }

    public Board findBoard(String id) {
        Board board = boardStore.retrieve(id);
        return board;
    }

    public BoardRdo findAllBoard() {
        List<Board> boardList = boardStore.findAll();
        BoardRdo boardRdo = new BoardRdo();
        boardRdo.setBoards(boardList);
        return boardRdo;
    }

    public void modifyBoard(Board board) {
//        Board postBoard = boardStore.retrieve(board.getId());
        boardStore.update(board);
    }

    public void deleteBoard(String id) {
        boardStore.delete(id);
    }

}
