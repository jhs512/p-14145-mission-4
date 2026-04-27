package com.back.domain.wiseSaying.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
public class WiseSaying {
    private int id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String content;
    private String author;

    public WiseSaying(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public boolean isNew(){
        return id == 0;
    }

}