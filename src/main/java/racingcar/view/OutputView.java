package racingcar.view;

import java.util.List;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarGameResultDto;

public class OutputView {
    public void printGameResult(RacingCarGameResultDto racingCarGameResultDto) {
        System.out.println(System.lineSeparator() + "실행 결과");
        System.out.println(racingCarGameResultDto.getWinners()
                + "가 최종 우승했습니다."
                + System.lineSeparator());
        List<RacingCarDto> racingCars = racingCarGameResultDto.getRacingCars();
        for (RacingCarDto racingCarDto : racingCars) {
            System.out.println(racingCarDto.getName() + " : " + racingCarDto.getPosition());
        }
    }
}
