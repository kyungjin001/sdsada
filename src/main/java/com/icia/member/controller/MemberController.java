package com.icia.member.controller;


import com.icia.member.dto.MemberLoginDTO;
import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
        model.addAttribute("member", new MemberSaveDTO());
        return "save";
    }

    @PostMapping("save")
    public String save(@Validated  @ModelAttribute("member") MemberSaveDTO memberSaveDTO, BindingResult bindingResult){
        System.out.println(memberSaveDTO); // sout
        System.out.println("memberSaveDTO = " + memberSaveDTO); //soup

        if(bindingResult.hasErrors()){
            return "save";
        }
// jghj
        ms.save(memberSaveDTO);

        return  "redirect:/member/login";
    }

    @GetMapping("login")
    public String loginForm(Model model ){
        model.addAttribute("login" ,new MemberLoginDTO());

        return "login";
    }


    @PostMapping("login")
    public String login(@Validated  @ModelAttribute("login") MemberLoginDTO memberLoginDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "login";
        }

        return  "redirect:/member/findAll";
    }

        // 상세조회
        // /member/2, /member/15 => /member/{memberId}
        @GetMapping("{memberId}")
    public String findById(@PathVariable("memberId") Long memberId, Model model){

            System.out.println("memberId = " + memberId);
            return "member/detail";
        }




}
