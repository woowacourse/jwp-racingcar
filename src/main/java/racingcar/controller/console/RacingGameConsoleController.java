package racingcar.controller.console;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.GameResultResponseDto;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

@Controller
public final class RacingGameConsoleController {

	private final InputView inputView;
	private final OutputView outputView;
	private final RacingCarService racingCarService;

	@Autowired
	public RacingGameConsoleController (final InputView inputView, final OutputView outputView,
										final RacingCarService racingCarService) {
		this.inputView = inputView;
		this.outputView = outputView;
		this.racingCarService = racingCarService;
	}

	public void run () {
		while (true) {
			playGameAndSave();
		}
	}

	private void playGameAndSave () {
		final List<String> carNames = inputCarsNameWithValidation();
		final TryCount count = inputTryCountWithValidation();
		GameResultResponseDto gameResultResponseDto = racingCarService.startRace(carNames, count);
		outputView.printResult(gameResultResponseDto);
	}

	private List<String> inputCarsNameWithValidation () {
		Optional<Cars> cars = null;
		do {
			cars = inputCarsHandlingException();
		} while (cars.isEmpty());
		return cars.get().getCars().stream().map(Car::getCarName).collect(Collectors.toList());
	}

	private Optional<Cars> inputCarsHandlingException () {
		try {
			return Optional.of(Cars.createByNames(inputView.inputCarNames()));
		} catch (IllegalArgumentException e) {
			outputView.printError(e.getMessage());
			return Optional.empty();
		}
	}

	경

	private TryCount inputTryCountWithValidation () {
		Optional<TryCount> tryCount = null;
		do {
			tryCount = inputTryCountHandlingException();
		} while (tryCount.isEmpty());
		return tryCount.get();
	}

	private Optional<TryCount> inputTryCountHandlingException () {
		try {
			return Optional.of(new TryCount(inputView.inputTrialCount()));
		} catch (IllegalArgumentException e) {
			outputView.printError(e.getMessage());
			return Optional.empty();
		}
	}
}
