package com.oppspark.shop_project.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MyPageController {
    @GetMapping("/mypage")
    public String myPage(Authentication auth){
        return"mypage.html";
    }
    @GetMapping("/logout")
    public String logout() {
        return"list.html";
    }
}
