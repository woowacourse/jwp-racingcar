package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import racingcar.service.GamesRepository;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class GamesRepositoryImplTest {

    private GamesRepository gamesRepository;

    @Autowired
    void setUp(final DataSource dataSource) {
        gamesRepository = RepositoryFactory.gamesRepository(dataSource);
    }

    @Test
    void 저장하면_게임_id_가_반환된다() {
        final int result = gamesRepository.save(3);

        assertThat(result).isPositive();
    }
}
