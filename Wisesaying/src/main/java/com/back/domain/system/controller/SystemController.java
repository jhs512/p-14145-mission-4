package com.back.domain.system.controller;

import com.back.domain.wiseSaying.controller.WiseSayingController;

import java.util.Arrays;
import java.util.Scanner;

public class SystemController {
    WiseSayingController wiseSayingController;

    public SystemController(Scanner sc) {
        wiseSayingController = new WiseSayingController(sc);
    }

    public void getCommand(String userCommand) {
        switch(userCommand) {
            case "등록":
                wiseSayingController.createWiseSaying();
                break;
            case "목록":
                wiseSayingController.getWiseSaying();
                break;
            case "종료":
                System.out.println("프로그램을 종료합니다.");
            default:
                String[] splitCommand = userCommand.split("\\?id=");
                switch (splitCommand[0]) {
                    case "삭제":
                        wiseSayingController.deleteWiseSaying(Integer.parseInt(splitCommand[1]));
                        break;
                    case "수정":
                        wiseSayingController.updateWiseSaying(Integer.parseInt(splitCommand[1]));
                        break;
                    default:
                        userCommand = userCommand.replace("목록?keywordType=", "")
                                .replace("keyword=", "");
                        String[] splitSearch = userCommand.split("&");
                        wiseSayingController.searchWiseSaying(splitSearch[0], splitSearch[1]);
                }
        }
    }
}
