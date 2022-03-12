package org.megabrain.kimchijjige.service;

import org.megabrain.kimchijjige.entity.Seat;
import org.megabrain.kimchijjige.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    private SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> allSeat() {

        List<Seat> seats = seatRepository.findAll();

        return seats;
    }
}
