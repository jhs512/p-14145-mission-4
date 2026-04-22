package com.back;
//com.back.App
//역할 : 사용자 입력을 받고 그것이 WiseSayingController 에게 넘거야 하는지 판단해서 맞으면 넘김(넘김의 의미 : 메서드 호출(인자와 함께))
//이 단계에서는 스캐너 사용가능
//이 단계에서는 출력 사용가능

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;
import com.back.domain.wiseSaying.service.WiseSayingService;

//컨트롤러 => 서비스 => 리포지터리 => 파일DB
public class App {
    WiseSayingController controller;
    SystemController systemController;
    App() {
        controller = new WiseSayingController();
        //service = new WiseSayingService();
        systemController = new SystemController();
    }
    App(String stream) {
        controller = new WiseSayingController(stream);
        //service = new WiseSayingService();
        systemController = new SystemController();
    }
    public void run() {
        controller.run();
    }
}
