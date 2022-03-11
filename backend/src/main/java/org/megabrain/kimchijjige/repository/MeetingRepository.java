package org.megabrain.kimchijjige.repository;

import org.megabrain.kimchijjige.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    // TODO : 시간대 체크해서 DB 가져오기
}
