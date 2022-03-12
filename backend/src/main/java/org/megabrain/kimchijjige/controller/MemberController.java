package org.megabrain.kimchijjige.controller;

import org.megabrain.kimchijjige.dto.NewMemberDto;
import org.megabrain.kimchijjige.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;

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
        }
        return new ResponseEntity("회원가입 완료", HttpStatus.OK);
    }
}
