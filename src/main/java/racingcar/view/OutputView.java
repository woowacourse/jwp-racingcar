package racingcar.view;

import racingcar.dto.CarStatusResponseDto;
import racingcar.dto.GameResultResponseDto;

public final class OutputView {

	public void printError (final String message) {
		System.out.println(message);
	}

	public void printResult (final GameResultResponseDto gameResultResponseDto) {
		System.out.println("승자: " + String.join(",", gameResultResponseDto.getWinners()));
		for (final CarStatusResponseDto carStatusResponseDto : gameResultResponseDto.getRacingCars()) {
			System.out.println("이름: " + carStatusResponseDto.getName() + " 위치: " + carStatusResponseDto.getPosition());
		}
	}
}
