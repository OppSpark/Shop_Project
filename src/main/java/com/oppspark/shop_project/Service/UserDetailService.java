package com.oppspark.shop_project.Service;

import com.oppspark.shop_project.Entity.Member;
import com.oppspark.shop_project.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> result = memberRepository.findByUsername(username);

        result.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        Member user = result.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("일반 사용자"));

        CustomUser customUser = new CustomUser(user.getUsername(),user.getPassword(),authorities);

        customUser.displayName = user.getDisplayName();

        //return new User(user.getUsername(), user.getPassword(), authorities);
        return customUser;
    }
}

class CustomUser extends User{
    public String displayName;
    public CustomUser(String username,
                      String password,
                      Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}