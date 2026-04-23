package com.back.domain.wiseSaying.controller;


import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private final WiseSayingService wiseSayingService;
    private final Scanner sc; // final로 선언해서 외부에서 주입받기

    // 서비스 주입
    public WiseSayingController(WiseSayingService wiseSayingService, Scanner sc) {
        this.wiseSayingService = wiseSayingService;
        this.sc = sc;
    }

    // 명언 등록
    public void doWrite() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();
        int id = wiseSayingService.write(content, author);
        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    // 명언 리스트
    public void showList(String command) {
        String keyword = getParam(command, "keyword", "");

        List<WiseSaying> wiseSayings = wiseSayingService.findAll();
        List<WiseSaying> resultList;

        // keyword가 비어있지 않다면 검색 모드
        if (!keyword.isEmpty()) {
            String keywordType = getParam(command, "keywordType", "");

            System.out.println("--------------------------");
            System.out.println("검색타입 : " + keywordType);
            System.out.println("검색어 : " + keyword);
            System.out.println("--------------------------");

            List<WiseSaying> filteredList = new ArrayList<>();

            for (WiseSaying ws : wiseSayings) {
                if (keywordType.equals("content")) {
                    if (ws.getContent().contains(keyword)) {
                        filteredList.add(ws);
                    }
                } else if (keywordType.equals("author")) {
                    if (ws.getAuthor().contains(keyword)) {
                        filteredList.add(ws);
                    }
                }
            }
            resultList = filteredList;
        }
        // keyword가 비어있다면 일반 모드
        else {
            resultList = wiseSayings;
        }
        List<WiseSaying> sortedList = new ArrayList<>(resultList);
        Collections.reverse(sortedList);

        System.out.println("번호 / 작가 / 명언");
        System.out.println("--------------------------");
        for (WiseSaying wiseSaying : sortedList) {
            System.out.println(wiseSaying.getId() + " / " + wiseSaying.getAuthor() + " / " +wiseSaying.getContent());
        }
    }

    // 명언 수정
    public void doModify(String command) {
        int id = getParamAsInt(command, "id", -1);
        if (id == -1) return;

        WiseSaying ws = wiseSayingService.findById(id);
        if(ws == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        System.out.println("명언(기존) : " + ws.getContent());
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.println("작가(기존) : " + ws.getAuthor());
        System.out.print("작가 : ");
        String author = sc.nextLine();

        if (wiseSayingService.modify(id, content, author)) {
            System.out.println(id + "번 명언이 수정되었습니다.");
        }
    }

    // 명언 삭제
    public void doRemove(String command) {
        int id = getParamAsInt(command, "id", -1);
        if (id == -1) return;

        WiseSaying ws = wiseSayingService.findById(id);
        if(ws == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        if (wiseSayingService.remove(id)) {
            System.out.println(id + "번 명언이 삭제되었습니다.");
        }
    }

    // 명령어 문자열에서 특정 파라미터의 값을 추출
    private String getParam(String command, String paramName, String defaultValue) {
        // 1. '?'가 없는 경우 파라미터가 없으므로 즉시 기본값 반환
        if (!command.contains("?")) {
            return defaultValue;
        }

        try {
            // 2. 명령어를 '?' 기준으로 나누어 데이터 부분(urlBits[1])만 취함
            String[] urlBits = command.split("\\?", 2);

            // 3. '&'를 기준으로 각 파라미터 쌍을 분리
            String[] paramBits = urlBits[1].split("&");

            for (String paramBit : paramBits) {
                // 4. '='를 기준으로 key와 value 분리
                String[] bit = paramBit.split("=", 2);

                // 5. 찾는 키(paramName)와 일치하고 값이 존재하는 경우 반환
                if (bit.length == 2 && bit[0].equals(paramName)) {
                    return bit[1];
                }
            }
        } catch (Exception e) {
            // 예상치 못한 문자열 형식 오류 시 조용히 기본값 반환
        }

        return defaultValue;
    }

    // getParam을 통해 추출한 값을 정수(int)로 변환하여 반환
    private int getParamAsInt(String command, String paramName, int defaultValue) {
        // 1. 문자열 형태의 값을 먼저 가져옴
        String value = getParam(command, paramName, "");

        // 2. 값이 비어있다면 바로 기본값 반환
        if (value.isEmpty()) {
            return defaultValue;
        }

        try {
            // 3. 숫자로 변환 시도
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            // 4. 숫자가 아닌 문자가 들어온 경우 기본값 반환
            return defaultValue;
        }
    }
}
