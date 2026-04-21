package com.back.domain.wiseSaying.service;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.ArrayList;
import java.util.List;

//com.back.domain.wiseSaying.service.WiseSayingService.java
//역할 : 순수 비지니스 로직
//스캐너 사용금지, 출력 금지
public class WiseSayingService {
    WiseSayingRepository repo;


    public WiseSayingService()
    {
        repo = new WiseSayingRepository();
    }
    public String print()
    {
        StringBuilder sb = new StringBuilder();
        List<WiseSaying> dummyList = repo.getAll();
        for(int i = dummyList.size()-1; i>=0; i--)
        {
            sb.append(dummyList.get(i));
        }
        return sb.toString();
    }
    public String regist(String query)
    {
        String[] splited = query.split(",");
        String answer = WiseSaying.num +"번 명언이 등록되었습니다.";
        //0이 명언, 1이 작가
        repo.insert(splited[0], splited[1]);
        return answer;
    }

    public String search(String query)
    {
        String answer = null;
        return answer;
    }
    public WiseSaying findById(int id)
    {
        WiseSaying answer = repo.get(id);
        return answer;
    }

    public String delete(String query)
    {
        String answer = "";
        List<String[]> keyValue = SystemController.parsing(query);

        //keywordType=content&keyword=과거
        switch(keyValue.getFirst()[0])
        {
            case "id":
                answer =  repo.delete(Integer.parseInt(keyValue.getFirst()[1]));
                break;
            case "keywordType":
                break;
            case "keyWord":
                break;
        }
        return answer;
    }


    //명령) 수정?id=2
    //명언(기존) : 과거에 집착하지 마라.
    //명언 : 현재와 자신을 사랑하라.
    //작가(기존) : 작자미상
    //작가 : 홍길동
    public void modify(int id, String content, String author)
    {
        repo.modify(id,content,author);
    }
}
