package com.icia.member.controller;


import com.icia.member.dto.MemberDetailDTO;
import com.icia.member.dto.MemberLoginDTO;
import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor // final 키워드만 제공해주는 어노테이션
public class MemberController {

private final MemberService ms;


    @GetMapping("/")
    public String index(){
        return "index";
    }


    @GetMapping("saveform")
    public String saveForm(Model model){
        model.addAttribute("member123", new MemberSaveDTO());
        return "member/save";
    }

    @PostMapping("save")
    public String save(@Validated  @ModelAttribute("member123") MemberSaveDTO memberSaveDTO, BindingResult bindingResult){
        System.out.println(memberSaveDTO); // sout
        System.out.println("memberSaveDTO = " + memberSaveDTO); //soup

        if(bindingResult.hasErrors()){
            return "member/save";
        }

        ms.save(memberSaveDTO);

        return  "redirect:/member/login";
    }

    @GetMapping("login")
    public String loginForm(Model model ){
        model.addAttribute("login123" ,new MemberLoginDTO());

        return "/member/login";
    }


    @PostMapping("login")
    public String login(@Validated  @ModelAttribute("login123") MemberLoginDTO memberLoginDTO, BindingResult bindingResult
        , HttpSession session){

        if(bindingResult.hasErrors()){
            return "member/login";
        }
        boolean loginResult = ms.login(memberLoginDTO);
        if(ms.login(memberLoginDTO)){
            session.setAttribute("loginEmail", memberLoginDTO.getMemberEmail());
            return "redirect:/member/findAll";
        } else{
            // 로그인 결과를 글로벌오류(Global Error)
            bindingResult.reject("loginFail","이메일 또는 비밀번호가 틀립니다!!");
        return  "member/login";
        }

    }

        // 상세조회
        // /member/2, /member/15 => /member/{memberId}
        @GetMapping("{memberId}")
    public String findById(@PathVariable("memberId") Long memberId, Model model){

            System.out.println("memberId = " + memberId);
            MemberDetailDTO member = ms.findById(memberId);
            model.addAttribute("member", member);
            return "member/detail";
        }




}
