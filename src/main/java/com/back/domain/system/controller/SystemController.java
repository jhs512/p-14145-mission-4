package com.back.domain.system.controller;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//com.back.domain.system.controller.SystemController.java
//역할 : 고객의 명령을 입력받고 적절한 응답을 표현
//이 단계에서는 스캐너 사용가능
//이 단계에서는 출력 사용가능
//역할 : 시스템에 관련된 응대
public class SystemController {

    public SystemController(){}

    private static final PrintStream ORIGINAL_OUT = System.out;
    private static PrintStream CURRENT_OUT;

    public static Scanner genScanner(String input) {
        return new Scanner(input);
    }

    public static ByteArrayOutputStream setOutToByteArray() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        CURRENT_OUT = new PrintStream(byteArrayOutputStream, true);
        System.setOut(CURRENT_OUT);

        return byteArrayOutputStream;
    }

    public static void clearSetOutToByteArray() {
        System.setOut(ORIGINAL_OUT);
        CURRENT_OUT.close();
        CURRENT_OUT = null;
    }
    public static List<String[]> parsing(String query)
    {
        String answer = "";
        String[] splited_and = query.split("&");
        List<String[]> keyValue = new ArrayList<>();

        for(String str : splited_and)
        {
            String[] splited = str.split("=");
            keyValue.add(splited);
        }

        return keyValue;
    }

}
