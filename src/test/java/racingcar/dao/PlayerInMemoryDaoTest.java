package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import racingcar.domain.CarGroup;

class PlayerInMemoryDaoTest {

    private PlayerInMemoryDao playerInMemoryDao;

    @BeforeEach
    void setUp() {
        playerInMemoryDao = new PlayerInMemoryDao();
    }

    @DisplayName("플레이어가 인메모리에서 정상적으로 저장되는지 확인한다.")
    @Test
    void save() {
        // given
        CarGroup carGroup = new CarGroup("저문,헤나");
        int racingGameId = 1;

        // when
        boolean isSaved = playerInMemoryDao.save(carGroup, racingGameId);

        // then
        assertThat(isSaved).isTrue();
    }
}
