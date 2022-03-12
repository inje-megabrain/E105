package org.megabrain.kimchijjige.repository;

import org.megabrain.kimchijjige.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    public Optional<Meeting> findByStartTimeBetween(LocalDateTime starTime, LocalDateTime endTime);

}
