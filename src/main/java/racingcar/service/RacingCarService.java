package racingcar.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.domain.Cars;
import racingcar.domain.Count;
import racingcar.domain.RacingCarGame;
import racingcar.dto.GamePlayerJoinDto;
import racingcar.dto.PlayerDto;
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
        List<GamePlayerJoinDto> gamePlayerJoinDtos = racingCarRepository.readGameResultAll();
        Map<Long, List<GamePlayerJoinDto>> gameIdByGamePlayerJoinDto = gamePlayerJoinDtos.stream()
            .collect(Collectors.groupingBy(GamePlayerJoinDto::getGameId));

        List<RacingCarGameResultResponseDto> result = new ArrayList<>();

        for (Long gameId : gameIdByGamePlayerJoinDto.keySet()) {
            List<GamePlayerJoinDto> sameGameData = gameIdByGamePlayerJoinDto.get(gameId);
            String winners = sameGameData.stream()
                .filter(GamePlayerJoinDto::isWinner)
                .map(GamePlayerJoinDto::getPlayerName)
                .collect(Collectors.joining(","));
            List<PlayerDto> playerDtos = sameGameData.stream().map(data -> new PlayerDto(data.getPlayerName(), data.getPosition()))
                .collect(Collectors.toList());
            result.add(new RacingCarGameResultResponseDto(winners, playerDtos));
        }
        return result;
    }

    private List<String> splitNameWithComma(RacingCarGameRequestDto racingCarGameRequestDto) {
        return Arrays.stream(racingCarGameRequestDto.getNames().split(","))
            .map(String::trim)
            .collect(Collectors.toList());
    }
}
