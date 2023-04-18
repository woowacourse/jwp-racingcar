package racing.view;

import java.util.List;
import racing.dto.CarDto;

public class OutputView {

    public void printFinalResult(List<CarDto> carDtos) {
        StringBuilder content = new StringBuilder();
        carDtos.forEach(car -> addCarResult(content, car));
        System.out.println(content);
    }

    private void addCarResult(final StringBuilder roundResult, CarDto carDto) {
        final String carResult = carDto.getName() + " : " + convertDistance(carDto.getPosition());
        roundResult.append(carResult).append(System.lineSeparator());
    }

    private String convertDistance(final int distance) {
        final String sign = "-";
        return sign.repeat(distance);
    }

    public void printWinners(final String winners) {
        System.out.println(winners + "가 최종 우승했습니다.");
    }
}
