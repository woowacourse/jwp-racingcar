package racingcar.controller;

import org.springframework.stereotype.Component;
import racingcar.dao.Player;
import racingcar.domain.RacingCars;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Component
public class RacingGameMapper {

    public GameResponse toGameResponse(final RacingCars racingCars) {
        final String winnerName = String.join(", ", racingCars.getWinnerNames());

        final List<RacingCarDto> racingCarsDto = racingCars.getRacingCars().stream()
                .map(racingCar -> new RacingCarDto(racingCar.getName(), racingCar.getPosition()))
                .collect(toList());

        return new GameResponse(winnerName, racingCarsDto);
    }

    public List<GameResponse> toGameResponses(Map<Long, List<Player>> results) {
        return results.values().stream()
                .map(RacingGameMapper::createGameResponses)
                .collect(toList());
    }

    private static GameResponse createGameResponses(List<Player> players) {
        final String winnersName = findWinnersName(players);
        final List<RacingCarDto> carDtos = toRacingCarDto(players);

        return new GameResponse(winnersName, carDtos);
    }

    private static List<RacingCarDto> toRacingCarDto(List<Player> players) {
        return players.stream()
                .map(player -> new RacingCarDto(player.getName(), player.getPosition()))
                .collect(toList());
    }

    private static String findWinnersName(List<Player> players) {
        return players.stream()
                .filter(Player::getIsWinner)
                .map(Player::getName)
                .collect(joining(", "));
    }
}
