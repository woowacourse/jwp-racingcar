package racingcar;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.dto.RacingCarNamesDto;
import racingcar.dto.RacingCarStatusDto;
import racingcar.dto.RacingCarWinnerDto;
import racingcar.entity.Player;
import racingcar.repository.GameRepository;
import racingcar.repository.PlayerRepository;
import racingcar.service.RacingCarGame;
import racingcar.service.RandomMoveStrategy;
import racingcar.service.TryCount;

@Service
public class RacingCarService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public RacingCarService(final GameRepository gameRepository, final PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public PlayResponse play(final PlayRequest playRequest) {
        RacingCarGame racingCarGame = createRacingCarGame(playRequest);
        TryCount tryCount = new TryCount(playRequest.getCount());
        playGame(tryCount, racingCarGame);
        PlayResponse response = save(playRequest.getCount(), racingCarGame);
        return response;
    }

    private RacingCarGame createRacingCarGame(final PlayRequest playRequest) {
        RacingCarNamesDto racingCarNamesDto = RacingCarNamesDto.of(playRequest.getNames());
        return RacingCarGame.from(racingCarNamesDto);
    }

    private void playGame(final TryCount tryCount, final RacingCarGame racingCarGame) {
        RandomMoveStrategy randomMoveStrategy = new RandomMoveStrategy();
        while (tryCount.isAvailable()) {
            racingCarGame.moveCars(randomMoveStrategy);
            tryCount.moveUntilZero();
        }
    }

    private PlayResponse save(final int trialCount, final RacingCarGame racingCarGame) {
        RacingCarWinnerDto winners = findWinners(racingCarGame);
        List<RacingCarStatusDto> racingCars = racingCarGame.getCarStatuses();
        long gameId = gameRepository.save(trialCount);
        playerRepository.saveAll(racingCars, winners.getWinners(), gameId);
        return PlayResponse.of(winners, racingCars);
    }

    private RacingCarWinnerDto findWinners(final RacingCarGame racingCarGame) {
        return racingCarGame.findWinners();
    }

    public List<HistoryResponse> getHistory() {
        List<Player> allPlayers = playerRepository.findAll();
        Map<Long, List<Player>> playersGroupingByGameId = allPlayers.stream()
                .collect(Collectors.groupingBy(Player::getGameId));

        return playersGroupingByGameId.values().stream()
                .map(makeHistoryResponse())
                .collect(Collectors.toList());
    }

    private Function<List<Player>, HistoryResponse> makeHistoryResponse() {
        return players -> {
            String winners = filterWinner(players).stream()
                    .map(Player::getName)
                    .collect(Collectors.joining(","));
            List<RacingCarStatusDto> racingCarStatusDtos = players.stream()
                    .map(RacingCarStatusDto::from)
                    .collect(Collectors.toList());
            return new HistoryResponse(winners, racingCarStatusDtos);
        };
    }

    private List<Player> filterWinner(List<Player> players) {
        return players.stream()
                .filter(Player::isWinner)
                .collect(Collectors.toList());
    }
}
