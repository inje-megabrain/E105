package org.megabrain.kimchijjige.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.megabrain.kimchijjige.constant.Role;
import org.megabrain.kimchijjige.dto.LoginDto;
import org.megabrain.kimchijjige.dto.NewMemberDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true, nullable = false)
    private String studentId;

    private String password;

    @Column(nullable = false)
    private String team;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(NewMemberDto memberDto) {
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .name(memberDto.getName())
                .studentId(memberDto.getStudentId())
                .password(new BCryptPasswordEncoder().encode(memberDto.getPassword()))
                .role(Role.GUEST)
                .team(memberDto.getTeam())
                .build();
        return member;
    }
}
