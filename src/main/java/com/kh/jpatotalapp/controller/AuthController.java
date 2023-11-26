package com.kh.jpatotalapp.controller;

import com.kh.jpatotalapp.dto.MemberReqDto;
import com.kh.jpatotalapp.dto.MemberResDto;
import com.kh.jpatotalapp.dto.TokenDto;
import com.kh.jpatotalapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.kh.jpatotalapp.utils.Common.CORS_ORIGIN;

@Slf4j
@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = CORS_ORIGIN)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResDto> signup(@RequestBody MemberReqDto requestDto) {
        return ResponseEntity.ok(authService.signup(requestDto));
    }
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberReqDto requestDto) {
        return ResponseEntity.ok(authService.login(requestDto));
    }
}
