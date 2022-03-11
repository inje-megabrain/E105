package org.megabrain.kimchijjige.controller;

import org.megabrain.kimchijjige.entity.Seat;
import org.megabrain.kimchijjige.service.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SeatController {

    private SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/seat")
    public @ResponseBody
    ResponseEntity getAllSeat(){
        List<Seat> seats = seatService.allSeat();

        return new ResponseEntity<>(seats, HttpStatus.OK );//데이터, 상태코드
    }
}
