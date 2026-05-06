package com.back.domain.wiseSaying.controller;

import com.back.AppTestRunner;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingControllerTest {
    @Test
    @DisplayName("등록")
    void t3() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(out)
                .contains("명언 :")
                .contains("작가 :")
                .contains("11번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("종료")
    void t4() {
        final String out = AppTestRunner.run("""
                종료
                """);

        assertThat(out)
                .contains("== 명언 앱 ==")
                .contains("명령)")
                .contains("프로그램이 종료합니다.");
    }

    @Test
    @DisplayName("등록 후 목록")
    void t5() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                목록
                """);

        assertThat(out)
                .contains("번호 / 작가 / 명언")
                .contains("11 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("목록은 최신 명언부터 출력")
    void t6() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                """);

        assertThat(out.indexOf("12 / 작자미상 / 과거에 집착하지 마라."))
                .isLessThan(out.indexOf("11 / 작자미상 / 현재를 사랑하라."));
    }

    @Test
    @DisplayName("삭제")
    void t7() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                삭제?id=11
                """);

        assertThat(out).contains("11번 명언이 삭제되었습니다.");
    }

    @Test
    @DisplayName("삭제 후 목록에서는 삭제된 명언 제외")
    void t8() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                삭제?id=11
                목록
                """);

        String listOut = out.substring(out.indexOf("번호 / 작가 / 명언"));

        assertThat(listOut)
                .contains("번호 / 작가 / 명언")
                .doesNotContain("11 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("존재하지 않는 명언 삭제")
    void t9() {
        final String out = AppTestRunner.run("""
                삭제?id=999
                """);

        assertThat(out).contains("999번 명언은 존재하지 않습니다.");
    }

    @Test
    @DisplayName("삭제할 id가 없는 경우")
    void t10() {
        final String out = AppTestRunner.run("""
                삭제
                """);

        assertThat(out).contains("id를 숫자로 입력해주세요.");
    }

    @Test
    @DisplayName("삭제할 id가 숫자가 아닌 경우")
    void t11() {
        final String out = AppTestRunner.run("""
                삭제?id=abc
                """);

        assertThat(out).contains("id를 숫자로 입력해주세요.");
    }

    @Test
    @DisplayName("수정")
    void t12() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                수정?id=11
                현재와 자신을 사랑하라.
                홍길동
                목록
                """);

        assertThat(out)
                .contains("명언(기존) : 현재를 사랑하라.")
                .contains("작가(기존) : 작자미상")
                .contains("11 / 홍길동 / 현재와 자신을 사랑하라.");
    }

    @Test
    @DisplayName("수정해도 명언 번호는 유지")
    void t13() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                수정?id=11
                현재와 자신을 사랑하라.
                홍길동
                목록
                """);

        assertThat(out)
                .contains("11 / 홍길동 / 현재와 자신을 사랑하라.")
                .doesNotContain("12 / 홍길동 / 현재와 자신을 사랑하라.");
    }

    @Test
    @DisplayName("존재하지 않는 명언 수정")
    void t14() {
        final String out = AppTestRunner.run("""
                수정?id=999
                """);

        assertThat(out).contains("999번 명언은 존재하지 않습니다.");
    }

    @Test
    @DisplayName("수정할 id가 없는 경우")
    void t15() {
        final String out = AppTestRunner.run("""
                수정
                """);

        assertThat(out).contains("id를 입력해주세요.");
    }

    @Test
    @DisplayName("수정할 id가 숫자가 아닌 경우")
    void t16() {
        final String out = AppTestRunner.run("""
                수정?id=abc
                """);

        assertThat(out).contains("id를 입력해주세요.");
    }

    @Test
    @DisplayName("앱 시작 시 샘플 데이터 10개 생성")
    void t17() {
        final String out = AppTestRunner.run("""
                목록
                """);

        assertThat(out)
                .contains("10 / 작자미상 10 / 명언 10")
                .contains("6 / 작자미상 6 / 명언 6")
                .doesNotContain("5 / 작자미상 5 / 명언 5");
    }

    @Test
    @DisplayName("여러 개 등록 시 번호 증가")
    void t18() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                등록
                미래를 두려워하지 마라.
                작자미상
                """);

        assertThat(out)
                .contains("11번 명언이 등록되었습니다.")
                .contains("12번 명언이 등록되었습니다.")
                .contains("13번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("삭제 후 새 명언 등록 시 번호는 증가")
    void t19() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                삭제?id=11
                등록
                다시 시작하라.
                작자미상
                목록
                """);

        assertThat(out)
                .contains("12번 명언이 등록되었습니다.")
                .contains("12 / 작자미상 / 다시 시작하라.")
                .doesNotContain("11 / 작자미상 / 다시 시작하라.");
    }

    @Test
    @DisplayName("수정 후 삭제")
    void t20() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                수정?id=11
                오늘을 사랑하라.
                이순신
                삭제?id=11
                목록
                """);

        String listOut = out.substring(out.indexOf("번호 / 작가 / 명언"));

        assertThat(out).contains("11번 명언이 삭제되었습니다.");
        assertThat(listOut).doesNotContain("11 / 이순신 / 오늘을 사랑하라.");
    }

    @Test
    @DisplayName("목록 검색")
    void t21() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록?keywordType=content&keyword=과거
                목록?keywordType=author&keyword=작자미상 1
                """);

        assertThat(out)
                .contains("검색타입 : content")
                .contains("검색어 : 과거")
                .contains("검색타입 : author")
                .contains("검색어 : 작자미상 1");

        String contentSearchOut = out.substring(
                out.indexOf("검색타입 : content"),
                out.indexOf("검색타입 : author")
        );

        String authorSearchOut = out.substring(out.indexOf("검색타입 : author"));

        assertThat(contentSearchOut)
                .contains("12 / 작자미상 / 과거에 집착하지 마라.")
                .doesNotContain("11 / 작자미상 / 현재를 사랑하라.");

        assertThat(authorSearchOut)
                .contains("10 / 작자미상 10 / 명언 10")
                .contains("1 / 작자미상 1 / 명언 1");

        assertThat(authorSearchOut.indexOf("10 / 작자미상 10 / 명언 10"))
                .isLessThan(authorSearchOut.indexOf("1 / 작자미상 1 / 명언 1"));
    }

    @Test
    @DisplayName("목록 1페이지")
    void t22() {
        final String out = AppTestRunner.run("""
                목록
                """);

        assertThat(out)
                .contains("10 / 작자미상 10 / 명언 10")
                .contains("9 / 작자미상 9 / 명언 9")
                .contains("8 / 작자미상 8 / 명언 8")
                .contains("7 / 작자미상 7 / 명언 7")
                .contains("6 / 작자미상 6 / 명언 6")
                .contains("페이지 : [1] / 2")
                .doesNotContain("5 / 작자미상 5 / 명언 5");
    }

    @Test
    @DisplayName("목록 2페이지")
    void t23() {
        final String out = AppTestRunner.run("""
                목록?page=2
                """);

        assertThat(out)
                .contains("5 / 작자미상 5 / 명언 5")
                .contains("4 / 작자미상 4 / 명언 4")
                .contains("3 / 작자미상 3 / 명언 3")
                .contains("2 / 작자미상 2 / 명언 2")
                .contains("1 / 작자미상 1 / 명언 1")
                .contains("페이지 : 1 / [2]")
                .doesNotContain("6 / 작자미상 6 / 명언 6");
    }

    @Test
    @DisplayName("검색어를 적용한 페이징")
    void t24() {
        final String out = AppTestRunner.run("""
                목록?keywordType=author&keyword=작자미상&page=2
                """);

        assertThat(out)
                .contains("검색타입 : author")
                .contains("검색어 : 작자미상")
                .contains("5 / 작자미상 5 / 명언 5")
                .contains("1 / 작자미상 1 / 명언 1")
                .contains("페이지 : 1 / [2]")
                .doesNotContain("6 / 작자미상 6 / 명언 6");
    }
}
