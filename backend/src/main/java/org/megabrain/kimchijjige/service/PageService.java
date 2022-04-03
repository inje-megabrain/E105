package org.megabrain.kimchijjige.service;

import org.megabrain.kimchijjige.entity.Bord;
import org.megabrain.kimchijjige.repository.BordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    @Autowired
    BordRepository bordRepository;

    public Page<Bord> getAllBord(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Bord> bords = bordRepository.findAll(pageable);
        return bords;
    }
}
