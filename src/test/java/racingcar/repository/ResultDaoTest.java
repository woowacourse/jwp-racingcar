package racingcar.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import racingcar.entity.Result;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class ResultDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private ResultDao resultDao;

    @BeforeEach
    void init() {
        resultDao = new ResultDao(jdbcTemplate);

        resultDao.insert(1, "test1");
        resultDao.insert(2, "test2");
        resultDao.insert(3, "test3");
        resultDao.insert(4, "test4");
    }

    @AfterEach
    void reset() {
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE");
        jdbcTemplate.update("TRUNCATE TABLE results");
        jdbcTemplate.execute("ALTER TABLE results ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE");

//        jdbcTemplate.update("DELETE FROM results");
//        jdbcTemplate.execute("ALTER TABLE results ALTER COLUMN id RESTART WITH 1");
    }

    @Test
    @DisplayName("결과 추가 시 id가 반환되는지 확인")
    void insert() {
        long id = resultDao.insert(8, "roy");

        assertThat(id).isEqualTo(5);
    }

    @Test
    @DisplayName("저장되어 있는 결과 id 리스트가 제대로 반환되는지 확인")
    void findAllId() {
        List<Result> results = resultDao.findAll();

        List<Long> ids = results.stream()
                                .map(Result::getId)
                                .collect(Collectors.toList());
        List<String> winners = results.stream()
                                      .map(Result::getWinners)
                                      .collect(Collectors.toList());
        List<Integer> trialCounts = results.stream()
                                           .map(Result::getTrialCount)
                                           .collect(Collectors.toList());

        assertAll(
                () -> assertThat(ids).containsExactly(1L, 2L, 3L, 4L),
                () -> assertThat(winners).containsExactly("test1", "test2", "test3", "test4"),
                () -> assertThat(trialCounts).containsExactly(1, 2, 3, 4)
        );
    }
}
