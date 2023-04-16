package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.PlayerDao;
import racingcar.dao.RaceDao;
import racingcar.dao.WinnerDao;
import racingcar.domain.Car;
import racingcar.dto.CarDto;
import racingcar.dto.GameInputDto;
import racingcar.dto.RacingResultRequestDto;
import racingcar.dto.RacingResultResponseDto;
import racingcar.game.Game;
import racingcar.util.NumberGenerator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional
public class RacingGameService {
    private final RaceDao raceDao;
    private final PlayerDao playerDao;
    private final WinnerDao winnerDao;

    public RacingGameService(RaceDao raceDao, PlayerDao playerDao, WinnerDao winnerDao) {
        this.raceDao = raceDao;
        this.playerDao = playerDao;
        this.winnerDao = winnerDao;
    }

    public RacingResultResponseDto playGameWithoutPrint(GameInputDto gameInputDto, NumberGenerator numberGenerator) {
        Game game = new Game(gameInputDto.getNames(), gameInputDto.getCount());
        game.playGameWithoutPrint(numberGenerator);
        RacingResultRequestDto racingResultRequestDto = new RacingResultRequestDto(game);
        
        long raceId = raceDao.insert(gameInputDto);
        playerDao.insertAll(racingResultRequestDto, raceId);
        List<Integer> winnerCarIds = playerDao.findWinnerCarIds(raceId, racingResultRequestDto);
        winnerDao.insertAll(raceId, winnerCarIds);
        return new RacingResultResponseDto(game.getWinners(), game.getCars());
    }
    
    public List<RacingResultResponseDto> findAllGameResult() {
        List<Long> ids = raceDao.findAllIds();
        List<List<Car>> winners = getWinners(ids);
        List<List<Car>> cars = getCars(ids);
        
        return IntStream.range(0, winners.size())
                .mapToObj(count -> new RacingResultResponseDto(winners.get(count), cars.get(count)))
                .collect(Collectors.toUnmodifiableList());
    }
    
    private List<List<Car>> getWinners(List<Long> ids) {
        return ids.stream()
                .map(winnerDao::findWinnerIdsByRaceId)
                .map(playerDao::findByIds)
                .map(this::parseCarDtos)
                .collect(Collectors.toUnmodifiableList());
    }
    
    private List<List<Car>> getCars(List<Long> ids) {
        return ids.stream()
                .map(playerDao::findByRaceIds)
                .map(this::parseCarDtos)
                .collect(Collectors.toUnmodifiableList());
    }
    
    private List<Car> parseCarDtos(List<CarDto> carDtos) {
        return carDtos.stream()
                .map(carDto -> {
                    Car car = new Car(carDto.getName(), carDto.getIdentifier());
                    car.drive(carDto.getPosition());
                    return car;
                })
                .collect(Collectors.toUnmodifiableList());
    }
}
