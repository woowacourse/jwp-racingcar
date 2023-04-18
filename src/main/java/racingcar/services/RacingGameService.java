package racingcar.services;

import org.springframework.stereotype.Service;
import racingcar.dao.RacingGameRepository;
import racingcar.dto.*;
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

    public ResponseDto play(StartInformationDto startInformationDto) {
        RacingGame racingGame = createRacingGame(startInformationDto);
        racingGame.play();
        racingGameRepository.saveGame(RacingGameMapper.toGameResultDto(racingGame));
        return RacingGameMapper.toResponseDto(racingGame);
    }

    private RacingGame createRacingGame(StartInformationDto startInformationDto) {
        Cars cars = Cars.from(startInformationDto.getNames());
        MoveCount moveCount = MoveCount.from(startInformationDto.getCount());
        return new RacingGame(new ThresholdCarMoveManager(), cars, moveCount);
    }

    public List<ResponseDto> queryHistory() {
        return RacingGameMapper.toResponseDto(racingGameRepository.selectAllGames());
    }
}
