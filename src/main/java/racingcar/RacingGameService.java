package racingcar;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Cars;
import racingcar.domain.Name;
import racingcar.domain.RacingGame;
import racingcar.domain.TryCount;
import racingcar.domain.movingstrategy.DefaultMovingStrategy;
import racingcar.repository.CarRepository;
import racingcar.repository.GameResultRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    private final GameResultRepository gameResultRepository;
    private final CarRepository carRepository;

    public RacingGameService(final GameResultRepository gameResultRepository, final CarRepository carRepository) {
        this.gameResultRepository = gameResultRepository;
        this.carRepository = carRepository;
    }


    @Transactional
    public ResultDto getResult(final UserInputDto inputDto) {
        final RacingGame racingGame = getRacingGame(inputDto);

        final Long gameResultId = gameResultRepository.saveGameResult(new TryCount(inputDto.getCount()));

        final List<Cars> result = racingGame.start(new DefaultMovingStrategy());
        final Cars finalResult = result.get(result.size() - 1);
        final Cars winnersResult = racingGame.decideWinners();

        carRepository.saveCars(gameResultId, finalResult, winnersResult);

        return new ResultDto(winnersResult, finalResult);
    }

    private RacingGame getRacingGame(final UserInputDto inputDto) {
        final String names = inputDto.getNames();
        final List<String> splitNames = List.of(names.split(","));
        final List<Name> nameList = splitNames.stream()
                .map(Name::of)
                .collect(Collectors.toList());
        final TryCount tryCount = new TryCount(inputDto.getCount());

        return new RacingGame(nameList, tryCount);
    }
}
