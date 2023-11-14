package com.kh.jpatotalapp.controller;

import com.kh.jpatotalapp.dto.BoardDto;
import com.kh.jpatotalapp.entity.Board;
import com.kh.jpatotalapp.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.kh.jpatotalapp.utils.Common.CORS_ORIGIN;

@Slf4j
@CrossOrigin(origins = CORS_ORIGIN)
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    // 게시글 등록
    @PostMapping("/new")
    public ResponseEntity<Boolean> boardRegister(@RequestBody BoardDto boardDto) {
        boolean isTrue = boardService.saveBoard(boardDto);
        return ResponseEntity.ok(isTrue);
    }
    // 게시글 수정
    // 게시글 삭제
    // 게시글 목록 조회
    // 게시글 상세 조회
    // 게시글 검색
    // 게시글 페이징
}
