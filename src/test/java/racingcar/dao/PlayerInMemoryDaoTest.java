package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import racingcar.dao.mapper.PlayerDtoMapper;
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

    @DisplayName("아이디를 받아서 플레이어들을 반환한다.")
    @Test
    void findAllById() {
        // given
        CarGroup carGroup = new CarGroup("저문,헤나");
        int racingGameId = 1;

        playerInMemoryDao.save(carGroup, racingGameId);

        // when
        List<PlayerDtoMapper> foundPlayers = playerInMemoryDao.findAllById(racingGameId);

        // then
        assertThat(foundPlayers).hasSize(2);
        assertThat(foundPlayers.get(0).getName()).isEqualTo("저문");
        assertThat(foundPlayers.get(1).getName()).isEqualTo("헤나");
    }
}
