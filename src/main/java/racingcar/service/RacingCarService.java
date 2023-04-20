package racingcar.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.domain.Cars;
import racingcar.domain.Count;
import racingcar.domain.RacingCarGame;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.ResultResponseDto;
import racingcar.repository.RacingCarRepository;
import racingcar.util.NumberGenerator;

@Service
public class RacingCarService {

    private final NumberGenerator numberGenerator;
    private final RacingCarRepository racingCarRepository;

    public RacingCarService(NumberGenerator numberGenerator, RacingCarRepository racingCarRepository) {
        this.numberGenerator = numberGenerator;
        this.racingCarRepository = racingCarRepository;
    }

    public ResultResponseDto play(RacingGameRequestDto racingGameRequestDto) {
        Cars cars = Cars.of(splitNameWithComma(racingGameRequestDto));
        Count count = Count.of(racingGameRequestDto.getCount());
        RacingCarGame racingCarGame = new RacingCarGame(cars, count);

        racingCarGame.play(numberGenerator);
        racingCarRepository.save(racingCarGame);

        return ResultResponseDto.of(racingCarGame);
    }

    public List<ResultResponseDto> readGameResultAll() {
        return racingCarRepository.readGameResultAll();
    }

    private List<String> splitNameWithComma(RacingGameRequestDto racingGameRequestDto) {
        return Arrays.stream(racingGameRequestDto.getNames().split(","))
            .map(String::trim)
            .collect(Collectors.toList());
    }
}
