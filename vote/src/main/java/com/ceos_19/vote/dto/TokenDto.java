package com.ceos_19.vote.dto;

import lombok.*;

@Getter
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    private String message;
    private String accessToken;
    private String refreshToken;
}
