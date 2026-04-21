package com.back.domain.wiseSaying.controller;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

//com.back.domain.wiseSaying.controller.WiseSayingController.java
//역할 : 고객의 명령을 입력받고 적절한 응답을 표현
//이 단계에서는 스캐너 사용가능
//이 단계에서는 출력 사용가능
//역할 : 명언에 관련된 응대
public class WiseSayingController {
    WiseSayingService wservice;
    Scanner sc;

    public WiseSayingController()
    {
        wservice = new WiseSayingService();
        this.sc = new Scanner(System.in);
    }
    public WiseSayingController(String stream)
    {
        wservice = new WiseSayingService();
        this.sc = new Scanner(stream);
    }


    public void run()
    {
        String command;
        String[] splited;
        String params;
        boolean online = true;


        System.out.println("== 명언 앱 ==");
        while(online) {
            System.out.print("명령) ");
            params = "";
            command = sc.nextLine().trim();
            System.out.println();
            if(command.contains("?"))
            {
                splited = command.split("\\?");
                command = splited[0];
                params = splited[1];
            }

            if(command.equals("종료"))online = false;
            else if(command.equals("등록"))
            {
                System.out.println(wservice.regist(regist()));
            }
            else if(command.equals("목록"))
            {
                printList(params);
            }
            else if(command.contains("삭제"))
            {
                System.out.println(wservice.delete(params));
            }
            else if(command.contains("수정"))
            {
                modify(params);
            }
        }
    }

    String regist()
    {
        StringBuilder sb = new StringBuilder();
        System.out.print("명언 : ");
        String context = sc.nextLine().trim();
        System.out.println();
        sb.append(context)
                        .append(",");

        System.out.print("작가 : ");
        String author = sc.nextLine().trim();
        System.out.println();
        sb.append(author);

        return sb.toString();
    }

    void modify(String params)
    {
        StringBuilder answer = new StringBuilder();
        List<String[]> keyValue = SystemController.parsing(params);
        int index = -1;
        for(String[] pair : keyValue)
        {
            if(pair[0].equals("id"))
            {
                index =  Integer.parseInt(pair[1]);
                break;
            }
        }
        WiseSaying ws = wservice.findById(index);

        if(ws == null)
        {
            System.out.println(index+"번 명언은 존재하지 않습니다.");
            return;
        }
        String afterContent = "";
        //content
        System.out.println("명언(기존) : "+ ws.getContent());
        System.out.print("명언 : ");
        afterContent = sc.nextLine().trim();


        //author
        String afterAuthor = "";
        System.out.println("작가(기존) : "+ ws.getAuthor());
        System.out.print("작가 : ");
        afterAuthor = sc.nextLine().trim();

        wservice.modify(ws.getIdx(), afterContent, afterAuthor);
    }

    void printList(String params)
    {
        StringBuilder answer  = new StringBuilder();
        if(!params.equals(""))
        {
            //명령) 목록?keywordType=author&keyword=작자
            //----------------------
            //검색타입 : author
            //검색어 : 작자
            //----------------------
            //번호 / 작가 / 명언
            //----------------------
            //2 / 작자미상 / 과거에 집착하지 마라.
            //1 / 작자미상 / 현재를 사랑하라.
            List<String[]> keyValues = SystemController.parsing(params);
            System.out.println("------------------------");
            String type = "";
            String param = "";
            for(String[] kv : keyValues)
            {
                String key = kv[0];
                String value = kv[1];
                switch (key)
                {
                    case "keywordType":
                        System.out.println("검색타입 : "+value);
                        type = value;
                        break;
                    case "keyword":
                        System.out.println("검색어 : "+value);
                        param = value;
                        break;
                }
            }
            System.out.println("------------------------");
            List<WiseSaying> wList = null;
            switch(type)
            {
                case "content":
                    wList = wservice.contentFindByKeyword(param);
                    break;
                case "author":
                    wList = wservice.authorFindByKeyword(param);
                    break;
            }
            if(wList!=null)
            {
                for(int i=wList.size()-1; i>=0; i--)answer.append(wList.get(i));
            }
        }
        else{
            answer.append(wservice.print());
        }
        System.out.println("번호 / 작가 / 명언");
        System.out.println("------------------------");
        System.out.println(answer.toString());

    }
}
