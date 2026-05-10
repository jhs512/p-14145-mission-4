package csh.service;

import csh.entity.WiseSaying;
import csh.repository.WiseSayingRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WiseSayingService {
    private int id = 1;
    private final WiseSayingRepository repo = new WiseSayingRepository();

    public int create(String content, String author) {
        WiseSaying ws = new WiseSaying
                .Builder()
                .id(id++)
                .author(author)
                .content(content)
                .build();
        repo.save(ws);
        return ws.getId();
    }

    public Map<Integer, WiseSaying> getList(String type, String keyword) {
        Map<Integer, WiseSaying> map = repo.findAll();
        return search(map, type, keyword);
    }

    public void update(int targetId, String content, String author) {
        WiseSaying ws = new WiseSaying
                .Builder()
                .id(targetId)
                .author(author)
                .content(content)
                .build();
        repo.update(ws);
    }

    public WiseSaying getWiseSayingById(int targetId) {
        WiseSaying ws = repo.findById(targetId);
        return ws;
    }

    public boolean deleteWiseSayingById(int targetId) {
        WiseSaying ws = repo.delete(targetId);
        if(ws == null) return false;
        return true;
    }

    private Map<Integer,WiseSaying> search(Map<Integer,WiseSaying> map, String type, String keyword){
        if("author".equals(type)) return filterByAuthor(map, keyword);
        else if("content".equals(type)) return filterByContent(map, keyword);
        else if("".equals(type) && !keyword.isEmpty()) return filterByKeyword(map, keyword);
        return map;
    }

    private Map<Integer,WiseSaying> filterByContent(Map<Integer, WiseSaying> map, String keyword) {
        Map<Integer,WiseSaying> newMap = new TreeMap<>(Comparator.reverseOrder());
        for(WiseSaying ws : map.values()) {
            if(ws.getContent().equals(keyword)) newMap.put(ws.getId(), ws);
        }
        return newMap;
    }
    private Map<Integer,WiseSaying> filterByAuthor(Map<Integer, WiseSaying> map, String keyword) {
        Map<Integer,WiseSaying> newMap = new TreeMap<>(Comparator.reverseOrder());
        for(WiseSaying ws : map.values()) {
            if(ws.getAuthor().equals(keyword)) newMap.put(ws.getId(), ws);
        }
        return newMap;
    }

    private Map<Integer,WiseSaying> filterByKeyword(Map<Integer, WiseSaying> map, String keyword) {
        Map<Integer,WiseSaying> newMap = new TreeMap<>(Comparator.reverseOrder());
        for(WiseSaying ws : map.values()) {
            if(ws.getContent().equals(keyword) || ws.getAuthor().equals(keyword)) newMap.put(ws.getId(), ws);
        }
        return newMap;
    }
}
