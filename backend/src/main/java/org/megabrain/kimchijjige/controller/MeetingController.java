package org.megabrain.kimchijjige.controller;

import org.megabrain.kimchijjige.dto.MeetingDto;
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
        meetingService.reservation(meetingDto);
        return new ResponseEntity("예약되었습니다", HttpStatus.OK);
    }

    @GetMapping("/meeting")
    public @ResponseBody
    ResponseEntity getAllReservation() {
        List<MeetingDto> meeting = meetingService.getAllMeeting();
        return new ResponseEntity(meeting, HttpStatus.OK);
    }
}
