package com.back.domain.wiseSaying.entity;

public class WiseSayingEntity {
    int id;
    public String wise;
    public String author;

    public void setId(int newId) {
        id = newId;
    }
    public int getId() { return id; }
    public WiseSayingEntity(String wise, String author) {
        this.wise = wise;
        this.author = author;
    }
}
