package com.back.domain.wiseSaying.service;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.ArrayList;


public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository;

    // 리포지토리 주입
    public WiseSayingService(WiseSayingRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
    }

    // 명언 등록
    public int write(String content, String author) {
        return wiseSayingRepository.save(content, author);
    }

    // 명언 목록
    public ArrayList<WiseSaying> findAll() {
        return wiseSayingRepository.findAll();
    }

    // 명언 수정
    public boolean modify(int id, String content, String author) {
        WiseSaying foundWiseSaying = findById(id);

        if (foundWiseSaying == null) {
            return false;
        }

        // 객체가 존재하는 경우 리포지토리의 수정 호출
        wiseSayingRepository.update(foundWiseSaying, content, author);
        return true;
    }

    // 명언 삭제
    public boolean remove(int id) {
        WiseSaying foundWiseSaying = findById(id);

        if (foundWiseSaying == null) {
            return false;
        }

        // 객체가 존재하는 경우 리포지토리의 삭제 호출
        wiseSayingRepository.remove(foundWiseSaying);
        return true;
    }

    // ID로 명언 조회
    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }

}
