package racingcar.repository.dao;

import static org.assertj.core.api.Assertions.*;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import racingcar.repository.entity.PositionEntity;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JdbcPositionDaoTest {

    @Autowired
    private DataSource dataSource;

    private PositionDao positionDao;

    @BeforeEach
    void setUp() {
        positionDao = new JdbcPositionDao(dataSource);
    }

    @Test
    void save_메서드로_position을_저장한다() {
        final Long dummyGameId = 1L;
        final Long dummyUserId = 1L;
        final PositionEntity positionEntity = new PositionEntity(dummyGameId, dummyUserId, 5);

        long positionId = positionDao.save(positionEntity);

        assertThat(positionId).isGreaterThan(0L);
    }
}
