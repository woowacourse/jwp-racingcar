package racingcar.repository;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.dto.RecordDto;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@TestPropertySource(locations = "/application.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class RecordDaoTest {

    @Autowired private RecordDao recordDao;
    @Autowired private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void beforeEach() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS record;\n" +
                "DROP TABLE IF EXISTS game;\n" +
                "\n" +
                "CREATE TABLE game\n" +
                "(\n" +
                "    id          int PRIMARY KEY AUTO_INCREMENT      NOT NULL,\n" +
                "    trial_count int                                 NOT NULL,\n" +
                "    game_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE record\n" +
                "(\n" +
                "    game_id     int         NOT NULL,\n" +
                "    position    int         NOT NULL,\n" +
                "    is_winner   boolean     NOT NULL,\n" +
                "    player_name varchar(30) NOT NULL,\n" +
                "    PRIMARY KEY (game_id, player_name),\n" +
                "    FOREIGN KEY (game_id) REFERENCES game (id)\n" +
                ");\n");

        initGameTableData();
        initRecordTableData();
    }

    private void initGameTableData() {
        List<Object[]> trialCounts = Arrays.asList(new String[]{"10"}, new String[]{"20"});
        jdbcTemplate.batchUpdate("INSERT INTO game(trial_count) VALUES (?)", trialCounts);
    }

    private void initRecordTableData() {
        List<Object[]> records = Stream.of(
                new Object[]{1, 5, false, "doggy"},
                new Object[]{1, 7, true, "power"},
                new Object[]{2, 7, true, "power"},
                new Object[]{2, 7, true, "doggy"},
                new Object[]{2, 3, false, "bree"}
        ).collect(toList());
        jdbcTemplate.batchUpdate("INSERT INTO record(game_id, position, is_winner, player_name) VALUES (?, ?, ?, ?)", records);
    }

    @Test
    @DisplayName("단일 행 정상 insert 테스트")
    void 단일_행_정상_insert_테스트() {
        Car doggy = new Car("5th");

        assertThatNoException().isThrownBy(
                () -> recordDao.insert(2, true, doggy)
        );
    }

    @Test
    @DisplayName("존재하지 않는 gameId가 들어온 경우 예외가 발생한다")
    void 존재하지_않는_gameId가_들어온_경우_예외가_발생한다() {
        Car doggy = new Car("doggy");

        assertThatThrownBy(
                () -> recordDao.insert(3, false, doggy)
        );
    }

    @Test
    @DisplayName("이미 존재하는 game_id와 player_name이 동시에 들어온 경우 예외가 발생한다")
    void 이미_존재하는_game_id와_player_name이_동시에_들어온_경우_예외가_발생한다() {
        Car doggy = new Car("doggy");

        assertThatThrownBy(() -> recordDao.insert(1, false, doggy));
    }

    @Test
    @DisplayName("gameId로 모든 데이터를 조회한다")
    void gameId로_모든_데이터를_조회한다() {
        long gameId = 1L;

        List<RecordDto> result = recordDao.findAllByGameId(gameId);

        assertThat(result).isEqualTo(List.of(
                new RecordDto(1, "doggy", 5, false),
                new RecordDto(1, "power", 7, true)
        ));
    }
}
