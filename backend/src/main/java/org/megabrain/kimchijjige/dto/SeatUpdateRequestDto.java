package org.megabrain.kimchijjige.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SeatUpdateRequestDto {
    private String position;
    private String team;

    public SeatUpdateRequestDto(String position, String team) {
        this.position = position;
        this.team = team;
    }
}
