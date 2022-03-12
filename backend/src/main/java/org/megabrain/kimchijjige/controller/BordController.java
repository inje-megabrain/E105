package org.megabrain.kimchijjige.controller;


import org.megabrain.kimchijjige.dto.BordDto;
import org.megabrain.kimchijjige.dto.SeatAddRequestDto;
import org.megabrain.kimchijjige.entity.Bord;
import org.megabrain.kimchijjige.entity.Seat;
import org.megabrain.kimchijjige.exception.DuplicateSeatException;
import org.megabrain.kimchijjige.service.BordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BordController {

    private BordService bordService;

    public BordController(BordService bordService) {
        this.bordService = bordService;
    }

    @GetMapping("/bord")
    public @ResponseBody
    ResponseEntity getAllContents(){
        List<Bord> bords = bordService.allContents();
        return new ResponseEntity<>(bords, HttpStatus.OK );//데이터, 상태코드
    }

    @PostMapping("/bord")
    public @ResponseBody
    ResponseEntity addSeat(@RequestBody BordDto requestDto) {
        try {
            bordService.addContents(requestDto);
        } catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity("저장되었습니다", HttpStatus.OK);
    }

    @DeleteMapping("/bord/{id}")
    public @ResponseBody
    ResponseEntity deleteContent(@PathVariable("id") Long id){
        try {
            bordService.delete(id);
        } catch (DuplicateSeatException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("삭제되었습니다", HttpStatus.OK);
    }

}
