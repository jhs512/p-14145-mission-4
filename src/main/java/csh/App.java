package csh;

import csh.controller.WiseSayingController;

import java.util.Scanner;

public class App {
    private final Scanner sc = new Scanner(System.in);
    private final WiseSayingController controller = new WiseSayingController(sc);
    public void run() {
        System.out.println("== 명언 앱 ==");
//        while(true) {
//            String input = sc.nextLine().trim();
//            switch (input) {
//                case "exit" -> {
//                    System.out.println("프로그램 종료");
//                    return;
//                }
//                case "create" -> controller.requestCreate();
//                case "list" -> controller.requestShowList();
//                case "update" -> {
//                    controller.requestUpdate(id);
//                }
//                case "delete" -> {
//                    controller.requestDelete(id);
//                }
//            }
//        }
        while(true) {
            System.out.print("명령) ");
            String input = sc.nextLine().trim();
            Rq rq = new Rq(input);
            String command = rq.getValue("actionName","");

            switch (command) {
                case "exit" -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                case "create" -> controller.requestCreate();
                case "list" -> {
                    String type = rq.getValue("keywordType","");
                    String keyword = rq.getValue("keyword","");
                    int page = rq.getIntValue("page",-1);
                    if(!type.isEmpty() && keyword.isEmpty()) System.out.println("검색을 하시려면 타입과 키워드를 입력하시거나 키워드만 입력해주세요");
                    else controller.requestShowList(type, keyword, page);
                }
                case "edit" -> {
                    int id = rq.getIntValue("id",-1);
                    controller.requestUpdate(id);
                }
                case "delete" -> {
                    int id = rq.getIntValue("id",-1);
                    controller.requestDelete(id);
                }
                default -> System.out.println("잘못된 입력입니다. 다시 입력하세요");
            }
        }
    }
}
