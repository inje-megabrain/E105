package org.megabrain.kimchijjige.service;

import lombok.RequiredArgsConstructor;
import org.megabrain.kimchijjige.constant.SeatStatus;
import org.megabrain.kimchijjige.dto.SeatAddRequestDto;
import org.megabrain.kimchijjige.dto.SeatUpdateRequestDto;
import org.megabrain.kimchijjige.entity.Seat;
import org.megabrain.kimchijjige.exception.DuplicateSeatException;
import org.megabrain.kimchijjige.repository.SeatRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.ArrayList;
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
        List<Seat> result = new ArrayList<>();

        for (Seat seat : seats) {
            if (seat.getStatus().equals(SeatStatus.ASSIGN)) {
                result.add(seat);
            }
        }

        return result;
    }

    public void addSeat(SeatAddRequestDto dto){
        Optional<Seat> find = seatRepository.findByPositionEquals(dto.getPosition());
        if (find.isPresent()) {
            throw new IllegalStateException("이미 자리가 있습니다");
        }

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


    @Transactional
    public Long update(Long id, SeatUpdateRequestDto requestDto) {
        Seat seat = validateDuplicateSeat(id);
        Optional<Seat> samePosition = seatRepository.findByPositionEquals(requestDto.getPosition());
        if (samePosition.isPresent()) {
            throw new IllegalStateException("이미 자리가 있습니다");
        }
        seat.update(requestDto.getPosition(), requestDto.getTeam());
        seatRepository.save(seat);
        return id;
    }

    private Seat validateDuplicateSeat(Long id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new DuplicateSeatException("해당 사용자가 없습니다 id = " + id));

    }
    public void delete(Long id){
        Seat seat=seatRepository.findById(id)
                .orElseThrow(()->new DuplicateSeatException("해당 사용자가 없습니다 id = " + id));
        seat.delete();
        seatRepository.save(seat);
    }

}
