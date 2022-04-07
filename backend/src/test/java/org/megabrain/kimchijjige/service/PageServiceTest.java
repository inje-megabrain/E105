package org.megabrain.kimchijjige.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.megabrain.kimchijjige.entity.Bord;
import org.megabrain.kimchijjige.repository.BordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PageServiceTest {
    @Autowired
    private PageService pageService;

    @Autowired
    private BordRepository repository;

    @BeforeEach
    public void setUp(){
        for(int i = 0 ; i < 100; i++){
            Bord bord = Bord.builder()
                    .author("User" + i)
                    .content("Body")
                    .createdTime(LocalDateTime.now())
                    .modifiedTime(LocalDateTime.now())
                    .title("title")
                    .build();
            repository.save(bord);
        }
    }

    @Test
    void 페이지네이션테스트(){

        Page<Bord> page = pageService.getAllBord(0, 10, "createdTime", false);
        assertEquals(page.getTotalPages(), 10);

    }

}