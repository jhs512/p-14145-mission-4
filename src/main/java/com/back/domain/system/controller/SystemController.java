package com.back.domain.system.controller;

public class SystemController {
    public void exit(){
        System.exit(1);
    }

    public void  inputError(){
        System.out.println("알 수 없는 명령어 입니다.");
    }
}
