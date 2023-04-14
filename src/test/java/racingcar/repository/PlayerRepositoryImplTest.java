package racingcar.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import racingcar.domain.CarGroup;

@SpringBootTest
class PlayerRepositoryImplTest {

    @Autowired
    private PlayerRepositoryImpl playerRepositoryImpl;

    @Autowired
    private RacingGameRepositoryImpl racingGameRepositoryImpl;

    @DisplayName("플레이어 저장")
    @Test
    void save() {
        // given
        CarGroup carGroup = new CarGroup("저문,헤나");
        int racingGameId = racingGameRepositoryImpl.save("저문,헤나", 10);

        // when
        boolean isSaved = playerRepositoryImpl.save(carGroup, racingGameId);

        // then
        assertThat(isSaved).isTrue();
    }
}
