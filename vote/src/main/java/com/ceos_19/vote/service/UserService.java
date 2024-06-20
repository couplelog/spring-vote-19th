package com.ceos_19.vote.service;

import com.ceos_19.vote.common.api.ApiResponseDto;
import com.ceos_19.vote.common.api.ErrorResponse;
import com.ceos_19.vote.common.api.ResponseUtils;
import com.ceos_19.vote.common.api.SuccessResponse;
import com.ceos_19.vote.common.enumSet.ErrorType;
import com.ceos_19.vote.common.enumSet.LoginType;
import com.ceos_19.vote.common.enumSet.UserRoleEnum;
import com.ceos_19.vote.common.exception.RestApiException;
import com.ceos_19.vote.common.jwt.JwtUtil;
import com.ceos_19.vote.domain.Part;
import com.ceos_19.vote.domain.Team;
import com.ceos_19.vote.domain.User;
import com.ceos_19.vote.dto.LoginRequestsDto;
import com.ceos_19.vote.dto.SignupRequestDto;
import com.ceos_19.vote.dto.TokenDto;
import com.ceos_19.vote.repository.UserRepository;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ApiResponseDto<SuccessResponse> signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            throw new RestApiException(ErrorType.ALREADY_EXIST);
        }
        Part part = requestDto.getPart();
        Team team = requestDto.getTeam();
        String email = requestDto.getEmail();
        // 입력한 username, password, admin 으로 user 객체 만들어 repository 에 저장
        UserRoleEnum role = requestDto.getRole() ? UserRoleEnum.ADMIN : UserRoleEnum.USER;
        String name = requestDto.getName();
        User user = User.of(LoginType.NONE, username, password, role,part, team, email, name );

        userRepository.save(user);

        return ResponseUtils.ok(SuccessResponse.of(HttpStatus.OK, "회원가입성공"), ErrorResponse.builder().status(200).message("요청 성공").build());

    }

    public ApiResponseDto<TokenDto> login(LoginRequestsDto requestDto, HttpServletResponse response) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // 사용자 확인 & 비밀번호 확인
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())) {
            throw new RestApiException(ErrorType.LOGIN_FAIL);
        }
        else {
            User loginedUser = user.orElseThrow(
                    () -> new RestApiException(ErrorType.NOT_FOUND_USER)
            );
            // header 에 들어갈 JWT 세팅
            TokenDto tokenDto = new TokenDto();
            String accessToken = jwtUtil.createAccessToken(loginedUser.getUsername(), loginedUser.getRole());
            String refreshToken = jwtUtil.createRefreshToken(loginedUser.getUsername(), loginedUser.getRole());

            response.setHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);

            tokenDto.setMessage("로그인 성공");
            tokenDto.setAccessToken(accessToken);
            tokenDto.setRefreshToken(refreshToken);
            return ResponseUtils.ok(tokenDto,ErrorResponse.builder().status(200).message("요청 성공").build());
        }
    }


}
