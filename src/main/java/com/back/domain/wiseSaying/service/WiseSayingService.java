package com.back.domain.wiseSaying.service;


import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingService {
    WiseSayingRepository wiseSayingRepository;

    public WiseSayingService(WiseSayingRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
    }

    public void register(int id, String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayingRepository.save(wiseSaying);
    }

    public List<WiseSaying> getList() {
        return wiseSayingRepository.findAll();
    }

    public boolean delete (int id) {
        WiseSaying byId = wiseSayingRepository.findById(id);
        if (byId != null) {
            wiseSayingRepository.delete(byId);
            return true;
        }
        return false;
    }

    public void modify (int id, String content, String author) {
        wiseSayingRepository.update(id, content, author);
    }

    public WiseSaying getById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public List<WiseSaying> findByKeyword(String keywordType, String keyword) {
        List<WiseSaying> wiseSayings = wiseSayingRepository.findAll();
        List<WiseSaying> newWiseSayings = new ArrayList<>();

        if (keywordType.equals("content")) {
            for (WiseSaying wiseSaying : wiseSayings) {
                if (wiseSaying.getContent().contains(keyword)) {
                    newWiseSayings.add(wiseSaying);
                }
            }
            return newWiseSayings;
        }

        if (keywordType.equals("author")) {
            for (WiseSaying wiseSaying : wiseSayings) {
                if (wiseSaying.getAuthor().contains(keyword)) {
                    newWiseSayings.add(wiseSaying);
                }
            }
            return newWiseSayings;
        }
        return null;
    }
}
