package com.back.domain.wiseSaying.controller;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    WiseSayingService wiseSayingService;

    public WiseSayingController(WiseSayingService wiseSayingService) {
        this.wiseSayingService = wiseSayingService;
    }

    private int count = 0;

    Scanner scanner = new Scanner(System.in);

    public void register() {
        System.out.print("명언 : ");
        String wish = scanner.nextLine().trim();

        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        wiseSayingService.register(++count, wish, author);
        System.out.println(count + "번 명언이 등록되었습니다.");
    }

    public void list() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<WiseSaying> wiseList = wiseSayingService.getList();
        for (int i = wiseList.size(); i > 0; i--) {
            System.out.println(wiseList.get(i - 1).getId() + " / " + wiseList.get(i - 1).getAuthor() + " / " + wiseList.get(i - 1).getContent());
        }
    }

    public void delete(String input) {
        String deleteId = input.substring("삭제?id=".length());
        int number = Integer.parseInt(deleteId);

        boolean result = wiseSayingService.delete(number);
        if (result) {
            System.out.println(number + "번 명언이 삭제되었습니다.");
        } else {
            System.out.println(number + "번 명언은 존재하지 않습니다.");
        }
    }

    public void modify(String input) {
        String modifyId = input.substring("수정?id=".length());
        int number = Integer.parseInt(modifyId);

        WiseSaying wiseSaying = wiseSayingService.getById(number);
        if (wiseSaying == null) {
            System.out.println(number + "번 명언은 존재하지 않습니다.");
        } else {
            System.out.println("명언(기존) : " + wiseSaying.getContent());
            System.out.print("명언 : ");
            String wishNew = scanner.nextLine();

            System.out.println("작가(기존) : " + wiseSaying.getAuthor());
            System.out.print("작가 : ");
            String authorNew = scanner.nextLine();

            wiseSayingService.modify(number, wishNew, authorNew);
        }
    }

    public void search (String input) {
        String keywordType = input.substring("목록?keywordType=".length(), input.indexOf("&"));
        String keyword = input.substring(input.indexOf("keyword=") + "keyword=".length());

        System.out.println("----------------------");
        System.out.println("검색타입 : " + keywordType);
        System.out.println("검색어 : " + keyword);
        System.out.println("----------------------");
        System.out.println("번호 / 작가 / 명언");

        List<WiseSaying> wiseSayings = wiseSayingService.findByKeyword(keywordType, keyword);
        for (int i = wiseSayings.size(); i > 0; i--) {
            System.out.println(wiseSayings.get(i - 1).getId() + " / " + wiseSayings.get(i - 1).getAuthor() + " / " + wiseSayings.get(i - 1).getContent());
        }
    }


}
