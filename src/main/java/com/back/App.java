package com.back;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public void run() throws IOException {
        Scanner sc = new Scanner(System.in);
        WiseSayingController wiseSayingController = new WiseSayingController(sc);
        SystemController systemController = new SystemController();
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령)");
            String saying = sc.nextLine().trim();

            if (saying.equals("종료"))
                systemController.exit();
            else if (saying.equals("등록")) {
                wiseSayingController.enroll();
            } else if (saying.startsWith("수정")) {
                wiseSayingController.update(saying);
            } else if (saying.startsWith("삭제")) {
                wiseSayingController.delete(saying);
            } else if (saying.startsWith("목록")) {
                wiseSayingController.printList(saying);
            } else {
                systemController.inputError();
            }
        }
    }
}

