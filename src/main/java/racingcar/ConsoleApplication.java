package racingcar;

import org.springframework.http.ResponseEntity;
import racingcar.controller.RacingCarController;
import racingcar.dao.InMemoryPlayResultDAO;
import racingcar.dao.InMemoryPlayerInfoDao;
import racingcar.dto.CarDto;
import racingcar.dto.MoveRequestDto;
import racingcar.dto.MoveResponseDto;
import racingcar.dto.PlayRequestDto;
import racingcar.dto.PlayResponseDto;
import racingcar.genertor.RandomNumberGenerator;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleApplication {
    private static final RacingCarService racingCarService = new RacingCarService(
            new InMemoryPlayResultDAO(),
            new InMemoryPlayerInfoDao(),
            new RandomNumberGenerator()
    );
    private static final RacingCarController racingCarController = new RacingCarController(racingCarService);

    public static void main(String[] args) {
        final PlayRequestDto playRequestDto = readPlayRequest();
        final ResponseEntity<PlayResponseDto> playResponse = racingCarController.play(playRequestDto);
        OutputView.printWinner(playResponse.getBody());
    }

    private static PlayRequestDto readPlayRequest() {
        OutputView.printInputCarNamesNotice();
        final String names = InputView.inputCarNames();

        OutputView.printInputTryTimesNotice();
        final int tryTime = InputView.inputTryTimes();

        return new PlayRequestDto(names, tryTime);
    }
}
