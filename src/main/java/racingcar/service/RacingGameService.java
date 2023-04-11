package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.domain.RacingGame;
import racingcar.dto.response.CarDto;
import racingcar.dto.response.CarGameResponse;
import racingcar.entity.CarEntity;
import racingcar.entity.PlayResultEntity;
import racingcar.mapper.CarMapper;
import racingcar.mapper.PlayResultMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    @Autowired
    private final CarMapper carMapper;

    @Autowired
    private final PlayResultMapper playResultMapper;

    public RacingGameService(CarMapper carMapper, PlayResultMapper playResultMapper) {
        this.carMapper = carMapper;
        this.playResultMapper = playResultMapper;
    }

    public CarGameResponse play(RacingGame game) {
        int tryCount = game.getTryCount();
        progress(game);
        List<CarDto> carDtos = game.getCars().getUnmodifiableCars().stream().map(CarDto::new).collect(Collectors.toList());
        String winners = game.decideWinners().stream().collect(Collectors.joining(","));

        long resultId = playResultMapper.save(PlayResultEntity.of(tryCount, winners));
        game.getCars().getUnmodifiableCars()
                .stream()
                .map(car -> CarEntity.of(resultId, car.getName(), car.getPosition()))
                .forEach(carMapper::save);

        CarGameResponse response = new CarGameResponse(winners, carDtos);
        return response;
    }

    private void progress(RacingGame game) {
        while (!game.isEnd()) {
            game.play();
        }
    }
}
