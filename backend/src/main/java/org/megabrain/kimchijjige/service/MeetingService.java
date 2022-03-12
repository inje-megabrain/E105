package org.megabrain.kimchijjige.service;

import org.megabrain.kimchijjige.constant.MeetingStatus;
import org.megabrain.kimchijjige.dto.MeetingDto;
import org.megabrain.kimchijjige.entity.Meeting;
import org.megabrain.kimchijjige.repository.MeetingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MeetingService {

    private MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public Long reservation(MeetingDto meetingDto) { // 숫자
        validateDuplicateMeeting(meetingDto);

        Meeting newMeeting = Meeting.of(meetingDto);
        meetingRepository.save(newMeeting);
        return newMeeting.getId();
    }

    private void validateDuplicateMeeting(MeetingDto meetingDto) {
        Optional<Meeting> find = meetingRepository.findByStartTimeBetween(meetingDto.getStartTime(), meetingDto.getEndTime());
        if (find.isPresent()) {
            throw new IllegalStateException("해당 시간대에 이미 예약되었습니다.");
        }
    }

    public List<Meeting> getAllMeeting() {
        List<Meeting> finds = meetingRepository.findAll();
        List<Meeting> result = new ArrayList<>();
        for (Meeting meeting : finds) {
            if (meeting.getMeetingStatus().equals(MeetingStatus.YET_USING)) {
                result.add(meeting);
            }
        }
        return result;
    }

    public Meeting getMeeting(Long id) {
        Meeting find = meetingRepository.findById(id)
                .orElseThrow(() -> {
                    throw new IllegalStateException("존재하지 않는 예약입니다.");
                });
        return find;
    }

    public void modifyMeeting(Long id, MeetingDto meetingDto) {
        // TODO : 시간대 말고, 조건절 하나 더 추가해서 가져오기
        Optional<Meeting> find = meetingRepository.findById(id);

        if (find.isEmpty()) {
            throw new IllegalStateException();
        }

        find.get().update(meetingDto);

        meetingRepository.save(find.get());
    }

    public void deleteMeeting(Long id) {
        Optional<Meeting> find = meetingRepository.findById(id);

        if (find.isEmpty()) {
            throw new IllegalStateException();
        }
        find.get().delete();
        meetingRepository.save(find.get());
    }
}
