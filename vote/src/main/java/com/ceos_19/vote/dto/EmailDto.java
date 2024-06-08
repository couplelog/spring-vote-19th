package com.ceos_19.vote.dto;

import jakarta.mail.internet.MimeMessage;
import lombok.Getter;

@Getter
public class EmailDto {
    MimeMessage code;

    public EmailDto(MimeMessage code) {
        this.code = code;
    }
}
