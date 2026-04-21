package com.back.domain.wiseSaying.controller;

import com.back.AppTestRunner;
//import com.back.global.app.AppConfig;
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
                .contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("목록")
    void t4() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                목록
                """);

        assertThat(out)
                .contains("명언 :")
                .contains("작가 :")
                .contains("1번 명언이 등록되었습니다.")
                .contains("현재를 사랑하라.")
                .contains("작자미상");
    }
    @Test
    @DisplayName("삭제")
    void t5() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                삭제?id=1
                목록
                """);

        assertThat(out)
                .contains("1번 명언이 등록되었습니다.")
                .doesNotContain("현재를 사랑하라.");
    }
    @Test
    @DisplayName("수정")
    void t6() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                수정?id=1
                명언입니다.
                작가입니다.
                목록
                """);

        assertThat(out)
                .contains("1번 명언이 등록되었습니다.")
                .contains("명언(기존) : 현재를 사랑하라.")
                .contains("작가(기존) : 작자미상")
                .contains("1 / 작가입니다. / 명언입니다.");
    }
    @Test
    @DisplayName("중간 삭제")
    void t7() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                가나다라마바사
                작자
                등록
                3번 명언입니다.
                3번 작가입니다.
                삭제?id=2
                목록
                """);

        assertThat(out)
                .contains("1번 명언이 등록되었습니다.")
                .contains("2번 명언이 등록되었습니다.")
                .doesNotContain("2 / 가나다라마바사 / 작자");
    }
    @Test
    @DisplayName("중간 수정")
    void t8() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                가나다라마바사
                작자
                등록
                3번 명언입니다.
                3번 작가입니다.
                수정?id=2
                수정한 명언입니다.
                수정한 작가입니다.
                목록
                """);

        assertThat(out)
                .contains("1번 명언이 등록되었습니다.")
                .contains("2 / 수정한 작가입니다. / 수정한 명언입니다.");
    }

    @Test
    @DisplayName("등록 3번")
    void t9() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                가나다라마바사
                작자
                등록
                3번 명언입니다.
                3번 작가입니다.
                목록
                """);

        assertThat(out)
                .contains("1번 명언이 등록되었습니다.")
                .contains("2번 명언이 등록되었습니다.")
                .contains("3번 명언이 등록되었습니다.")
                .contains("3 / 3번 작가입니다. / 3번 명언입니다.");
    }
    // ...
}