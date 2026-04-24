package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {

    private List<WiseSaying> wiseSayings = new ArrayList<>();

    public void save(WiseSaying wiseSaying) {
        wiseSayings.add(wiseSaying);
    }

    public WiseSaying findById(int id) {
        for (WiseSaying wiseSaying : wiseSayings) {
            if (wiseSaying.getId() == id) {
                return wiseSaying;
            }
        }
        return null;
    }

    public List<WiseSaying> findAll() {
        return wiseSayings;
    }

    public void delete(WiseSaying wiseSaying ) {
        wiseSayings.remove(wiseSaying);
    }

    public void update(int id, String content, String author) {
        WiseSaying wiseSaying = findById(id);
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }


}
