package racingcar.database;

import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.dto.response.RacingCarResponseDto;
import racingcar.dto.response.RacingGameResponseDto;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabase implements Database {

    private final List<RacingGame> racingGames;
    private final List<Integer> trialCounts;

    public InMemoryDatabase() {
        this.racingGames = new ArrayList<>();
        this.trialCounts = new ArrayList<>();
    }

    @Override
    public void save(RacingGame racingGame, int trialCount) {
        racingGames.add(racingGame);
        trialCounts.add(trialCount);
    }

    @Override
    public List<RacingGameResponseDto> findAllHistories() {
        List<RacingGameResponseDto> racingGameResponseDtos = new ArrayList<>();

        for (RacingGame racingGame : racingGames) {
            List<RacingCarResponseDto> racingCarResponseDtos = createRacingCarResponseDtos(racingGame);

            racingGameResponseDtos.add(new RacingGameResponseDto(
                    String.join(",", racingGame.findWinners()), racingCarResponseDtos));
        }
        return racingGameResponseDtos;
    }

    private List<RacingCarResponseDto> createRacingCarResponseDtos(RacingGame racingGame) {
        List<RacingCarResponseDto> racingCarResponseDtos = new ArrayList<>();

        for (Car car : racingGame.getCurrentResult()) {
            racingCarResponseDtos.add(new RacingCarResponseDto(car.getName(), car.getPosition()));
        }
        return racingCarResponseDtos;
    }
}
