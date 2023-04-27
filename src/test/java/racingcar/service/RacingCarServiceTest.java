package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.RandomNumberGenerator;
import racingcar.domain.TestNumberGenerator;
import racingcar.domain.entity.CarResultEntity;
import racingcar.domain.entity.RacingGameResultEntity;
import racingcar.dto.request.RacingGameRequest;
import racingcar.dto.response.RacingGameResponse;
import racingcar.repository.RacingCarRepository;

class RacingCarServiceTest {

    private RacingCarService racingCarService;

    @DisplayName("게임을 실행한 후 결과에 맞는 Response를 생성한다.")
    @Test
    void play_and_return() {
        //given
        TestNumberGenerator testNumberGenerator = new TestNumberGenerator(List.of(1, 10, 10));
        racingCarService = new RacingCarService(new TestRacingCarRepository(null), testNumberGenerator);
        RacingGameRequest given = new RacingGameRequest("포비,현서,참치", 1);
        //when
        RacingGameResponse racingGameResponse = racingCarService.play(given);
        //then
        assertThat(racingGameResponse.getRacingCars().size()).isEqualTo(3);
        assertThat(racingGameResponse.getWinners()).isEqualTo("현서,참치");
    }

    @DisplayName("저장된 모든 Entity를 가져와 양식에 맞는 Response로 변환한다.")
    @Test
    void findAll() {
        //given
        List<CarResultEntity> carEntities = List.of(
                new CarResultEntity("현서", 10, true),
                new CarResultEntity("오리", 10, true),
                new CarResultEntity("은서", 1, false));
        List<RacingGameResultEntity> racingGameEntities = List.of(new RacingGameResultEntity(carEntities, 10));

        RacingCarService racingCarService = new RacingCarService(
                new TestRacingCarRepository(racingGameEntities), new RandomNumberGenerator());

        //when
        List<RacingGameResponse> actualList = racingCarService.findGameResults();
        RacingGameResponse actual = actualList.get(0);
        //then
        assertThat(actualList.size()).isEqualTo(1);
        assertThat(actual.getWinners()).contains("현서", "오리");
        assertThat(actual.getRacingCars().size()).isEqualTo(3);
    }

    class TestRacingCarRepository implements RacingCarRepository {

        private final List<RacingGameResultEntity> entities;

        public TestRacingCarRepository(final List<RacingGameResultEntity> entities) {
            this.entities = entities;
        }

        @Override
        public void save(final RacingGameResultEntity racingGameResultDto) {
            return;
        }

        @Override
        public List<RacingGameResultEntity> findAll() {
            return entities;
        }
    }
}
