package com.oppspark.shop_porject.Controller;

import com.oppspark.shop_porject.Entity.Member;
import com.oppspark.shop_porject.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegController {
    //회원가입 기능 구현
    private final MemberRepository memberRepository;

    @GetMapping("/register")
    public String Register(){

        return "register.html";
    }


    @PostMapping("/api/register")
    public String addRegister(
            String username,
            String password,
            String displayName
    ) {
        //PW 를 암호화해서 저장
        String hashPW = BCrypt.hashpw(password, BCrypt.gensalt());

        Member member = new Member();
        member.setUsername(username);
        member.setPassword(hashPW);
        member.setDisplayName(displayName);

        System.out.println(member);

        memberRepository.save(member);

        return "list.html";
    }
}
