package com.back.domain.wiseSaying.service;

import com.back.AppContext;
import com.back.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {


    private final WiseSayingRepository wiseSayingRepository ;

    public List<WiseSaying> findForList() {
        return findForList("", "");
    }

    public List<WiseSaying> findForList(String keywordType, String keyword) {
        return wiseSayingRepository.findForList(keywordType, keyword);
    }

    public WiseSayingService(){
        this.wiseSayingRepository = AppContext.wiseSayingRepository;
    }

    public void makeSampleData() {
        if (wiseSayingRepository.count() > 0) return;

        for (int i = 1; i <= 10; i++) {
            write("명언 " + i, "작자미상 " + i);
        }
    }

    public WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(content,author);

        wiseSayingRepository.save(wiseSaying);

        return wiseSaying;
    }
    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }
    public boolean delete(int id) {
        return wiseSayingRepository.delete(id);
    }
    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);

        wiseSayingRepository.save(wiseSaying);
    }

}
