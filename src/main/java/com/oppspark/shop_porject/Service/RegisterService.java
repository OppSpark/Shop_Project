package com.oppspark.shop_porject.Service;

import com.oppspark.shop_porject.Entity.Member;
import com.oppspark.shop_porject.Repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final MemberRepository memberRepository;

    public void register (
            String username,
            String password,
            String passwordVal,
            String displayName
    ){

        if(!password.equals(passwordVal))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        if(username.length() <6 || username.length()>12)
            throw new IllegalArgumentException("ID의 길이는 6 ~ 12자리 입니다.");
        if(password.length() < 8 || password.length()>20)
            throw new IllegalArgumentException("PW의 길이는 8 ~ 20자리 입니다.");
        if(displayName.length() < 2 || displayName.length()>10)
            throw new IllegalArgumentException("닉네임의 길이는 2 ~ 10자리 입니다.");

        String hashPW = BCrypt.hashpw(password, BCrypt.gensalt());

        //중복 검사 해봐야함

        Member member = new Member();
        member.setUsername(username);
        member.setPassword(hashPW);
        member.setDisplayName(displayName);

        memberRepository.save(member);
    }
}
