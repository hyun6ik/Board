package com.example.board.service.impl;

import com.example.board.domain.Account;
import com.example.board.domain.AccountDto;
import com.example.board.repository.AccountRepository;
import com.example.board.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service("accountService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Long join(AccountDto accountDto) {
        boolean accountExists = accountRepository.findByUsername(accountDto.getUsername()).isPresent();

        System.out.println("accountExists = " + accountExists);
        if(accountExists){
            throw new IllegalStateException("해당 계정이 이미 존재합니다.");
        }

        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(accountDto, Account.class);
//        account.EncryptPassword(passwordEncoder.encode(accountDto.getPassword()));
        System.out.println("account = " + account);

        accountRepository.save(account);
        System.out.println("DB등록 성공");
        return account.getId();
    }

    public boolean check(HttpServletRequest request) {
        String username = request.getParameter("username");
        System.out.println("username = " + username);
        String password = request.getParameter("password");
        System.out.println("password = " + password);

        Account findAccount = accountRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("아이디없음"));

        String findPassword = accountRepository.findByPassword(findAccount.getPassword());
        if(!passwordEncoder.matches(password, findPassword)){
            return false;
        }
        return true;
    }

}
