package com.back.domain.wiseSaying.repository;

import com.back.WiseSaying;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    public int lastId = 0;
    private final List<WiseSaying> wiseSayings = new ArrayList<>();
    public List<WiseSaying> findForList() {
        return findForList("", "");
    }

    public List<WiseSaying> findForList(String keywordType, String keyword) {
        return wiseSayings
                .reversed()
                .stream()
                .filter(wiseSaying -> isMatched(wiseSaying, keywordType, keyword))
                .toList();
    }

    public int count() {
        return wiseSayings.size();
    }

    private boolean isMatched(WiseSaying wiseSaying, String keywordType, String keyword) {
        if (keywordType.isEmpty() || keyword.isEmpty()) return true;

        return switch (keywordType) {
            case "content" -> wiseSaying.getContent().contains(keyword);
            case "author" -> wiseSaying.getAuthor().contains(keyword);
            default -> true;
        };
    }

    public void save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            wiseSaying.setId(++lastId);
            LocalDateTime now = LocalDateTime.now();
            wiseSaying.setCreateDate(now);
            wiseSaying.setModifyDate(now);
            wiseSayings.add(wiseSaying);
        } else{
            wiseSaying.setModifyDate(LocalDateTime.now());
        }
    }
    public WiseSaying findById(int id) {
        return wiseSayings
                .stream()
                .filter(wiseSaying -> wiseSaying.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public int findIndexById(int id) {
        for (int i = 0; i < wiseSayings.size(); i++) {
            if (wiseSayings.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
    public boolean delete(int id) {
        int deleteIndex = findIndexById(id);

        if (deleteIndex == -1) return false;

        wiseSayings.remove(deleteIndex);

        return true;
    }
    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }
}
