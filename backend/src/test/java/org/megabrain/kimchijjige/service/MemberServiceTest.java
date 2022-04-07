package org.megabrain.kimchijjige.service;

import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.megabrain.kimchijjige.dto.LoginDto;
import org.megabrain.kimchijjige.entity.Member;
import org.megabrain.kimchijjige.exception.NotEqualsPasswordException;
import org.megabrain.kimchijjige.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @BeforeEach
    public void setUp(){
        memberRepository.deleteAll();
        Member member = Member.builder()
                .email("sbs@gg.com")
                .password("1234")
                .name("성병석")
                .team("Mega")
                .studentId("20182626")
                .build();
        memberRepository.save(member);
    }

    @Test
    void 로그인(){
        //given
        LoginDto dto = new LoginDto();
        dto.setEmail("sbs@gg.com");
        dto.setPassword("1234");
        //when
       String msg =  memberService.login(dto);
       //then
       assertThat(msg).isEqualTo("Login 성공");
    }

    @Test
    void 비밀번호_오류_테스트(){
        LoginDto dto = new LoginDto();
        dto.setEmail("sbs@gg.com");
        dto.setPassword("2345");

        try{
            memberService.login(dto);
        }catch (NotEqualsPasswordException e){
            assertThat(e.getMessage()).isEqualTo("비밀번호를 다시 확인하세요");
        }

    }

    @Test
    void 아이디_오류_테스트(){
        LoginDto dto = new LoginDto();
        dto.setEmail("sh@gg.com");
        dto.setPassword("1234");

        try{
            memberService.login(dto);
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("다시 확인하세요");
        }

    }

}