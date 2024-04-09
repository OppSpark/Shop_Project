package com.oppspark.shop_porject.Controller;

import com.oppspark.shop_porject.Repository.MemberRepository;
import com.oppspark.shop_porject.Service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegController {
    //회원가입 기능 구현
    private final RegisterService registerService;
    @GetMapping("/register")
    public String Register(){
        return "register.html";
    }

    @PostMapping("/api/register")
    public String addRegister(
            String username,
            String password,
            String passwordVal,
            String displayName
    ) {
        registerService.register(username,password,passwordVal,displayName);
        return "list.html";
    }
}
