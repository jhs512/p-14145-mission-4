package com.back.domain.wiseSaying.entity;

import java.util.List;

public class Page {

    private List<WiseSaying> wiseSayings;
    private int currentPage;
    private int totalPage;

    public Page(List<WiseSaying> wiseSayings, int currentPage, int totalPage) {
        this.wiseSayings = wiseSayings;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }

    public List<WiseSaying> getWiseSayings() {
        return wiseSayings;
    }

    public void setWiseSayings(List<WiseSaying> wiseSayings) {
        this.wiseSayings = wiseSayings;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
