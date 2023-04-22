package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import racingcar.domain.Car;
import racingcar.entity.PlayerInfoEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("transactional")
class JdbcPlayerInfoDAOTest {

    private final int playResultId = 1;
    private JdbcPlayerInfoDAO jdbcPlayerInfoDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcPlayerInfoDAO = new JdbcPlayerInfoDAO(jdbcTemplate);
        final String sql = "insert into play_result (id, count, winners) values (?, ?, ?)";
        jdbcTemplate.update(sql, playResultId, 2, "Test");
    }

    @Test
    void insert_를_통해_DB_에_유저_정보를_저장한다() {
        //given
        jdbcPlayerInfoDAO.insert(playResultId, List.of(new Car("car", 1)));

        //when
        final List<PlayerInfoEntity> playerByPlayResultId = jdbcPlayerInfoDAO.findPlayerByPlayResultId(playResultId);
        final PlayerInfoEntity insertedPlayer = playerByPlayResultId.get(0);

        //then
        assertAll(
                () -> assertThat(insertedPlayer.getName()).isEqualTo("car"),
                () -> assertThat(insertedPlayer.getPosition()).isEqualTo(1)
        );
    }

    @Test
    void insert_파라미터로_전달된_id_가_Play_Result에_없다면_예외가_발생한다() {
        //given
        int notExitingResultId = 10;
        assertThatThrownBy(() -> jdbcTemplate.queryForObject("select id from play_result where id = ? ", Integer.class, notExitingResultId))
                .isInstanceOf(EmptyResultDataAccessException.class);

        //expect
        assertThatThrownBy(() -> jdbcPlayerInfoDAO.insert(10, List.of(new Car("car", 1))))
                .isInstanceOf(DataAccessException.class);
    }

    @Test
    void findPlayerByPlayResultId_는_result_id_를_가진_데이터를_가져온다() {
        //given
        jdbcPlayerInfoDAO.insert(playResultId, List.of(new Car("car", 1)));

        //when
        final List<PlayerInfoEntity> playerByPlayResultId = jdbcPlayerInfoDAO.findPlayerByPlayResultId(playResultId);
        final PlayerInfoEntity playerInfoEntity = playerByPlayResultId.get(0);

        //then
        assertAll(
                () -> assertThat(playerInfoEntity.getName()).isEqualTo("car"),
                () -> assertThat(playerInfoEntity.getPosition()).isEqualTo(1)
        );
    }

    @Test
    void findPlayerByPlayResultId_는_같은_result_id_를_가진_데이터를_모두_찾을_수_있다() {
        //given
        jdbcPlayerInfoDAO.insert(playResultId, List.of(
                new Car("car", 1),
                new Car("car2", 2),
                new Car("car3", 3)
        ));

        //when
        final List<PlayerInfoEntity> playerByPlayResultId = jdbcPlayerInfoDAO.findPlayerByPlayResultId(playResultId);

        //then
        assertThat(playerByPlayResultId).hasSize(3);
    }
}
