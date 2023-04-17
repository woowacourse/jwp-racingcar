package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Cars;
import racingcar.domain.Names;
import racingcar.domain.RacingGame;
import racingcar.domain.TryCount;
import racingcar.domain.movingstrategy.DefaultMovingStrategy;
import racingcar.dto.GameResultResponseDto;
import racingcar.dto.UserRequestDto;
import racingcar.repository.RacingGameRepository;
import racingcar.utils.DtoMapper;

import java.util.List;

@Service
public class RacingGameService {

    private final RacingGameRepository racingGameRepository;

    public RacingGameService(final RacingGameRepository racingGameRepository) {
        this.racingGameRepository = racingGameRepository;
    }

    @Transactional
    public GameResultResponseDto getResult(final UserRequestDto inputDto) {
        final RacingGame racingGame = mapToRacingGame(inputDto);
        final Cars finalResult = racingGame.run(new DefaultMovingStrategy());
        racingGameRepository.save(racingGame);

        return DtoMapper.mapToGameResultResponseDto(finalResult);
    }

    private RacingGame mapToRacingGame(final UserRequestDto inputDto) {
        final String names = inputDto.getNames();
        final List<String> splitNames = List.of(names.split(","));
        final TryCount tryCount = new TryCount(inputDto.getCount());

        return new RacingGame(new Names(splitNames), tryCount);
    }
}
