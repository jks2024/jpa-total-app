package com.kh.jpatotalapp.controller;
import com.kh.jpatotalapp.dto.BoardDto;
import com.kh.jpatotalapp.dto.MemberReqDto;
import com.kh.jpatotalapp.dto.MemberResDto;
import com.kh.jpatotalapp.entity.Member;
import com.kh.jpatotalapp.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.kh.jpatotalapp.utils.Common.CORS_ORIGIN;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    // 회원 전체 조회
    @GetMapping("/list")
    public ResponseEntity<List<MemberResDto>> memberList() {
        List<MemberResDto> list = memberService.getMemberList();
        return ResponseEntity.ok(list);
    }
    // 총 페이지 수
    @GetMapping("/list/count")
    public ResponseEntity<Integer> memberCount(@RequestParam(defaultValue = "20") int page,
                                               @RequestParam(defaultValue = "0") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        int pageCnt = memberService.getMemberPage(pageRequest);
        return ResponseEntity.ok(pageCnt);
    }
    // 회원 조회 페이지네이션
    @GetMapping("/list/page")
    public ResponseEntity<List<MemberResDto>> memberList(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "20") int size) {
        List<MemberResDto> list = memberService.getMemberList(page, size);
        return ResponseEntity.ok(list);
    }
    // 회원 상세 조회
    @GetMapping("/detail/{email}")
    public ResponseEntity<MemberResDto> memberDetail(@PathVariable String email) {
        MemberResDto memberDto = memberService.getMemberDetail(email);
        return ResponseEntity.ok(memberDto);
    }
    // 개인 정보 조회
    @GetMapping("/info")
    public ResponseEntity<MemberResDto> memberInfo() {
        MemberResDto memberDto = memberService.getMemberInfo();
        return ResponseEntity.ok(memberDto);
    }

    // 회원 수정
    @PutMapping("/modify")
    public ResponseEntity<Boolean> memberModify(@RequestBody MemberReqDto memberDto) {
        log.info("memberDto: {}", memberDto.getEmail());
        boolean isTrue = memberService.modifyMember(memberDto);
        return ResponseEntity.ok(isTrue);
    }
    // 회원 탈퇴
    @DeleteMapping("/del/{email}")
    public ResponseEntity<Boolean> memberDelete(@PathVariable String email) {
        boolean isTrue = memberService.deleteMember(email);
        return ResponseEntity.ok(isTrue);
    }
}
