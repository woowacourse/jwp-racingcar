package view;

import java.util.List;

import racingcar.dto.CarDto;

public class OutputView {

	public void printResult(String winners, List<CarDto> cars) {
		printWinners(winners);
		printHistory(cars);
	}

	private void printWinners(String winners) {
		String TELL_FINAL_WINNER = "\n우승자:\n";
		String result = TELL_FINAL_WINNER + winners;
		System.out.println(result);
	}

	private void printHistory(List<CarDto> cars){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n결과:\n");
		for(CarDto car : cars){
			stringBuilder.append("Name: ").append(car.getName());
			stringBuilder.append(", Position: ").append(car.getPosition()).append("\n");
		}
		System.out.println(stringBuilder);
	}
}
