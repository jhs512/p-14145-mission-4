package org.example;
import java.util.ArrayList;
//데이터의 조회/수정/삭제/생성을 담당

public class WiseSayingRepository {
    ArrayList<WiseSaying> wsal = new ArrayList<>();
    WiseSayingController wc1 = new WiseSayingController();
    SystemController sc1 = new SystemController();

    public void generateWiseSaying(int count, String s1, String s2) { //데이터 생성
        WiseSaying ws1 = new WiseSaying();


        ws1.number = count;
        ws1.wise = s1;
        ws1.writer = s2;

        wsal.add(ws1);

        wc1.generatePrint(ws1.number);
    }

    public void deleteWiseSaying(int md4) { //데이터 삭제

        if (md4 < 1 || md4 > wsal.size()) {
            wc1.deleteWiseSaying(md4,1);
        } else {
            wsal.remove(md4-1);
            wc1.deleteWiseSaying(md4,2);
        }
    }

    public void viewWiseSaying(int input,String key, int page) { //데이터 조회 페이지 번호 있음
       if(input ==1){
           wc1.viewPrint(wsal,"content",key,page);
       }else{
           wc1.viewPrint(wsal,"author",key, page);
       }
    }

    public void viewWiseSaying2(int page) { //데이터 조회 페이지번호 없이
        wc1.viewPrint2(wsal, page);
    }

    public void modifyWiseSaying(int md4) { //데이터 수정
        if (md4 < 1 || md4 > wsal.size()) {
            wc1.deleteWiseSaying(md4,1);
        } else {

            WiseSaying ws = wsal.get(md4 - 1);

            wc1.scanWiseSaying1(ws.wise);
            String str1 = sc1.scanS();
            ws.wise = str1;

            wc1.scanWiseSaying2(ws.writer);
            String str2 = sc1.scanH();
            ws.writer = str2;
        }
    }
}
