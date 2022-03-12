package org.megabrain.kimchijjige.service;

import org.megabrain.kimchijjige.dto.LoginDto;
import org.megabrain.kimchijjige.dto.NewMemberDto;
import org.megabrain.kimchijjige.entity.Member;
import org.megabrain.kimchijjige.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void login(LoginDto loginDto) {

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
