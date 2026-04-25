package com.back.domain.wiseSaying.controller;

import com.back.AppTestRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingControllerTest {

    // 앱 시작 시 샘플 10개 자동 생성(num 1~10), 다음 등록은 11번부터

    @Test
    @DisplayName("등록")
    void t1() {
        String out = AppTestRunner.run("""
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
    @DisplayName("삭제 - 존재하는 번호")
    void t2() {
        String out = AppTestRunner.run("""
                삭제?id=1
                """);

        assertThat(out).contains("1번 명언이 삭제되었습니다.");
    }

    @Test
    @DisplayName("삭제 - 존재하지 않는 번호")
    void t3() {
        String out = AppTestRunner.run("""
                삭제?id=99
                """);

        assertThat(out).contains("99번 명언은 존재하지 않습니다.");
    }

    @Test
    @DisplayName("수정 - 존재하는 번호")
    void t4() {
        String out = AppTestRunner.run("""
                수정?id=1
                새로운 명언
                새 작가
                """);

        assertThat(out)
                .contains("명언(기존) : 명언 1")
                .contains("작가(기존) : 작자미상 1")
                .contains("1번 명언이 수정되었습니다.");
    }

    @Test
    @DisplayName("수정 - 존재하지 않는 번호")
    void t5() {
        String out = AppTestRunner.run("""
                수정?id=99
                """);

        assertThat(out).contains("99번 명언은 존재하지 않습니다.");
    }

    @Test
    @DisplayName("목록 - 1페이지(기본), 최신순")
    void t6() {
        String out = AppTestRunner.run("""
                목록
                """);

        assertThat(out)
                .contains("10 / 작자미상 10 / 명언 10")
                .contains("9 / 작자미상 9 / 명언 9")
                .contains("8 / 작자미상 8 / 명언 8")
                .contains("7 / 작자미상 7 / 명언 7")
                .contains("6 / 작자미상 6 / 명언 6")
                .doesNotContain("5 / 작자미상 5 / 명언 5")
                .contains("1 / 2");
    }

    @Test
    @DisplayName("목록 - 2페이지")
    void t7() {
        String out = AppTestRunner.run("""
                목록?page=2
                """);

        assertThat(out)
                .contains("5 / 작자미상 5 / 명언 5")
                .contains("4 / 작자미상 4 / 명언 4")
                .contains("3 / 작자미상 3 / 명언 3")
                .contains("2 / 작자미상 2 / 명언 2")
                .contains("1 / 작자미상 1 / 명언 1")
                .doesNotContain("6 / 작자미상 6 / 명언 6")
                .contains("2 / 2");
    }

    @Test
    @DisplayName("목록 - 없는 페이지 번호")
    void t8() {
        String out = AppTestRunner.run("""
                목록?page=99
                """);

        assertThat(out).contains("없는 페이지 번호 입니다.");
    }

    @Test
    @DisplayName("목록 - 명언 내용으로 검색")
    void t9() {
        String out = AppTestRunner.run("""
                목록?keywordType=wisesaying&keyword=명언 5
                """);

        assertThat(out)
                .contains("5 / 작자미상 5 / 명언 5")
                .doesNotContain("6 / 작자미상 6 / 명언 6")
                .contains("1 / 1");
    }

    @Test
    @DisplayName("목록 - 작가로 검색")
    void t10() {
        String out = AppTestRunner.run("""
                목록?keywordType=author&keyword=작자미상 3
                """);

        assertThat(out)
                .contains("3 / 작자미상 3 / 명언 3")
                .doesNotContain("4 / 작자미상 4 / 명언 4")
                .contains("1 / 1");
    }
}
