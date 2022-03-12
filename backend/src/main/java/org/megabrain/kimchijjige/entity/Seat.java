package org.megabrain.kimchijjige.entity;

import lombok.*;
import org.megabrain.kimchijjige.constant.SeatStatus;
import org.megabrain.kimchijjige.dto.SeatAddRequestDto;
import org.megabrain.kimchijjige.dto.SeatUpdateRequestDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String position;

    private String team;

    private LocalDateTime createdTime;

    @Enumerated(EnumType.STRING) //Enum인지 알려주는것 -> 타입은 문자열
    private SeatStatus status;

    public static Seat of(SeatAddRequestDto dto) {
        Seat seat = Seat.builder()
                .position(dto.getPosition())
                .team(dto.getTeam())
                .status(SeatStatus.ASSIGN)
                .createdTime(LocalDateTime.now())
                .build();
        return seat;
    }

    public void update(String position, String team){
        this.position = position;
        this.team = team;
    }

    public void delete(){
        this.status =SeatStatus.NON_ASSIGN;
    }

}
