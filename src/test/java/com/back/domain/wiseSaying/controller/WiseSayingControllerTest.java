package com.back.domain.wiseSaying.controller;

import com.back.AppTestRunner;
//import com.back.global.app.AppConfig;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;
public class WiseSayingControllerTest {
    @Test
    @DisplayName("등록-목록")
    void t3() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                목록
                """);

        assertThat(out)
                .contains("/ 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("삭제")
    void t4() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                삭제?id=11
                목록
                """);

        assertThat(out)
                .doesNotContain("현재를 사랑하라.");
    }
    @Test
    @DisplayName("수정")
    void t5() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                수정?id=11
                명언입니다.
                작가입니다.
                목록
                """);

        assertThat(out)
                .contains("1번 명언이 등록되었습니다.")
                .contains("명언(기존) : 현재를 사랑하라.")
                .contains("작가(기존) : 작자미상")
                .contains("11 / 작가입니다. / 명언입니다.");
    }
    @Test
    @DisplayName("중간 삭제")
    void t6() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                가나다라마바사
                작자
                등록
                13번 명언입니다.
                13번 작가입니다.
                삭제?id=12
                목록
                """);

        assertThat(out)
                .doesNotContain("12 / 가나다라마바사 / 작자");
    }
    @Test
    @DisplayName("중간 수정")
    void t7() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                가나다라마바사
                작자
                등록
                13번 명언입니다.
                13번 작가입니다.
                수정?id=12
                수정한 명언입니다.
                수정한 작가입니다.
                목록
                """);

        assertThat(out)
                .contains("12 / 수정한 작가입니다. / 수정한 명언입니다.");
    }

    @Test
    @DisplayName("등록 3번")
    void t8() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                가나다라마바사
                작자
                등록
                13번 명언입니다.
                13번 작가입니다.
                목록
                """);

        assertThat(out)
                .contains("13 / 13번 작가입니다. / 13번 명언입니다.");
    }

    @Test
    @DisplayName("목록 조건1")
    void t9() {
        //명령) 목록?keywordType=content&keyword=과거
        //----------------------
        //검색타입 : content
        //검색어 : 과거
        //----------------------
        //번호 / 작가 / 명언
        //----------------------
        //2 / 작자미상 / 과거에 집착하지 마라.
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                등록
                13번 명언입니다.
                13번 작가입니다.
                목록?keywordType=content&keyword=과거
                """);

        assertThat(out)
                .contains("검색타입 : content")
                .contains("검색어 : 과거")
                .doesNotContain("13 / 13번 작가입니다. / 13번 명언입니다.")
                .contains("12 / 작자미상 / 과거에 집착하지 마라.");
    }


    @Test
    @DisplayName("목록 조건2")
    void t10() {
        //명령) 목록?keywordType=author&keyword=작자
        //----------------------
        //검색타입 : author
        //검색어 : 작자
        //----------------------
        //번호 / 작가 / 명언
        //----------------------
        //2 / 작자미상 / 과거에 집착하지 마라.
        //1 / 작자미상 / 현재를 사랑하라.
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                등록
                3번 명언입니다.
                3번 작가입니다.
                목록?keywordType=author&keyword=작자
                """);
        //System.out.println(out);
        assertThat(out)
                .contains("검색타입 : author")
                .contains("검색어 : 작자")
                .doesNotContain("13 / 13번 작가입니다. / 13번 명언입니다.")
                .contains("12 / 작자미상 / 과거에 집착하지 마라.")
                .contains("11 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("목록 page")
    void t11() {
        final String out = AppTestRunner.run("""
                목록
                """);
        //System.out.println(out);
        assertThat(out)
                .contains("------------------------\n" +
                        "10 / 작자미상 10 / 명언 10\n" +
                        "9 / 작자미상 9 / 명언 9\n" +
                        "8 / 작자미상 8 / 명언 8\n" +
                        "7 / 작자미상 7 / 명언 7\n" +
                        "6 / 작자미상 6 / 명언 6\n" +
                        "------------------------")
                .contains("페이지 : [1] / 2");
    }
    @Test
    @DisplayName("목록 page 조건1")
    void t12() {
        final String out = AppTestRunner.run("""
                목록?page=2
                """);
        //System.out.println(out);
        assertThat(out)
                .contains("페이지 : 1 / [2]");
    }
    // ...
}