package racingcar.web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Cars;
import racingcar.domain.Name;
import racingcar.domain.RacingGame;
import racingcar.domain.TryCount;
import racingcar.utils.DefaultMovingStrategy;
import racingcar.web.dto.ResultDto;
import racingcar.web.dto.UserInputDto;
import racingcar.web.repository.RacingGameRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    private final RacingGameRepository repository;

    public RacingGameService(RacingGameRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ResultDto getResult(UserInputDto inputDto) {
        RacingGame racingGame = getRacingGame(inputDto);

        Long gameResultId = repository.saveGameResult(new TryCount(inputDto.getCount()));

        List<Cars> results = getResults(racingGame);
        Cars finalResult = results.get(results.size() - 1);
        Cars winnersResult = racingGame.decideWinners();

        repository.saveCars(gameResultId, finalResult, winnersResult);

        return new ResultDto(winnersResult, finalResult);
    }

    private List<Cars> getResults(RacingGame racingGame) {
        List<Cars> results = racingGame.start(new DefaultMovingStrategy());
        return results;
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
