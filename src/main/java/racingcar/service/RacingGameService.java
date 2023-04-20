package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dto.PlayerSaveDto;
import racingcar.dao.RacingGameDao;
import racingcar.domain.*;
import racingcar.dto.PostGameResponse;
import racingcar.dto.RacingCarDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class RacingGameService {

    private final RacingGameDao racingGameDao;

    public RacingGameService(final RacingGameDao racingGameDao) {
        this.racingGameDao = racingGameDao;
    }

    public PostGameResponse run(final List<String> inputNames, final int inputCount) {
        final List<Name> names = generateNames(inputNames);
        final RacingGame racingGame = new RacingGame(new RacingCars(generateRacingCars(names)), new TryCount(inputCount));
        final RacingCars racingCars = racingGame.moveCars();

        saveRacingCars(inputCount, racingCars);

        return generatePostGameResponse(racingCars);
    }

    private List<Name> generateNames(final List<String> inputNames) {
        return inputNames.stream()
                .map(Name::new)
                .collect(toList());
    }

    private List<RacingCar> generateRacingCars(final List<Name> names) {
        return names.stream()
                .map(RacingCar::createRandomMoveRacingCar)
                .collect(toList());
    }

    private void saveRacingCars(final int tryCount, final RacingCars racingCars) {
        final List<String> winnerNames = racingCars.getWinnerNames();
        final List<PlayerSaveDto> playerSaveDtos = racingCars.getRacingCars().stream()
                .map(racingCar -> createPlayerSaveDto(winnerNames, racingCar))
                .collect(toList());
        racingGameDao.save(tryCount, playerSaveDtos);
    }

    private PlayerSaveDto createPlayerSaveDto(final List<String> winnerNames, final RacingCar racingCar) {
        return new PlayerSaveDto(racingCar.getName(), racingCar.getPosition(), winnerNames.contains(racingCar.getName()));
    }

    private PostGameResponse generatePostGameResponse(RacingCars racingCars) {
        final List<String> winnerNames = racingCars.getWinnerNames();
        final String winnerName = String.join(", ", winnerNames);

        final List<RacingCarDto> racingCarsDto = racingCars.getRacingCars().stream()
                .map(racingCar -> new RacingCarDto(racingCar.getName(), racingCar.getPosition()))
                .collect(toList());
        return new PostGameResponse(winnerName, racingCarsDto);
    }
}
