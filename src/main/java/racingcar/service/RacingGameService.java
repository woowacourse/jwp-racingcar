package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.*;
import racingcar.dto.CarStatusDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.GameEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    private static final int RACE_START_POINT = 0;
    private static final String DELIMITER = ",";

    private final GameDao gameDao;
    private final CarDao carDao;
    private final NumberGenerator numberGenerator;

    public RacingGameService(final GameDao gameDao, final CarDao carDao, final NumberGenerator numberGenerator) {
        this.gameDao = gameDao;
        this.carDao = carDao;
        this.numberGenerator = numberGenerator;
    }

    public RacingGameResponseDto run(RacingGameRequestDto racingGameRequestDto) {
        //todo 2 : String으로 넘어온 자동차 이름들을 구분자(,)를 기준으로 리스트로 변환 후에, 넘겨주는 게 맞을까? RacingGame에 String을 넘기는 것이 맞을까?
        //뷰에서 넘어오는 구분자의 기준이 뭐가 될지 모른다. 따라서 컨트롤러에서 하는 게 맞는 것 같다 -- 추후 리팩터링 할것
        List<String> carNames = List.of(racingGameRequestDto.getNames().split(DELIMITER));
        RacingGame racingGame = RacingGame.of(carNames, racingGameRequestDto.getCount());
        racingGame.race(numberGenerator);

        int gameId = saveGame(racingGame);
        saveCars(racingGame, gameId);

        return createResult(racingGame);
    }

    //비즈니스 로직과 분리된 entity의 생성 책임은 gameDao에 있다고 생각한다
    private int saveGame(final RacingGame racingGame) {
        String winnerCars = String.join(",", racingGame.pickWinnerCarNames());
        return gameDao.save(new GameEntity(winnerCars, racingGame.getTryCount(), racingGame.getCreated_at()));
    }

    private void saveCars(RacingGame racingGame, final int gameId) {
        List<CarEntity> carEntities = new ArrayList<>();
        for (Car car : racingGame.getCars()) {
            carEntities.add(new CarEntity(car.getName(), car.getPosition(), gameId));
        }
        carDao.saveAll(carEntities);
    }

    private RacingGameResponseDto createResult(final RacingGame racingGame) {
        List<String> winnerCars = racingGame.pickWinnerCarNames();
        List<CarStatusDto> carStatuses = racingGame.getCars().stream()
                .map(car -> new CarStatusDto(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());

        return new RacingGameResponseDto(winnerCars, carStatuses);
    }


    //to remove
    private void saveCars1(final RacingGame racingGame, final int gameId) {
        for (Car car : racingGame.getCars()) {
            CarEntity carEntity = new CarEntity(car.getName(), car.getPosition(), gameId);
            carDao.save(carEntity);
        }
    }
}
