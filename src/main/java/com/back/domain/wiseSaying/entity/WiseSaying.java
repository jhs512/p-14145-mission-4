package com.back.domain.wiseSaying.entity;

public class WiseSaying {
    public int num;
    public String content;
    public String author;

    public WiseSaying(int num, String wiseSaying, String writer) {
        this.num = num;
        this.content = wiseSaying;
        this.author = writer;
    }
}
