package com.example.board.controller;

import com.example.board.domain.Account;
import com.example.board.domain.AccountDto;
import com.example.board.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class LoginController {

    private final AccountService accountService;
    private final HttpSession httpSession;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model){
        model.addAttribute("error", error);
        return "account/login";
    }

    @PostMapping("/login")
    public String check() {
        httpSession.setAttribute("auth", "아아아아" );
        return "redirect:/";
    }

    @GetMapping("/session")
    @ResponseBody
    public String session(HttpServletRequest request){
        Object auth = httpSession.getAttribute("auth");
        System.out.println("auth = " + auth);
        return "성공";
    }


    @GetMapping("/register")
    public String register(){
        return "account/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam(value = "error", required = false) String error, Model model, AccountDto accountDto){
        model.addAttribute("error", error);
        accountService.join(accountDto);
        return "redirect:/";
    }
}
