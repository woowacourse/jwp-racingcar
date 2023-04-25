package racingcar.view;

import racingcar.game.dto.GameResponseDTO;

public class OutputView {
    
    
    private static final String PRINT_READ_CAR_NAMES_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String PRINT_READ_TRY_NUM_MESSAGE = "시도할 회수는 몇회인가요?";
    private static final String PRINT_RACING_RESULT_MESSAGE = "\n실행 결과";
    private static final String PRINT_WINNERS_MESSAGE = "가 최종 우승했습니다.";
    
    public void printCarPositionResult(final GameResponseDTO gameResponseDTO) {
        System.out.println(PRINT_RACING_RESULT_MESSAGE);
        gameResponseDTO.getRacingCars()
                .forEach(car -> System.out.println(car.getName().getValue() + " : " + car.getPosition().getValue()));
        System.out.println(gameResponseDTO.getWinners() + PRINT_WINNERS_MESSAGE);
    }
}
