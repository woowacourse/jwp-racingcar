package racingcar.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import racingcar.model.Car;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestPropertySource(locations = "/application.properties")
@JdbcTest
public class RecordDaoTest {

    private RecordDao recordDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        recordDao = new RecordDao(jdbcTemplate);

        jdbcTemplate.execute("DROP TABLE record IF EXISTS");
        jdbcTemplate.execute("DROP TABLE game IF EXISTS");

        jdbcTemplate.execute("CREATE TABLE game (\n" +
                "    id int PRIMARY KEY AUTO_INCREMENT,\n" +
                "    trial_count int NOT NULL,\n" +
                "    game_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n" +
                ");");

        jdbcTemplate.execute("DROP TABLE record IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE record\n" +
                "(\n" +
                "    player_name varchar(30),\n" +
                "    game_id     int,\n" +
                "    position    int     NOT NULL,\n" +
                "    is_winner   boolean NOT NULL,\n" +
                "    PRIMARY KEY (player_name, game_id),\n" +
                "    FOREIGN KEY (game_id) REFERENCES game (id)\n" +
                ");");

        List<Object[]> trial_count = Arrays.asList(new String[]{"10"}, new String[]{"20"});
        jdbcTemplate.batchUpdate("INSERT INTO game(trial_count) VALUES (?)", trial_count);

        List<Object[]> records = Stream.of(new Object[]{1, 8, false, "doggy"}, new Object[]{2, 7, true, "power"})
                .collect(Collectors.toList());
        jdbcTemplate.batchUpdate("INSERT INTO record(game_id, position, is_winner, player_name) VALUES (?, ?, ?, ?)", records);
    }

    @Test
    @DisplayName("단일 Record 삽입 테스트")
    void 단일_Record_삽입_테스트() {
        Car doggy = new Car("doggy");

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
    @DisplayName("모든 결과 값을 반환")
    void 모든_결과_값을_반환() {
        assertThatNoException().isThrownBy(
                () -> recordDao.findAll()
        );
    }
}
