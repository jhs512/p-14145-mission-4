package util;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;
import com.back.domain.wiseSaying.service.WiseSayingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingServiceTest {
    WiseSayingService service;

    @BeforeEach
    void setUp() {
        WiseSayingRepository repository = new WiseSayingRepository();
        service = new WiseSayingService(repository);
    }

    @Test
    @DisplayName("명언 등록")
    void t1() {
        service.register(1, "현재에 집중해라", "작자미상");

        List<WiseSaying> list = service.getList();
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0).getContent()).isEqualTo("현재에 집중해라");
        assertThat(list.get(0).getAuthor()).isEqualTo("작자미상");
    }

    @Test
    @DisplayName("명언 목록 조회")
    void t2() {
        service.register(1, "현재에 집중해라", "작자미상");
        service.register(2, "과거에 집중해라", "홍길동");

        List<WiseSaying> list = service.getList();
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("명언 삭제 성공")
    void t3() {
        service.register(1, "현재에 집중해라", "작자미상");

        boolean result = service.delete(1);
        assertThat(result).isTrue();
        assertThat(service.getList().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("존재하지 않는 명언 삭제 실패")
    void t4() {
        boolean result = service.delete(999);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("명언 수정")
    void t5() {
        service.register(1, "현재에 집중해라", "작자미상");

        service.modify(1, "과거에 집중해라", "홍길동");

        WiseSaying wiseSaying = service.getById(1);
        assertThat(wiseSaying.getContent()).isEqualTo("과거에 집중해라");
        assertThat(wiseSaying.getAuthor()).isEqualTo("홍길동");
    }

    @Test
    @DisplayName("존재하지 않는 명언 조회시 null 반환")
    void t6() {
        WiseSaying wiseSaying = service.getById(999);
        assertThat(wiseSaying).isNull();
    }
}
