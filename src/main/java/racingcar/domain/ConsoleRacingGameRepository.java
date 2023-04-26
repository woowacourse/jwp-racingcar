package racingcar.domain;

import racingcar.dto.CarDto;
import racingcar.dto.RacingGameDto;
import racingcar.dto.WinnerDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public final class ConsoleRacingGameRepository implements RacingGameRepository {

    private Long serial = 1L;
    private final Map<Long, RacingGame> racingGameSaved = new HashMap<>();
    private final Map<Long, Winners> winnersSaved = new HashMap<>();
    private final Map<Long, Integer> gameTimeSaved = new HashMap<>();

    @Override
    public Long save(final RacingGame racingGame) {
        racingGameSaved.put(serial, racingGame);
        final Winners winners = new Winners(new Cars(racingGame.getCars()));
        winnersSaved.put(serial, winners);
        gameTimeSaved.put(serial, racingGame.getGameTimeValue());
        return serial++;
    }

    @Override
    public Optional<RacingGameDto> findById(final Long id) {
        final RacingGame racingGame = racingGameSaved.get(id);
        final List<CarDto> carDtos = racingGame.getCars().stream()
                .map(CarDto::from)
                .collect(Collectors.toList());
        final Integer trialCount = gameTimeSaved.get(id);

        return Optional.of(new RacingGameDto(carDtos, trialCount));
    }

    @Override
    public Map<Long, List<CarDto>> findAllCars() {
        final Map<Long, List<CarDto>> allCars = new HashMap<>();
        for (final Map.Entry<Long, RacingGame> entry : racingGameSaved.entrySet()) {
            final Long id = entry.getKey();
            final RacingGame racingGame = entry.getValue();
            final List<CarDto> carDtos = racingGame.getCars().stream()
                    .map(CarDto::from)
                    .collect(Collectors.toList());
            allCars.put(id, carDtos);
        }
        return allCars;
    }

    @Override
    public Map<Long, List<WinnerDto>> findAllWinners() {
        final Map<Long, List<WinnerDto>> allWinners = new HashMap<>();
        for (final Map.Entry<Long, Winners> entry : winnersSaved.entrySet()) {
            final Long id = entry.getKey();
            final Winners winners = entry.getValue();
            final List<WinnerDto> winnerDtos = WinnerDto.createWinnerDtos(winners);
            allWinners.put(id, winnerDtos);
        }
        return allWinners;
    }

    public Map<Long, RacingGame> getRacingGameSaved() {
        return new HashMap<>(racingGameSaved);
    }

    public Map<Long, Winners> getWinnersSaved() {
        return new HashMap<>(winnersSaved);
    }

    public Map<Long, Integer> getGameTimeSaved() {
        return new HashMap<>(gameTimeSaved);
    }
}
