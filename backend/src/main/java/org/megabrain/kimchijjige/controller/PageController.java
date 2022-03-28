package org.megabrain.kimchijjige.controller;

import org.megabrain.kimchijjige.entity.Bord;
import org.megabrain.kimchijjige.repository.BordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class PageController {
    private BordRepository bordRepository;
    public PageController(BordRepository repository){
        this.bordRepository = repository;
    }

    @GetMapping("/list")
    public Page<Bord> getAllBord(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size );
        return bordRepository.findAll(pageRequest);
    }

    /* 보드 100개 생성
    @PostConstruct
    public void initializing(){
        for (int i = 0; i < 100; i++){
            Bord bord = Bord.builder()
                    .author("User" + i)
                    .content("Body")
                    .createdTime(LocalDateTime.now())
                    .modifiedTime(LocalDateTime.now())
                    .title("title")
                    .build();
            bordRepository.save(bord);
        }
    }*/
}
