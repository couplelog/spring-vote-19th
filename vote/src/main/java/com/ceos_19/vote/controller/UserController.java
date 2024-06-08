package com.ceos_19.vote.controller;

import com.ceos_19.vote.common.api.ApiResponseDto;
import com.ceos_19.vote.common.api.SuccessResponse;
import com.ceos_19.vote.dto.LoginRequestsDto;
import com.ceos_19.vote.dto.SignupRequestDto;
import com.ceos_19.vote.dto.TokenDto;
import com.ceos_19.vote.service.EmailServiceImpl;
import com.ceos_19.vote.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final EmailServiceImpl emailService;
    @PostMapping("/signup")
    public ApiResponseDto<SuccessResponse> signup(@RequestBody SignupRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    @PostMapping("/login")
    public ApiResponseDto<TokenDto> login(@RequestBody LoginRequestsDto requestDto, HttpServletResponse response) {
        return userService.login(requestDto, response);
    }

    @PostMapping("/verify")
    public String verify(@RequestBody String email) throws Exception {
        return emailService.sendSimpleMessage(email);
    }

}
