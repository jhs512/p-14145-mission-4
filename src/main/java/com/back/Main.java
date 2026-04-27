package com.back;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WiseSayingRepository repository = new WiseSayingRepository();
        WiseSayingService service = new WiseSayingService(repository);
        WiseSayingController wiseSayingController = new WiseSayingController(service);
        SystemController systemController = new SystemController();

        new App(wiseSayingController, systemController).run();
    }
}