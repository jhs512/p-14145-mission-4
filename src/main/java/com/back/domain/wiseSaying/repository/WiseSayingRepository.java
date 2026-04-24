package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSayingEntity;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {

    private final List<WiseSayingEntity> wiseSayings = new ArrayList<>();
    private int _top = 0;

    public List<WiseSayingEntity> findForList() {
        return wiseSayings.reversed();
    }

    public WiseSayingRepository() {
        while (wiseSayings.size() < 10) {
            addWiseSaying(new WiseSayingEntity(
                    "명언 %d".formatted(wiseSayings.size() + 1),
                    "작자미상%d".formatted(wiseSayings.size() + 1)));
        }
    }

    public void addWiseSaying(WiseSayingEntity newWise) {
        newWise.setId(++_top);
        wiseSayings.add(newWise);
    }

    public boolean editWiseSaying(int id, WiseSayingEntity newWise) {
        int idx = findId(id);
        if (idx < 0) return false;

        newWise.setId(idx);
        wiseSayings.set(idx, newWise);

        return true;
    }

    public WiseSayingEntity findWiseSaying(int id) {
        int idx = findId(id);
        if (idx < 0) return null;
        return wiseSayings.get(idx);
    }

    public boolean deleteWiseSaying(int id) {
        int idx = findId(id);
        if (idx < 0) return false;

        wiseSayings.remove(idx);
        return true;
    }

    private int findId(int targetId) {
        int start = 0;
        int end = wiseSayings.size();
        while (start < end) {
            int now = (start + end) / 2;

            WiseSayingEntity entity = wiseSayings.get(now);

            if (entity.getId() == targetId) {
                return now;
            }
            if (entity.getId() < targetId) {
                start = now + 1;
                continue;
            }
            end = now;
        }
        return -1;
    }

}
