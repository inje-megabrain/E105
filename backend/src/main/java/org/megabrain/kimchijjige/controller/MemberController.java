package org.megabrain.kimchijjige.controller;

import jdk.jfr.Frequency;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.megabrain.kimchijjige.auth.SessionConst;
import org.megabrain.kimchijjige.dto.LoginDto;
import org.megabrain.kimchijjige.dto.NewMemberDto;
import org.megabrain.kimchijjige.entity.Member;
import org.megabrain.kimchijjige.exception.NotEqualsPasswordException;
import org.megabrain.kimchijjige.repository.MemberRepository;
import org.megabrain.kimchijjige.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    @Autowired
    //private SessionManager sessionManager;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member")
    public @ResponseBody
    ResponseEntity addMember(@RequestBody NewMemberDto newMemberDto) {
        try {
            memberService.newMember(newMemberDto);
        } catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity("이미 등록된 학번/이메일 입니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("회원가입 완료", HttpStatus.OK);
    }

     @PostMapping("/login")
     public @ResponseBody
     ResponseEntity login(@RequestBody LoginDto loginDto, BindingResult result, HttpServletRequest request) {
         if(result.hasErrors())
             return new ResponseEntity("로그인에러", HttpStatus.BAD_REQUEST);
         String login;
         try{
             login = memberService.login(loginDto);
             if(login ==null){
                 result.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
                 return new ResponseEntity("로그인에러", HttpStatus.BAD_REQUEST);
             }
         }catch (RuntimeException e){
             return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
         }
         HttpSession session = request.getSession();
         session.setAttribute(SessionConst.LOGIN_MEMBER, loginDto);


         return new ResponseEntity(login, HttpStatus.OK);
     }

     @PostMapping("/logout")
     public @ResponseBody
     ResponseEntity logout(HttpServletRequest request){
         HttpSession session = request.getSession(false);
         if(session != null)
             session.invalidate();
         return  new ResponseEntity("로그아웃", HttpStatus.OK);
     }


    @GetMapping("/member")
    public @ResponseBody
    ResponseEntity getAllMember() {

        List<Member> memberList;
        try {
            memberList = memberService.allMember();
        } catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(memberList, HttpStatus.OK);
    }
}
