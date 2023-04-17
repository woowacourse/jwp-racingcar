package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.domain.GameProcess;
import racingcar.domain.Names;
import racingcar.domain.RacingGame;
import racingcar.domain.movingstrategy.MovingStrategy;
import racingcar.dto.input.CarNameRequest;
import racingcar.dto.input.TryCountRequest;
import racingcar.dto.output.PrintCriticalExceptionDto;
import racingcar.dto.output.PrintExceptionDto;
import racingcar.dto.output.PrintMovingStatusDto;
import racingcar.dto.output.PrintWinnersDto;
import racingcar.view.IOViewResolver;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class RacingGameController {

    private final IOViewResolver ioViewResolver;
    private final Map<GameProcess, Supplier<GameProcess>> processMap;

    private RacingGame racingGame;
    private Names names;

    public RacingGameController(final IOViewResolver ioViewResolver, final MovingStrategy strategy) {
        this.ioViewResolver = ioViewResolver;
        this.processMap = new EnumMap<>(GameProcess.class);
        initProcessMap(strategy);
    }

    private void initProcessMap(final MovingStrategy strategy) {
        processMap.put(GameProcess.READ_CAR_NAMES, this::readCarNames);
        processMap.put(GameProcess.READ_TRY_COUNT, this::readTryCount);
        processMap.put(GameProcess.START_RACE, () -> this.startRace(strategy));
        processMap.put(GameProcess.PRINT_WINNERS, this::printWinners);
    }

    public GameProcess run(final GameProcess process) {
        try {
            return processMap.get(process).get();
        } catch (IllegalArgumentException exception) {
            ioViewResolver.outputViewResolve(new PrintExceptionDto(exception));
            return process;
        } catch (Exception exception) {
            ioViewResolver.outputViewResolve(new PrintCriticalExceptionDto(exception));
            return GameProcess.EXIT;
        }
    }

    private GameProcess readCarNames() {
        final CarNameRequest carNameRequest = ioViewResolver.inputViewResolve(CarNameRequest.class);
        this.names = new Names(carNameRequest.getCarNames());

        return GameProcess.READ_TRY_COUNT;
    }

    private GameProcess readTryCount() {
        final TryCountRequest tryCountRequest = ioViewResolver.inputViewResolve(TryCountRequest.class);
        racingGame = new RacingGame(names, tryCountRequest.getTryCount());
        return GameProcess.START_RACE;
    }

    private GameProcess startRace(final MovingStrategy strategy) {
        final List<Cars> movingStatus = racingGame.run(strategy);
        ioViewResolver.outputViewResolve(new PrintMovingStatusDto(movingStatus));
        return GameProcess.PRINT_WINNERS;
    }

    private GameProcess printWinners() {
        ioViewResolver.outputViewResolve(new PrintWinnersDto(racingGame.getWinners()));
        return GameProcess.EXIT;
    }
}
