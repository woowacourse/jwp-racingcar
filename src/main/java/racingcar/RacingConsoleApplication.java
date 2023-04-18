package racingcar;

import java.util.Scanner;
import racingcar.controller.console.RacingConsoleController;
import racingcar.dao.RacingConsoleDao;
import racingcar.service.RacingService;
import racingcar.view.inputview.KoreanInputView;
import racingcar.view.outputview.KoreanOutputView;

public class RacingConsoleApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KoreanInputView koreanInputView = new KoreanInputView(scanner);
        KoreanOutputView koreanOutputView = new KoreanOutputView();

        RacingService racingService = new RacingService(new RacingConsoleDao());

        RacingConsoleController racingConsoleController = new RacingConsoleController(koreanInputView, koreanOutputView, racingService);

        racingConsoleController.start();
    }
}
