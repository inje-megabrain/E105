package org.megabrain.kimchijjige.controller;

import org.apache.coyote.Response;
import org.megabrain.kimchijjige.dto.MeetingDto;
import org.megabrain.kimchijjige.entity.Meeting;
import org.megabrain.kimchijjige.service.MeetingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MeetingController {

    private MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping("/meeting")
    public @ResponseBody
    ResponseEntity reservation(@RequestBody MeetingDto meetingDto) {
        Long id;
        try {
            id = meetingService.reservation(meetingDto);
        } catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(id + "번 예약되었습니다", HttpStatus.OK);    // TODO : User 추가하기
    }

    @GetMapping("/meeting")
    public @ResponseBody
    ResponseEntity getAllReservation() {
        List<Meeting> meeting = meetingService.getAllMeeting();
        return new ResponseEntity(meeting, HttpStatus.OK);
    }

    @GetMapping("/meeting/{id}")
    public @ResponseBody ResponseEntity getReservationDetail(@PathVariable("id") Long id) {
        Meeting meeting;
        try {
            meeting = meetingService.getMeeting(id);
        } catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(meeting, HttpStatus.OK);

    }

    @PutMapping("/meeting/{id}")
    public @ResponseBody
    ResponseEntity modifyReservation(@RequestBody MeetingDto meetingDto, @PathVariable("id") Long id) {

        try {
            meetingService.modifyMeeting(id, meetingDto);
        } catch (IllegalStateException e) {
            return new ResponseEntity(id + "번, 존재하지 않는 예약입니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("예약이 수정되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/meeting/{id}")
    public @ResponseBody
    ResponseEntity deleteReservation(@PathVariable("id") Long id) {
        try {
            meetingService.deleteMeeting(id);
        } catch (IllegalStateException e) {
            return new ResponseEntity(id + "번, 존재하지 않는 예약입니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("예약이 삭제되었습니다.", HttpStatus.OK);
    }
}
