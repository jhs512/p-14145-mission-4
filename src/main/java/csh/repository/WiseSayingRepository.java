package csh.repository;

import csh.entity.WiseSaying;

import java.util.*;

public class WiseSayingRepository {
    private Map<Integer, WiseSaying> map = new TreeMap<>(Comparator.reverseOrder());

    public void save(WiseSaying wiseSaying) {
        map.put(wiseSaying.getId(), wiseSaying);
    }

    public Map<Integer,WiseSaying> findAll() {
        return map;
    }

    public WiseSaying findById(int id) {
        return map.get(id);
    }

    public void update(WiseSaying wiseSaying) {
        save(wiseSaying);
    }

    public WiseSaying delete(int id) {
        return map.remove(id);
    }
}
