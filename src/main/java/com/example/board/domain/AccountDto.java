package com.example.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    @NotEmpty(message = "아이디를 입력하세요")
    private String username;
    @NotEmpty(message = "비밀번호를 입력하세요")
    private String password;
}
