package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;

//com.back.domain.wiseSaying.repository.WiseSayingRepository.java
//역할 : 데이터의 조회/수정/삭제/생성을 담당
//스캐너 사용금지, 출력 금지
public class WiseSayingRepository {
    List<WiseSaying> mydb;


    public WiseSayingRepository()
    {
        mydb = new ArrayList<>();
    }


    public WiseSaying get(int id)
    {
        if(!contains(id)) return null;
        int index = indexOf(id);
        return mydb.get(index);
    }
    public List<WiseSaying> getAll()
    {
        return this.mydb;
    }
    WiseSaying search(int index)
    {
        WiseSaying ws;
        if(!contains(index))return null;
        int idx = indexOf(index);
        return mydb.get(idx);
    }

    public void modify(int index, String content, String author)
    {
        WiseSaying ws;
        if(!contains(index))return;
        int idx = indexOf(index);
        ws = mydb.get(idx);
        ws.setContent(content);
        ws.setAuthor(author);
    }
    public void modify(int index, String content)
    {
        WiseSaying ws;
        if(!contains(index))return;
        int idx = indexOf(index);
        ws = mydb.get(idx);
        ws.setContent(content);
    }

    public void insert(String content, String author)
    {
        mydb.add(new WiseSaying(content,author));
    }

    public void insert(String content)
    {
        mydb.add(new WiseSaying(content, "작자미상" + WiseSaying.num));
    }
    public void insert(WiseSaying ws)
    {
        mydb.add(ws);
    }
    public String delete(int index)
    {
        String answer = index+"번 명언은 존재하지 않습니다.";
        if(!contains(index)) return answer;
        int idx = indexOf(index);
        mydb.remove(idx);
        answer = index+"번 명언이 삭제되었습니다.";
        return answer;
    }



    private boolean contains(int idx)
    {
        for(WiseSaying ws : mydb)
        {
            if(ws.getIdx() == idx) return true;
        }
        return false;
    }

    private int indexOf(int index)
    {
        for(int i=0; i<mydb.size(); i++)
        {
            if(mydb.get(i).getIdx() == index) return i;
        }
        return -1;
    }
}
