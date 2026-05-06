package com.back.domain.wiseSaying.controller;

import com.back.AppContext;
import com.back.Rq;
import com.back.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;
import java.util.List;
import java.util.Scanner;


public class WiseSayingController {

    private static final int PAGE_SIZE = 5;
    private final Scanner scanner;
    private final WiseSayingService wiseSayingService;
    public WiseSayingController(){
        this.scanner = AppContext.scanner;
        this.wiseSayingService = new WiseSayingService();
    }

    public void actionList(Rq rq) {
        String keywordType = rq.getParam("keywordType", "");
        String keyword = rq.getParam("keyword", "");

        if (!keywordType.isEmpty() && !keyword.isEmpty()) {
            System.out.println("----------------------");
            System.out.println("검색타입 : " + keywordType);
            System.out.println("검색어 : " + keyword);
            System.out.println("----------------------");
        }

        int page = rq.getParamAsInt("page", 1);
        if (page < 1) page = 1;

        List<WiseSaying> forListWiseSayings = wiseSayingService.findForList(keywordType, keyword);
        int totalItems = forListWiseSayings.size();
        int totalPages = Math.max(1, (int) Math.ceil((double) totalItems / PAGE_SIZE));
        if (page > totalPages) page = totalPages;
        int fromIndex = Math.min((page - 1) * PAGE_SIZE, totalItems);
        int toIndex = Math.min(fromIndex + PAGE_SIZE, totalItems);
        List<WiseSaying> pagedWiseSayings = forListWiseSayings.subList(fromIndex, toIndex);

        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for (WiseSaying wiseSaying : pagedWiseSayings) {
            System.out.printf("%d / %s / %s\n"
                    , wiseSaying.getId(), wiseSaying.getAuthor()
                    , wiseSaying.getContent());
        }

        System.out.println("----------------------");
        System.out.println("페이지 : " + genPageMenu(page, totalPages));
    }

    private String genPageMenu(int currentPage, int totalPages) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= totalPages; i++) {
            if (i > 1) sb.append(" / ");

            if (i == currentPage) sb.append("[").append(i).append("]");
            else sb.append(i);
        }

        return sb.toString();
    }

    public void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        WiseSaying wiseSaying = wiseSayingService.write(content, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));
    }

    public void actionDelete(Rq rq) {
        int id = rq.getParamAsInt("id" , -1);
        if (id==-1){
            System.out.println("id를 숫자로 입력해주세요.");
            return ;
        }
        boolean  deleted = wiseSayingService.delete(id);
        if (!deleted){
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }
        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    public void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id",-1);

        if (id == -1) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = wiseSayingService.findById(id);

        if (wiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.printf("명언(기존) : %s\n", wiseSaying.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.printf("작가(기존) : %s\n", wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        wiseSayingService.modify(wiseSaying, content, author);
    }
}
