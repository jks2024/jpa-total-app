package com.kh.jpatotalapp.service;

import com.kh.jpatotalapp.dto.MemberDto;
import com.kh.jpatotalapp.entity.Member;
import com.kh.jpatotalapp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 가입 여부 확인
    public boolean isMember(String email) {
        return memberRepository.existsByEmail(email);
    }
    // 회원 전체 조회
    public List<MemberDto> getMemberList() {
        List<Member> members = memberRepository.findAll();
        List<MemberDto> memberDtos = new ArrayList<>();
        for(Member member : members) {
            MemberDto memberDto = new MemberDto();
            memberDto.setUserId(member.getUserId());
            memberDto.setEmail(member.getEmail());
            memberDto.setName(member.getName());
            memberDto.setRegDate(member.getRegDate());
            memberDtos.add(memberDto);
        }
        return memberDtos;
    }
    // 회원 전체 조회 (페이징)
//    public List<MemberDto> getMemberList(int page, int size) {
//        List<Member> members = memberRepository.findAll();
//        List<MemberDto> memberDtos = new ArrayList<>();
//        for(Member member : members) {
//            MemberDto memberDto = new MemberDto();
//            memberDto.setUserId(member.getUserId());
//            memberDto.setEmail(member.getEmail());
//            memberDto.setName(member.getName());
//            memberDto.setRegDate(member.getRegDate());
//            memberDtos.add(memberDto);
//        }
//        return memberDtos;
//    }

    // 회원전체 조회 (스트림)
//    public List<MemberDto> getMemberList() {
//        List<Member> members = memberRepository.findAll();
//        List<MemberDto> memberDtos = members.stream().map(member -> {
//            MemberDto memberDto = new MemberDto();
//            memberDto.setUserId(member.getUserId());
//            memberDto.setEmail(member.getEmail());
//            memberDto.setName(member.getName());
//            memberDto.setRegDate(member.getRegDate());
//            return memberDto;
//        }).collect(Collectors.toList());
//        return memberDtos;
//    }



    // 회원 상세 조회
    public MemberDto getMemberDetail(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("해당 회원이 존재하지 않습니다.")
        );
        MemberDto memberDto = new MemberDto();
        memberDto.setUserId(member.getUserId());
        memberDto.setEmail(member.getEmail());
        memberDto.setName(member.getName());
        memberDto.setRegDate(member.getRegDate());
        return memberDto;
    }
    // 회원 가입
    public boolean saveMember(Member member) {
        Member rst = memberRepository.save(member);
        return rst != null;
    }

    // 로그인
    public boolean login(String userId, String pwd) {
        Optional<Member> member = memberRepository.findByUserIdAndPassword(userId, pwd);
        return member.isPresent();
    }
    // 회원 삭제
    public boolean deleteMember(String email) {
        try {
            Member member = memberRepository.findByEmail(email).orElseThrow(
                    () -> new RuntimeException("해당 회원이 존재하지 않습니다.")
            );
            memberRepository.delete(member);
            return true; // 회원이 존재하면 true 반환
        } catch (RuntimeException e) {
            return false; // 회원이 존재하지 않으면 false 반환
        }
    }
}
