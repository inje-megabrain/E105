package org.megabrain.kimchijjige.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class NewMemberDto {

    private String name;

    private String email;

    private String studentId;

    private String password;

    private String team;

}

