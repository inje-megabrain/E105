package org.megabrain.kimchijjige.service;

import lombok.RequiredArgsConstructor;
import org.megabrain.kimchijjige.dto.SeatAddRequestDto;
import org.megabrain.kimchijjige.entity.Seat;
import org.megabrain.kimchijjige.repository.SeatRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    private SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> allSeat(){

        List<Seat> seats = seatRepository.findAll();

        return seats;
    }

    public void addSeat(SeatAddRequestDto dto){
        Seat seat = Seat.of(dto);
        seatRepository.save(seat);
    }

   @Transactional
    public Seat findId(Long id){
       Seat find = notFoundSeat(id );
       return find;
   }

    private Seat notFoundSeat(Long id) {
        Optional<Seat> savedSeat = seatRepository.findById(id);
        if (savedSeat.isEmpty()) {
            throw new IllegalStateException();
        }
        return savedSeat.get();
    }
}
