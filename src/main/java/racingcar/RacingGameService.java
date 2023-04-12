package racingcar;

import org.springframework.stereotype.Service;
import racingcar.domain.Cars;
import racingcar.domain.Name;
import racingcar.domain.RacingGame;
import racingcar.domain.TryCount;
import racingcar.repository.RacingGameRepository;
import racingcar.utils.DefaultMovingStrategy;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    private final RacingGameRepository repository;

    public RacingGameService(RacingGameRepository repository) {
        this.repository = repository;
    }

    public ResultDto getResult(UserInputDto inputDto) {
        RacingGame racingGame = getRacingGame(inputDto);

        Long gameResultId = repository.saveGameResult(new TryCount(inputDto.getCount()));

        List<Cars> result = racingGame.start(new DefaultMovingStrategy());
        Cars finalResult = result.get(result.size() - 1);
        Cars winnersResult = racingGame.decideWinners();

        repository.saveCars(gameResultId, finalResult, winnersResult);

        return new ResultDto(winnersResult, finalResult);
    }

    private RacingGame getRacingGame(UserInputDto inputDto) {
        String names = inputDto.getNames();
        List<String> splitNames = List.of(names.split(","));
        List<Name> nameList = splitNames.stream()
                .map(Name::of)
                .collect(Collectors.toList());
        TryCount tryCount = new TryCount(inputDto.getCount());

        return new RacingGame(nameList, tryCount);
    }
}
