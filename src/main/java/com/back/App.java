package com.back;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;
import com.back.global.rq.Rq;

import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public App() {
        this.scanner = AppContext.scanner;
    }

    public void run() {
        System.out.println("== 명언 앱 ==");
        SystemController systemController = AppContext.systemController;
        WiseSayingController wiseSayingController = AppContext.wiseSayingController;

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();
            Rq rq = new Rq(cmd);

            switch (rq.getActionName()) {
                case "등록" -> wiseSayingController.write();
                case "목록" -> wiseSayingController.list(rq);
                case "삭제" -> wiseSayingController.delete(rq);
                case "수정" -> wiseSayingController.modify(rq);
                case "종료" -> {
                    systemController.exit();
                    return;
                }
            }
        }
    }
}