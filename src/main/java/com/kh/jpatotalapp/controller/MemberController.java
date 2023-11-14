package com.kh.jpatotalapp.controller;
import com.kh.jpatotalapp.dto.MemberDto;
import com.kh.jpatotalapp.entity.Member;
import com.kh.jpatotalapp.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.kh.jpatotalapp.utils.Common.CORS_ORIGIN;

@Slf4j
@CrossOrigin(origins = CORS_ORIGIN)
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    // 회원 전체 조회
    @GetMapping("/list")
    public ResponseEntity<List<MemberDto>> memberList() {
        List<MemberDto> list = memberService.getMemberList();
        return ResponseEntity.ok(list);
    }
    // 회원 상세 조회
    @GetMapping("/detail/{userid}")
    public ResponseEntity<MemberDto> memberDetail(String userid) {
        MemberDto memberDto = memberService.getMemberDetail(userid);
        return ResponseEntity.ok(memberDto);
    }
    // 회원 수정
    @PutMapping("/modify/{email}")
    public ResponseEntity<Boolean> memberModify(@RequestBody MemberDto memberDto) {
        Member member = new Member();
        member.setEmail(memberDto.getEmail());
        member.setName(memberDto.getName());
        member.setRegDate(memberDto.getRegDate());
        boolean isTrue = memberService.saveMember(member);
        return ResponseEntity.ok(isTrue);
    }
    // 회원 등록
    @PostMapping("/new")
    public ResponseEntity<Boolean> memberRegister(@RequestBody MemberDto memberDto) {
        Member member = new Member();
        member.setPassword(memberDto.getPwd());
        member.setEmail(memberDto.getEmail());
        member.setName(memberDto.getName());
        member.setRegDate(memberDto.getRegDate());
        boolean isTrue = memberService.saveMember(member);
        return ResponseEntity.ok(isTrue);
    }
    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Boolean> memberLogin(@RequestBody MemberDto memberDto) {
        boolean isTrue = memberService.login(memberDto.getEmail(), memberDto.getPwd());
        return ResponseEntity.ok(isTrue);
    }
    // 회원 존재 여부 확인
    @GetMapping("/check")
    public ResponseEntity<Boolean> isMember(@RequestParam String email) {
        log.info("email: {}", email);
        boolean isReg = memberService.isMember(email);
        return ResponseEntity.ok(isReg);
    }
    // 회원 삭제
    @DeleteMapping("/del/{email}")
    public ResponseEntity<Boolean> memberDelete(String email) {
        boolean isTrue = memberService.deleteMember(email);
        return ResponseEntity.ok(isTrue);
    }

}
