package org.megabrain.kimchijjige.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.megabrain.kimchijjige.constant.MeetingStatus;
import org.megabrain.kimchijjige.dto.MeetingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String purpose;

    private String team;

    private int usingPeople;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private MeetingStatus meetingStatus;

    public static Meeting of(MeetingDto dto) {
        Meeting meeting = Meeting.builder()
                .purpose(dto.getPurpose())
                .team(dto.getTeam())
                .usingPeople(dto.getUsingPeople())
                .startTime(dto.getStartTime()) // TODO : 시간 Pattern 설정하기
                .endTime(dto.getEndTime())
                .meetingStatus(MeetingStatus.YET_USING)
                .build();
        return meeting;
    }

    public void update(MeetingDto dto) {
        this.purpose = dto.getPurpose();
        this.team = dto.getTeam();
        this.usingPeople = dto.getUsingPeople();
        this.startTime = dto.getStartTime();
        this.endTime = dto.getEndTime();
    }

    public void delete(){
        this.meetingStatus = MeetingStatus.DONE;
    }


}
