package org.megabrain.kimchijjige.service;

import org.megabrain.kimchijjige.dto.BordDto;
import org.megabrain.kimchijjige.entity.Bord;

import org.megabrain.kimchijjige.exception.DuplicateSeatException;
import org.megabrain.kimchijjige.repository.BordRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BordService {

    private BordRepository bordRepository;

    public BordService(BordRepository bordRepository) {
        this.bordRepository = bordRepository;
    }

    public List<Bord> allContents(){

        List<Bord> bords = bordRepository.findAll();

        return bords;
    }

    public void addContents(BordDto dto){
        Bord bord = Bord.of(dto);
        bordRepository.save(bord);
    }

    public void delete(Long id){
        Bord bord = bordRepository.findById(id)
                .orElseThrow(()->new DuplicateSeatException("게시글이 없습니다."));
        bordRepository.delete(bord);
    }


    public void update(Long id, BordDto requestDto) {
        Bord bord = bordRepository.findById(id)
                .orElseThrow(()-> new DuplicateSeatException("게시글이 없습니다."));
        bord.update(requestDto.getContent(), requestDto.getTitle());
        bordRepository.save(bord);
    }

}
