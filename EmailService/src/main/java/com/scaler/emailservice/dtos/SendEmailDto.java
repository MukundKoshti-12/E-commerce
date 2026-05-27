package com.scaler.emailservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailDto {
    public String email;
    public String subject;
    public String body;
}
