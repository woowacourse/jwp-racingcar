package racingcar.services;

import org.springframework.stereotype.Service;
import racingcar.dao.RacingGameRepository;
import racingcar.dto.RacingGameDto;
import racingcar.dto.RacingGameMapper;
import racingcar.dto.StartInformationDto;
import racingcar.model.MoveCount;
import racingcar.model.RacingGame;
import racingcar.model.car.Cars;
import racingcar.model.manager.ThresholdCarMoveManager;

import java.util.List;

@Service
public class RacingGameService {

    private final RacingGameRepository racingGameRepository;

    public RacingGameService(RacingGameRepository racingGameRepository) {
        this.racingGameRepository = racingGameRepository;
    }

    public RacingGameDto play(StartInformationDto startInformationDto) {
        RacingGame racingGame = createRacingGame(startInformationDto);
        racingGame.play();
        RacingGameDto racingGameDto = RacingGameMapper.toRacingGameDto(racingGame);
        racingGameRepository.save(racingGameDto);
        return racingGameDto;
    }

    private RacingGame createRacingGame(StartInformationDto startInformationDto) {
        Cars cars = Cars.from(startInformationDto.getNames());
        MoveCount moveCount = MoveCount.from(startInformationDto.getCount());
        return new RacingGame(new ThresholdCarMoveManager(), cars, moveCount);
    }

    public List<RacingGameDto> queryHistory() {
        return racingGameRepository.selectAll();
    }
}
