package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private final List<WiseSaying> list = new ArrayList<>();
    private int lastId = 0;

    public int enroll(String saying, String writer) {
        list.add(new WiseSaying(++lastId, saying, writer));
        return lastId;
    }

    public boolean delete(int id) {
        return list.removeIf(w -> w.num == id);
    }

    public void update(int id, String saying, String writer) {
        for (WiseSaying w : list) {
            if (w.num == id) {
                w.content = saying;
                w.author = writer;
                return;
            }
        }
    }

    public int getSize() {
        return list.size();
    }

    public List<WiseSaying> getWisSayingList() {
        return new ArrayList<>(list);
    }

    public WiseSaying getWiseSaying(int id) {
        for (WiseSaying w : list) {
            if (w.num == id) return w;
        }
        return null;
    }

    public void putSampleData() {
        for (int i = 1; i <= 10; i++) {
            list.add(new WiseSaying(++lastId, "명언 " + i, "작자미상 " + i));
        }
    }
}
