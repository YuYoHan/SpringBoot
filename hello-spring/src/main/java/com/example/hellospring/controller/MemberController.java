package com.example.hellospring.controller;

import com.example.hellospring.domain.UserDTO;
import com.example.hellospring.service.MemberService;
import com.example.hellospring.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;
    @Setter(onMethod_ = @Autowired)
    private UserService userService;


    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

//    @PostMapping("/members/new")
//    public String create(MemberForm form) {
//        Member member = new Member();
//        member.setName(form.getName());
//
//        memberService.join(member);
//
//        return "redirect:/";
//    }

//    @GetMapping("/members")
//    public String list(Model model) {
//        List<Member> members = memberService.findMembers();
//        // 화면으로 보여주려면 model 안에 담아줘서 보내줘야 한다.
//        model.addAttribute("members", members);
//        return "members/memberList";
//    }


    @GetMapping("/members/signUp")
    public String saveForm() {
        return "/members/signUp";
    }

    @GetMapping("/members/login")
    public String loginForm() {
        return "/members/login";
    }

    @PostMapping("/members/signUp")
    public String join(UserDTO user, HttpServletResponse resp) {
        if(userService.join(user)) {
            Cookie joinId = new Cookie("joinId", user.getUserId());
            joinId.setMaxAge(300);
            resp.addCookie(joinId);
        }
        return "redirect:/";
    }

    @PostMapping("/members/login")
    public String login(String userId, String userPw, HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        UserDTO user = userService.login(userId, userPw);
        if(user != null) {
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userPw", user.getUserPw());
            model.addAttribute("userId", (String)session.getAttribute("userId"));
            model.addAttribute("userPw", (String)session.getAttribute("userPw"));

        }
        return "home";
    }
}
