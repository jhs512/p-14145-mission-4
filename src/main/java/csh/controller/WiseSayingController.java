package csh.controller;

import csh.entity.WiseSaying;
import csh.service.WiseSayingService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class WiseSayingController {
    private final WiseSayingService service = new WiseSayingService();
    private final Scanner sc;
    private static final int PAGE_LIMIT = 5;
    public WiseSayingController(Scanner scanner) {
        sc = scanner;
    }

    public void requestCreate() {
        System.out.print("명언 : ");
        String content = sc.nextLine().trim();
        System.out.print("저자 : ");
        String author = sc.nextLine().trim();
        int id = service.create(content, author);
        System.out.println("%d번 명언이 등록되었습니다.".formatted(id));
    }

    public void requestShowList(String type, String keyword, int page) {
        page = Math.abs(page);
        boolean typeFlag = !type.isEmpty();
        boolean keywordFlag = !keyword.isEmpty();

        if(typeFlag || keywordFlag) System.out.println("----------------------");
        if(typeFlag) System.out.println("검색타입 : %s".formatted(type));
        if(keywordFlag) System.out.println("검색어 : %s".formatted(keyword));
        if(typeFlag || keywordFlag) System.out.println("----------------------");

        Map<Integer, WiseSaying> map = service.getList(type,keyword);

        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        WiseSaying[] wiseSayings = map.values().toArray(new WiseSaying[0]);
        int length = wiseSayings.length;
        int start = (page-1) * PAGE_LIMIT;
        int end = page * PAGE_LIMIT ;
        if(end > length) end = length;

        for(int i=start; i<end; i++) {
            System.out.println(wiseSayings[i].getId() + " / " + wiseSayings[i].getAuthor() + " / " + wiseSayings[i].getContent());
        }

        int size = 0;
        if(length % PAGE_LIMIT != 0 || length == 0) size = length / PAGE_LIMIT + 1;
        else size = length / 5;
        System.out.println("페이지 : [%d] / [%d]".formatted(page, size));
    }

    public void requestUpdate(int id) {
        WiseSaying ws = service.getWiseSayingById(id);
        if(ws == null) {
            System.out.println("해당 명언은 존재 하지않습니다.");
            return;
        }
        System.out.println("명언(기존) : %s".formatted(ws.getContent()));
        System.out.print("명언 : ");
        String content = sc.nextLine().trim();
        System.out.println("저자(기존) : %s".formatted(ws.getAuthor()));
        System.out.print("저자 : ");
        String author = sc.nextLine().trim();
        service.update(id, content, author);
        System.out.println("%d번 명언이 수정되었습니다.".formatted(id));
    }

    public void requestDelete(int id) {
        boolean flag = service.deleteWiseSayingById(id);
        if(flag) System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
        else System.out.println("해당 명언은 존재하지않습니다.");
    }
}
