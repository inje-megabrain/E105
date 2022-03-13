package org.megabrain.kimchijjige.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class BordDto {
    private String title;
    private String content;
    private String author;

    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    public BordDto(String title, String content, LocalDateTime createdTime, LocalDateTime modifiedTime, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }
}
