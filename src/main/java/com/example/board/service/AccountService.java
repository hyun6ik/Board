package com.example.board.service;

import com.example.board.domain.Account;
import com.example.board.domain.AccountDto;

import javax.servlet.http.HttpServletRequest;

public interface AccountService {

    Long join(AccountDto accountDto);

    boolean check(HttpServletRequest request);
}
