package org.megabrain.kimchijjige.entity;

import lombok.*;
import org.megabrain.kimchijjige.constant.SeatStatus;

import javax.persistence.*;
import java.time.LocalDate;
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
}
