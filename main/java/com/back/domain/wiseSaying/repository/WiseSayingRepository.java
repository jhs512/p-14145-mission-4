package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;

public class WiseSayingRepository {
    private final ArrayList<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastID = 1;

    // 명언 저장하기
    public int save(String content, String author) {
        // 명언 객체 생성 후 저장
        WiseSaying wiseSaying = new WiseSaying(lastID, content, author);
        wiseSayings.add(wiseSaying);
        
        // 몇 번째 명언인지 저장
        int savedId = lastID;
        lastID++;

        return savedId;
    }

    // 명언 리스트 불러오기
    public ArrayList<WiseSaying> findAll() {
        // 새 리스트에 담아서 반환
        return new ArrayList<>(wiseSayings);
    }

    // 명언 수정하기
    public void update(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }

    // 명언 삭제하기
    public void remove(WiseSaying wiseSaying) {
        wiseSayings.remove(wiseSaying);
    }

    // ID로 명언 찾기
    public WiseSaying findById(int id) {
        for (WiseSaying ws : wiseSayings) {
            if (ws.getId() == id) {
                return ws;
            }
        }
        return null;
    }
}
