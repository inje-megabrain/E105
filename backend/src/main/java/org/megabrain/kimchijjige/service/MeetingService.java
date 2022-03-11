package org.megabrain.kimchijjige.service;

import org.megabrain.kimchijjige.dto.MeetingDto;
import org.megabrain.kimchijjige.entity.Meeting;
import org.megabrain.kimchijjige.repository.MeetingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingService {

    private MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public void reservation(MeetingDto meetingDto) {
        Meeting meeting = Meeting.of(meetingDto);
        meetingRepository.save(meeting);
    }

    public List<MeetingDto> getAllMeeting() {
        List<Meeting> meetings = meetingRepository.findAll();
        List<MeetingDto> meetingDtos = new ArrayList<>();

        for (Meeting meeting : meetings) {
            MeetingDto meetingDto = new MeetingDto();
            meetingDto.setPurpose(meeting.getPurpose());
            meetingDto.setTeam(meeting.getTeam());
            meetingDto.setUsingPeople(meeting.getUsingPeople());
            meetingDto.setStartTime(meeting.getStartTime());
            meetingDto.setEndTime(meeting.getEndTime());

            meetingDtos.add(meetingDto);
        }
        return meetingDtos;
    }
}
