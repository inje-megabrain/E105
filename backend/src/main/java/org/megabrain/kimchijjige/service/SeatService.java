package org.megabrain.kimchijjige.service;

import org.megabrain.kimchijjige.constant.SeatStatus;
import org.megabrain.kimchijjige.entity.Seat;
import org.megabrain.kimchijjige.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {

    private SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> allSeat(){
        Seat seat1 = Seat.builder()
                .createdTime(LocalDateTime.now())
                .position("A1")
                .status(SeatStatus.ASSIGN)
                .team("Dotgabit")
                .build();
        seatRepository.save(seat1);

        List<Seat> seats = seatRepository.findAll();

        return seats;
    }
}
