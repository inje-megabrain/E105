package org.megabrain.kimchijjige.controller;

import org.hibernate.JDBCException;
import org.hibernate.exception.ConstraintViolationException;
import org.megabrain.kimchijjige.dto.LoginDto;
import org.megabrain.kimchijjige.dto.NewMemberDto;
import org.megabrain.kimchijjige.entity.Member;
import org.megabrain.kimchijjige.exception.NotEqualsPasswordException;
import org.megabrain.kimchijjige.repository.MemberRepository;
import org.megabrain.kimchijjige.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

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

    @GetMapping("/login")
    public @ResponseBody
    ResponseEntity login(@RequestBody LoginDto loginDto) {
        String login;
        try {
            login = memberService.login(loginDto);
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(login, HttpStatus.OK);
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