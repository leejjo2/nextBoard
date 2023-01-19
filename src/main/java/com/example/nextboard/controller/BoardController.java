package com.example.nextboard.controller;

import com.example.nextboard.entity.board.Board;
import com.example.nextboard.entity.board.sdo.BoardRdo;
import com.example.nextboard.entity.board.sdo.BoardRequestDto;
import com.example.nextboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/save-board")
    public void saveBoard(@RequestPart(value="board")Board board, @RequestPart(value="file")MultipartFile file){
        long sysTime = System.currentTimeMillis();
        String filePath = "/Users/jameslee/IdeaProjects/files" + "/" + sysTime + "." + file.getOriginalFilename().split("\\.")[1];
        boardService.saveFile(file, filePath);
        boardService.newBoard(board, sysTime, file.getOriginalFilename(), filePath);
    }

    @PostMapping("/edit-board")
    public void editBoard( @RequestPart(value="board")Board board, @RequestPart(value="file")MultipartFile file){
        long sysTime = System.currentTimeMillis();
        Board exBoard = boardService.findBoard(board.getId());
        exBoard.setTitle(board.getTitle());
        exBoard.setContent(board.getContent());
        String filePath = "/Users/jameslee/IdeaProjects/files" + "/" + sysTime + "." + file.getOriginalFilename().split("\\.")[1];
        String exFilePath = exBoard.getFilePath();
        boardService.editFile(file, filePath, exFilePath);
        boardService.editBoard(exBoard, sysTime, file.getOriginalFilename(), filePath);
    }

    @PostMapping("/find-board")
    public Board findBoard (@RequestBody BoardRequestDto boardRequestDto) {
        Board board = boardService.findBoard(boardRequestDto.getId());
        return board;
    };
    @PostMapping("/find-all-board-list")
    public BoardRdo allBoardList () {
        BoardRdo boardRdo = boardService.findAllBoard();
        return boardRdo;
    };

    @GetMapping("/show-img/{fileName}")
    public ResponseEntity<byte[]> showImg(@PathVariable String fileName) throws Exception {
        FileInputStream inputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{
            inputStream = new FileInputStream("/Users/jameslee/IdeaProjects/files" + "/" +fileName);
        }catch (Exception e){
            throw new Exception("problem found");
        }
        int readCound;
        byte[] buffer = new byte[1024];
        byte[] fileArray;
        try{
            while((readCound = inputStream.read(buffer))!=-1){
                outputStream.write(buffer, 0, readCound);
            }
            fileArray = outputStream.toByteArray();
            inputStream.close();
            outputStream.close();
        }catch (Exception e){
            throw new Exception("problem found");
        }
        return new ResponseEntity<>(fileArray, HttpStatus.OK);
    }


    @PostMapping(value = "/modify-board")
    public String modifyBoard(@RequestBody Board board){
        try{
            boardService.modifyBoard(board);
            return "Success";
        }catch(Exception e){
            return "Fail";
        }
    }

    @PostMapping(value = "/delete-board")
    public String deleteBoard(@RequestBody BoardRequestDto boardRequestDto){
        try{
            boardService.deleteBoard(boardRequestDto.getId());
            return "Success";
        }catch(Exception e){
            return "Fail";
        }
    }

}
