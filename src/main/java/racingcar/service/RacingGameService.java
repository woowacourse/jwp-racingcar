package racingcar.service;

import static java.util.Collections.addAll;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import racingcar.dao.PlayerSaveDto;
import racingcar.dao.RacingGameDao;
import racingcar.domain.Name;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;
import racingcar.domain.TryCount;
import racingcar.exception.CommaNotFoundException;

@Service
public class RacingGameService {

    private final RacingGameDao racingGameDao;

    public RacingGameService(final RacingGameDao racingGameDao) {
        this.racingGameDao = racingGameDao;
    }

    public RacingCars run(final String inputNames, final int inputCount) {
        final List<Name> names = sliceNames(inputNames);
        final RacingCars racingCars = new RacingCars(createRacingCar(names));
        final TryCount tryCount = new TryCount(inputCount);

        moveCars(racingCars, tryCount);

        saveRacingCars(inputCount, racingCars);
        return racingCars;
    }

    private static List<Name> sliceNames(final String inputNames) {
        return sliceNameByComma(inputNames).stream()
                .map(Name::new)
                .collect(toList());
    }

    private static List<String> sliceNameByComma(final String names) {
        validateComma(names);

        return getSplitName(names);
    }

    private static List<String> getSplitName(final String names) {
        List<String> splitNames = new ArrayList<>();
        addAll(splitNames, names.split(","));

        return splitNames;
    }

    private static void validateComma(final String names) {
        if (!names.contains(",")) {
            throw new CommaNotFoundException();
        }
    }

    private List<RacingCar> createRacingCar(final List<Name> names) {
        return names.stream()
                .map(RacingCar::createRandomMoveRacingCar)
                .collect(toList());
    }

    private void moveCars(final RacingCars racingCars, final TryCount tryCount) {
        while (canProceed(tryCount)) {
            racingCars.moveAll();
            tryCount.deduct();
        }
    }

    private boolean canProceed(final TryCount tryCount) {
        return !tryCount.isZero();
    }

    private void saveRacingCars(final int tryCount, final RacingCars racingCars) {
        final List<String> winnerNames = racingCars.getWinnerNames();
        final List<PlayerSaveDto> playerSaveDtos = racingCars.getRacingCars().stream()
                .map(racingCar -> createPlayerSaveDto(winnerNames, racingCar))
                .collect(toList());
        racingGameDao.save(tryCount, playerSaveDtos);
    }

    private static PlayerSaveDto createPlayerSaveDto(final List<String> winnerNames, final RacingCar racingCar) {
        return new PlayerSaveDto(racingCar.getName(), racingCar.getPosition(), winnerNames.contains(racingCar.getName()));
    }
}
