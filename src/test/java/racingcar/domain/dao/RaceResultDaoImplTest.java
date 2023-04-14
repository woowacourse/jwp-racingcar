package racingcar.domain.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.dao.entity.RaceEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class RaceResultDaoImplTest {

    private static final int trialCount = 10;
    private static final String winners = "test1,test2";

    @Autowired
    private RaceResultDaoImpl raceResultDao;

    @Test
    @DisplayName("레이싱 결과를 저장한다.")
    public void save() {
        //when
        final Long savedId = raceResultDao.save(trialCount, winners);

        //then
        assertThat(savedId).isNotNull();
    }

    @Test
    @DisplayName("모든 레이싱 결과를 가져온다.")
    public void findAll() {
        //given
        raceResultDao.save(trialCount, winners);

        //when
        final List<RaceEntity> result = raceResultDao.findAll();

        //then
        assertThat(result.size()).isEqualTo(1);
    }
}
