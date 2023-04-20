package racingcar.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.domain.Cars;
import racingcar.domain.Count;
import racingcar.domain.RacingCarGame;
import racingcar.dto.RacingCarGameRequestDto;
import racingcar.dto.RacingCarGameResultResponseDto;
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

    public RacingCarGameResultResponseDto play(RacingCarGameRequestDto racingCarGameRequestDto) {
        Cars cars = Cars.of(splitNameWithComma(racingCarGameRequestDto));
        Count count = Count.of(racingCarGameRequestDto.getCount());
        RacingCarGame racingCarGame = new RacingCarGame(cars, count);

        racingCarGame.play(numberGenerator);
        racingCarRepository.save(racingCarGame);

        return RacingCarGameResultResponseDto.of(racingCarGame);
    }

    public List<RacingCarGameResultResponseDto> readGameResultAll() {
        return racingCarRepository.readGameResultAll();
    }

    private List<String> splitNameWithComma(RacingCarGameRequestDto racingCarGameRequestDto) {
        return Arrays.stream(racingCarGameRequestDto.getNames().split(","))
            .map(String::trim)
            .collect(Collectors.toList());
    }
}
