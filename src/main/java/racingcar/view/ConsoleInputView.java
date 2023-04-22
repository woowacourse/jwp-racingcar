package racingcar.view;

import racingcar.dto.RacingInfoRequestDto;

import java.util.Scanner;

public class ConsoleInputView {
    public static final String ASKING_CAR_NAMES_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,)를 기준으로 구분).";
    public static final String ASKING_TRIAL_MESSAGE = "시도할 횟수는 몇회인가요?";
    private static final Scanner scanner = new Scanner(System.in);

    public static RacingInfoRequestDto getRacingInfoRequest() {
        System.out.println(ASKING_CAR_NAMES_MESSAGE);
        String names = scanner.nextLine();
        System.out.println(ASKING_TRIAL_MESSAGE);
        int count = Integer.parseInt(scanner.nextLine());

        return new RacingInfoRequestDto(names, count);
    }

    public static ApplicationCommand getApplicationCommand() {
        System.out.println("어떤 애플리케이션으로 실행할까요? (콘솔 : C, 웹 : W)");
        return ApplicationCommand.getByCommand(scanner.nextLine());
    }

    public static FindingCommand getFindingCommand() {
        System.out.println("모든 결과를 조회해 보시겠습니까? (예 : Y, 아니오 : N)");
        return FindingCommand.getByCommand(scanner.nextLine());
    }

    public static ReplayCommand getReplayCommand() {
        System.out.println("게임을 또 플레이하시겠습니까? (예 : Y, 아니오 : N)");
        return ReplayCommand.getByCommand(scanner.nextLine());
    }
}
