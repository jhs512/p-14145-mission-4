package com.back.domain.wiseSaying.service;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {
    WiseSayingRepository repo = new WiseSayingRepository();

    public int enroll(String Saying, String writer) {
        return repo.enroll(Saying, writer);
    }

    public void update(int id, String Saying, String writer) {
        repo.update(id, Saying, writer);
    }

    public List<WiseSaying> getWisSayingList() {
        return repo.getWisSayingList();
    }

    public WiseSaying getWiseSaying(int id) {
        return repo.getWiseSaying(id);
    }

    public boolean delete(int id) {
        return repo.delete(id);
    }

    public int getWiseSayingSize() {
        return repo.getSize();
    }

    public void putSampleData() {
        repo.putSampleData();
    }
}
