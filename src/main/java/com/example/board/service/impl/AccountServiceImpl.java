package com.example.board.service.impl;

import com.example.board.domain.Account;
import com.example.board.domain.AccountDto;
import com.example.board.repository.AccountRepository;
import com.example.board.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Account join(Account account) {
        Account findAccount = accountRepository.findByUsername(account.getUsername());

        if( findAccount == null){
            throw new UsernameNotFoundException("해당 하는 아이디를 찾을 수 없습니다.");
        }


    }
}
