package org.megabrain.kimchijjige.auth;

import org.megabrain.kimchijjige.entity.Member;
import org.megabrain.kimchijjige.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

public class PrincipalDetailService implements UserDetailsService {

    private MemberRepository memberRepository;

    public PrincipalDetailService() {
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("해당 사용자가 없습니다. " + email);
                });
        return new PrincipalDetail(member);
    }
}
