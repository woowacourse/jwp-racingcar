package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.RacingCarGameDao;
import racingcar.domain.car.Car;
import racingcar.domain.game.RacingCarGame;
import racingcar.dto.CarDto;
import racingcar.dto.DtoMapper;

import java.util.List;

@Service
public class RacingCarGameServiceImpl implements RacingCarGameService {

    private final RacingCarGameDao racingCarGameDao;

    @Autowired
    public RacingCarGameServiceImpl(final RacingCarGameDao racingCarGameDao) {
        this.racingCarGameDao = racingCarGameDao;
    }

    @Override
    public int play(final String names, final int count) {
        final RacingCarGame newGame = RacingCarGame.createNewGame(names);
        newGame.moveCars(count);
        return racingCarGameDao.save(newGame, count);
    }

    @Override
    public List<CarDto> getCars(final int gameId) {
        List<Car> carList = racingCarGameDao.findCarsByGameId(gameId);
        return DtoMapper.toCarDtos(carList);
    }

    @Override
    public List<String> getWinners(final int gameId) {
        return racingCarGameDao.findWinners(gameId);
    }

    @Override
    public List<Integer> getAllGameIds() {
        return racingCarGameDao.findAllGameIds();
    }
}
