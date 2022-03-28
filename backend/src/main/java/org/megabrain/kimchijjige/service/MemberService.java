package org.megabrain.kimchijjige.service;

import lombok.extern.slf4j.Slf4j;
import org.megabrain.kimchijjige.dto.LoginDto;
import org.megabrain.kimchijjige.dto.NewMemberDto;
import org.megabrain.kimchijjige.entity.Member;
import org.megabrain.kimchijjige.exception.NotEqualsPasswordException;
import org.megabrain.kimchijjige.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MemberService {



    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void newMember(NewMemberDto memberDto) {
        validateDuplicateMember(memberDto.getEmail());
        Member member = Member.createMember(memberDto);
        memberRepository.save(member);
    }


    public String login(LoginDto loginDto) {

        Optional<Member> member =  memberRepository.findByEmail(loginDto.getEmail());
        if(member.isEmpty()) {
            throw new IllegalStateException("다시 확인하세요");
        }
        else if(member.isPresent()){
            if(!member.get().getPassword().equals(loginDto.getPassword())) {
                throw new NotEqualsPasswordException("비밀번호를 다시 확인하세요");

            }
        }
        return "Login 성공";
    }


    private void validateDuplicateMember(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> allMember() {
        return memberRepository.findAll();
    }
}
