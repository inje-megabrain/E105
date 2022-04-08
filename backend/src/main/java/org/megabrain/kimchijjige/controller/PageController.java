package org.megabrain.kimchijjige.controller;

import org.megabrain.kimchijjige.entity.Bord;
import org.megabrain.kimchijjige.repository.BordRepository;
import org.megabrain.kimchijjige.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class PageController {
    private BordRepository bordRepository;
    @Autowired
    private PageService pageService;
    public PageController(BordRepository repository){
        this.bordRepository = repository;
    }

    @GetMapping("/list")
    public Page<Bord> getAllBord(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("sortBy") String sortBY, @RequestParam("isAsc") Boolean isAsc) {

        return pageService.getAllBord(page, size, sortBY ,isAsc);
    }

}
