package com.kh.jpatotalapp.service;
import com.kh.jpatotalapp.dto.BoardDto;
import com.kh.jpatotalapp.entity.Board;
import com.kh.jpatotalapp.entity.Member;
import com.kh.jpatotalapp.repository.BoardRepository;
import com.kh.jpatotalapp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    // 게시글 등록
    public boolean saveBoard(BoardDto boardDto) {
        try {
            Board board = new Board();
            Member member = memberRepository.findByEmail(boardDto.getEmail()).orElseThrow(
                    () -> new RuntimeException("해당 회원이 존재하지 않습니다.")
            );
            board.setTitle(boardDto.getTitle());
            board.setContent(boardDto.getContent());
            board.setImgPath(boardDto.getImg());
            board.setMember(member);
            boardRepository.save(board);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // 게시글 전체 조회
    // 게시글 상세 조회
    // 게시글 수정
    // 게시글 삭제
    // 게시글 검색
    // 게시글 페이징
}
