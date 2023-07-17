package com.zzdd.smallspending.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/user")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @PostMapping("/sign_up")
    public String signUp(MemberDto memberDto){

        int result = memberService.signUp(memberDto);
        if(result != 0){
            return "success";
        }
        return "fail";
    }

    @GetMapping("/check_id")
    public String checkId(String id) {
        boolean idExist = memberService.isIdExist(id);
        if(idExist){
            return "true";
        }
        return "false";
    }

    @GetMapping("/check_nick")
    public String checkNick(String nick) {
        boolean nickExist = memberService.isNickExist(nick);
        if(nickExist){
            return "true";
        }
        return "false";
    }
    

}
