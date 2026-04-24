package com.back;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.Scanner;

public class App {

    WiseSayingController wiseSayingController;
    SystemController systemController;

    public App(WiseSayingController wiseSayingController,  SystemController systemController) {
        this.wiseSayingController = wiseSayingController;
        this.systemController = systemController;
    }

    Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("== 명언 앱 ==");
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")) {
                exit = systemController.exit();
            } else if (cmd.equals("등록")) {
                wiseSayingController.register();
            } else if (cmd.equals("목록")) {
                wiseSayingController.list();
            } else if (cmd.startsWith("삭제?id=")) {
                wiseSayingController.delete(cmd);
            } else if (cmd.startsWith("수정?id=")) {
                wiseSayingController.modify(cmd);
            }
        }
    }
}
