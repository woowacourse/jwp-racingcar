package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.dao.WinnerDao;
import racingcar.dao.entity.WinnerEntity;
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

    private final GameDao gameDao;
    private final CarDao carDao;
    private final WinnerDao winnerDao;
    private final NumberGenerator numberGenerator;

    public RacingGameService(final GameDao gameDao, final CarDao carDao, final WinnerDao winnerDao, final NumberGenerator numberGenerator) {
        this.gameDao = gameDao;
        this.carDao = carDao;
        this.winnerDao = winnerDao;
        this.numberGenerator = numberGenerator;
    }

    public RacingGameResponseDto run(RacingGameRequestDto racingGameRequestDto) {
        RacingGame racingGame = RacingGame.of(racingGameRequestDto.getNamesList(), racingGameRequestDto.getCount());
        racingGame.race(numberGenerator);

        int gameId = gameDao.save(GameEntity.from(racingGame));
        saveCars(racingGame.getCars(), gameId);
        saveWinners(racingGame.pickWinnerCarNames(), gameId);

        return findResultByGameId(gameId);
    }

    private void saveCars(final List<Car> cars, final int gameId) {
        List<CarEntity> carEntities = cars.stream()
                .map(car -> CarEntity.from(car, gameId))
                .collect(Collectors.toCollection(ArrayList::new));
        carDao.saveAll(carEntities);
    }

    private void saveWinners(List<String> winnerNames, int gameId) {
        List<CarEntity> cars = carDao.findCarsByGameID(gameId);
        List<WinnerEntity> winnerEntities = cars.stream()
                .filter(car -> winnerNames.contains(car.getName()))
                .map(car -> new WinnerEntity(car.getCarId(), car.getGameId()))
                .collect(Collectors.toList());

        winnerDao.saveAll(winnerEntities);
    }

    private RacingGameResponseDto findResultByGameId(int gameId) {
        List<CarEntity> carEntities = carDao.findCarsByGameID(gameId);
        List<String> winnerCarNames = findWinnerCarNames(gameId, carEntities);

        List<CarStatusDto> carStatuses = carEntities.stream()
                .map(car -> new CarStatusDto(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());

        return new RacingGameResponseDto(winnerCarNames, carStatuses);
    }

    private List<String> findWinnerCarNames(final int gameId, final List<CarEntity> cars) {
        List<Integer> winnerCarsId = winnerDao.findWinnerCarIdsByGameId(gameId);
        return cars.stream()
                .filter(car -> winnerCarsId.contains(car.getCarId()))
                .map(CarEntity::getName)
                .collect(Collectors.toList());
    }

    public List<RacingGameResponseDto> findAllGameResult() {
        List<Integer> listOfGameId = gameDao.findGameIds();

        return listOfGameId.stream()
                .peek(gameId -> System.out.println(gameId))
                .map(gameId -> findResultByGameId(gameId))
                .collect(Collectors.toList());
    }


    //1차
//    public RacingGameResponseDto run1(RacingGameRequestDto racingGameRequestDto) {
//        //todo 2 : String으로 넘어온 자동차 이름들을 구분자(,)를 기준으로 리스트로 변환 후에, 넘겨주는 게 맞을까? RacingGame에 String을 넘기는 것이 맞을까?
//        //뷰에서 넘어오는 구분자의 기준이 뭐가 될지 모른다. 따라서 컨트롤러에서 하는 게 맞는 것 같다 -- 추후 리팩터링 할것
//        List<String> carNames = List.of(racingGameRequestDto.getNames().split(DELIMITER));
//        RacingGame racingGame = RacingGame.of(carNames, racingGameRequestDto.getCount());
//        racingGame.race(numberGenerator);
//
//        int gameId = gameDao.save(GameEntity.of(racingGame.getTryCount(), racingGame.getCreated_at()));;
//        saveCars(racingGame, gameId);
//        saveWinners(racingGame);
//
//        return createResult(racingGame);
//    }
//
//
//    private void saveCars1(final RacingGame racingGame, final int gameId) {
//        for (Car car : racingGame.getCars()) {
//            CarEntity carEntity = CarEntity.of(car.getName(), car.getPosition(), gameId);
//            int carId = carDao.save(carEntity);
//            car.setCarId(carId);
//        }
//    }
//
//    private void saveWinners1(RacingGame racingGame) {
//        List<WinnerEntity> winnerEntities = racingGame.pickWinningCars().stream()
//                        .map(car -> new WinnerEntity(car.getCarId(), car.getGameId()))
//                        .collect(Collectors.toList());
//        winnerDao.saveAll(winnerEntities);
//    }
//    private RacingGameResponseDto createResult1(final RacingGame racingGame) {
//        List<String> winnerCars = racingGame.pickWinnerCarNames();
//        List<CarStatusDto> carStatuses = racingGame.getCars().stream()
//                .map(car -> new CarStatusDto(car.getName(), car.getPosition()))
//                .collect(Collectors.toUnmodifiableList());
//
//        return new RacingGameResponseDto(winnerCars, carStatuses);
//    }
//

}
