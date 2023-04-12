package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.domain.RacingGame;
import racingcar.dto.response.CarDto;
import racingcar.dto.response.CarGameResponse;
import racingcar.entity.CarResultEntity;
import racingcar.entity.PlayResultEntity;
import racingcar.mapper.CarResultMapper;
import racingcar.mapper.PlayResultMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    @Autowired
    private final CarResultMapper carResultMapper;

    @Autowired
    private final PlayResultMapper playResultMapper;

    public RacingGameService(CarResultMapper carResultMapper, PlayResultMapper playResultMapper) {
        this.carResultMapper = carResultMapper;
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
                .map(car -> CarResultEntity.of(resultId, car.getName(), car.getPosition()))
                .forEach(carResultMapper::save);

        CarGameResponse response = new CarGameResponse(winners, carDtos);
        return response;
    }

    private void progress(RacingGame game) {
        while (!game.isEnd()) {
            game.play();
        }
    }
}
