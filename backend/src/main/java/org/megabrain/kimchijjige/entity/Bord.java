package org.megabrain.kimchijjige.entity;

import lombok.*;
import org.megabrain.kimchijjige.constant.SeatStatus;
import org.megabrain.kimchijjige.dto.BordDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.stream.events.Comment;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdTime;

    private LocalDateTime modifiedTime;

    private String author;

    public static Bord of(BordDto dto) {
        Bord bord = Bord.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .createdTime(LocalDateTime.now())
                .modifiedTime(LocalDateTime.now())
                .author(dto.getAuthor())
                .build();
        return bord;
    }
    public void  update(String content, String title){
        this.content = content;
        this.title = title;
        this.modifiedTime = LocalDateTime.now();
    }


}
