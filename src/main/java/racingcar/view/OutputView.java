package racingcar.view;

import racingcar.dto.PlayerDto;
import racingcar.dto.ResultResponseDto;

import java.util.List;

public class OutputView {

    private static final String CAR_NAME_FORMAT = " : ";
    private static final String MOVEMENT = "-";
    private static final String COUNT_MESSAGE = "시도할 회수는 몇회인가요?";
    private static final String START_INPUT_CAR_NAME_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String END_MESSAGE = "가 최종 우승했습니다.";

    public void printStatus(ResultResponseDto resultResponseDto) {
        List<PlayerDto> playerDtos = resultResponseDto.getRacingCars();

        playerDtos.stream()
                .map(this::toPlayerStatus)
                .forEach(System.out::println);
    }

    public String toPlayerStatus(PlayerDto playerDto) {
        return playerDto.getName() +
                CAR_NAME_FORMAT +
                " " +
                MOVEMENT.repeat(playerDto.getPosition());
    }

    public void printStartMessage() {
        System.out.println(START_INPUT_CAR_NAME_MESSAGE);
    }

    public void printCountMessage() {
        System.out.println(COUNT_MESSAGE);
    }

    public void printWinners(ResultResponseDto resultResponseDto) {
        System.out.println(resultResponseDto.getWinners() + END_MESSAGE);
    }
}
