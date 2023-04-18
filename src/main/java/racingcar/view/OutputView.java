package racingcar.view;

import java.util.List;
import racingcar.dto.PlayerDto;

public class OutputView {

    private static final String RESULT_MESSAGE = "실행결과";
    private static final String CAR_NAME_FORMAT = " : ";
    private static final String MOVEMENT = "-";
    private static final String COUNT_MESSAGE = "시도할 회수는 몇회인가요?";
    private static final String START_INPUT_CAR_NAME_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String END_MESSAGE = "가 최종 우승했습니다.";

    public void printStatus(List<PlayerDto> playerDtos) {
        for (PlayerDto playerDto : playerDtos) {
            printStatusOfResult(playerDto);
        }
    }

    private void printStatusOfResult(PlayerDto playerDto) {
        String carName = playerDto.getName();
        int position = playerDto.getPosition();
        System.out.print(carName + CAR_NAME_FORMAT);
        System.out.println(MOVEMENT.repeat(position));
    }

    public void resultMessage() {
        System.out.println(RESULT_MESSAGE);
    }

    public void printStartMessage() {
        System.out.println(START_INPUT_CAR_NAME_MESSAGE);
    }

    public void printCountMessage() {
        System.out.println(COUNT_MESSAGE);
    }

    public void printWinners(String winners) {
        System.out.println(winners + END_MESSAGE);
    }
}
