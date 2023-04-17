package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.GameResultDAO;
import racingcar.dao.PlayerResultDAO;
import racingcar.domain.Car;
import racingcar.domain.Name;
import racingcar.domain.RacingGame;
import racingcar.domain.TryCount;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayerResultDto;
import racingcar.dto.response.GameResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {
    private static final String DELIMITER = ",";

    private final GameResultDAO gameResultDAO;
    private final PlayerResultDAO playerResultDAO;

    @Autowired
    public RacingGameService(GameResultDAO gameResultDAO, PlayerResultDAO playerResultDAO) {
        this.gameResultDAO = gameResultDAO;
        this.playerResultDAO = playerResultDAO;
    }

    public GameResponseDto play(List<String> names, int tryCount) {
        RacingGame racingGame = new RacingGame(convertToNames(names));

        racingGame.moveCars(TryCount.from(tryCount));

        String winners = decideWinners(racingGame);
        List<PlayerResultDto> playerResultDtos = getPlayerResultDtos(racingGame);

        int savedId = gameResultDAO.save(GameResultDto.of(tryCount, winners));
        playerResultDAO.save(savedId, playerResultDtos);

        return new GameResponseDto(winners, playerResultDtos);
    }

    private List<Name> convertToNames(List<String> names) {
        return names.stream()
                .map(Name::from)
                .collect(Collectors.toList());
    }

    private String decideWinners(RacingGame racingGame) {
        return racingGame.decideWinners().getCars().stream()
                .map(Car::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    private List<PlayerResultDto> getPlayerResultDtos(RacingGame racingGame) {
        return racingGame.getCars().stream()
                .map(PlayerResultDto::from)
                .collect(Collectors.toList());
    }
}
