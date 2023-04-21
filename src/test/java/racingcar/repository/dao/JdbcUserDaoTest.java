package racingcar.repository.dao;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import racingcar.repository.entity.UserEntity;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JdbcUserDaoTest {

    @Autowired
    private DataSource dataSource;

    private UserDao userDao;

    @BeforeEach
    void setUp() {
        userDao = new JdbcUserDao(dataSource);
    }

    @Test
    void save_메서드로_user를_저장한다() {
        final UserEntity userEntity = new UserEntity("modi");
        long userId = userDao.save(userEntity);

        assertThat(userId).isGreaterThan(1L);
    }

    @Test
    void findByName_메서드로_user를_검색한다() {
        final UserEntity modiEntity = new UserEntity("modi");
        long userId = userDao.save(modiEntity);

        final UserEntity findEntity = userDao.findByName("modi");

        assertAll(
            () -> assertEquals(userId, findEntity.getId()),
            () -> assertEquals(modiEntity.getName(), findEntity.getName())
        );
    }
}
