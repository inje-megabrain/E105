package org.megabrain.kimchijjige.repository;

import org.megabrain.kimchijjige.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    public Optional<Seat> findByPositionEquals(String position);
}
