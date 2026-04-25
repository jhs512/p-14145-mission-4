package com.back.domain.wiseSaying.controller;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.*;

public class WiseSayingController {
    WiseSayingService service = new WiseSayingService();
    Scanner sc;

    public WiseSayingController(Scanner sc) {
        this.sc = sc;
        if (service.getWiseSayingSize() == 0) {
            service.putSampleData();
        }
    }

    public void enroll() {
        System.out.print("명언 : ");
        String saying = sc.nextLine().trim();
        System.out.print("작가 : ");
        String writer = sc.nextLine().trim();
        System.out.println(service.enroll(saying, writer) + "번 명언이 등록되었습니다.");
    }

    public void printList(String cmd) {
        Map<String, String> map = parseListQuery(cmd);

        String keywordType = map.get("keywordType");
        String keyword = map.get("keyword");
        String page = map.get("page");

        System.out.println("----------------------");
        System.out.println("검색타입 : " + keywordType);
        System.out.println("검색어 : " + keyword);
        System.out.println("----------------------");

        List<WiseSaying> list = service.getWisSayingList();
        List<WiseSaying> searchList = new ArrayList<>();

        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for (int i = list.size() - 1; i >= 0; i--) {
            WiseSaying cur = list.get(i);

            boolean matches = true;

            if (keywordType != null && keyword != null) {
                if ("author".equals(keywordType)) {
                    matches = cur.author.contains(keyword);
                } else if ("content".equals(keywordType)) {
                    matches = cur.content.contains(keyword);
                }
            }

            if (matches) {
                searchList.add(cur);
            }
        }

        int printSrtPage = page == null ? 1 : Integer.parseInt(page);
        int totalSize = searchList.size();
        int totalPages = (totalSize + 4) / 5;

        if(printSrtPage < 1 || (totalSize > 0 && printSrtPage > totalPages)) {
            System.out.println("없는 페이지 번호 입니다.");
            return;
        }

        for(int i = (printSrtPage - 1) * 5; i < printSrtPage * 5 && i < totalSize; i++) {
            WiseSaying cur = searchList.get(i);
            System.out.println(cur.num + " / " + cur.author + " / " + cur.content);
        }

        System.out.println("\t\t" + printSrtPage + " / " + totalPages);
    }

    private Map<String, String> parseListQuery(String cmd) {
        Map<String, String> map = new HashMap<>();

        String[] splits = cmd.split("\\?");
        if(splits.length < 2) return map;

        String query = splits[1];
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] tmp = pair.split("=");
            map.put(tmp[0], tmp[1]);
        }
        return map;
    }

    public void delete(String cmd) {
        int id;
        try {
            id = Integer.parseInt(cmd.split("=")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("올바른 형식이 아닙니다. 예: 삭제?id=1");
            return;
        }

        if (id < 1) {
            System.out.println("잘못된 id 값입니다.");
            return;
        }

        boolean isOk = service.delete(id);
        if (isOk) System.out.println(id + "번 명언이 삭제되었습니다.");
        else System.out.println(id + "번 명언은 존재하지 않습니다.");
    }

    public void update(String cmd) {
        try {
            int id = Integer.parseInt(cmd.split("=")[1]);

            if (id < 1) {
                System.out.println(id + "번 명언은 존재하지 않습니다.");
                return;
            }
            WiseSaying bef = service.getWiseSaying(id);
            if (bef == null) {
                System.out.println(id + "번 명언은 존재하지 않습니다.");
                return;
            }

            System.out.println("명언(기존) : " + bef.content);
            System.out.print("명언 : ");
            String saying = sc.nextLine().trim();
            System.out.println("작가(기존) : " + bef.author);
            System.out.print("작가 : ");
            String writer = sc.nextLine().trim();
            service.update(id, saying, writer);

            System.out.println(id + "번 명언이 수정되었습니다.");
        } catch (Exception e) {
            System.out.println("id는 숫자여야 합니다.");
        }
    }
}
