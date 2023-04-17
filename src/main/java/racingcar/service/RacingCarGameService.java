package racingcar.service;

import racingcar.dto.CarDto;

import java.util.List;

public interface RacingCarGameService {


    int play(String carNames, int count);

    List<CarDto> getCars(int gameId);

    List<String> getWinners(int gameId);

    List<Integer> getAllGameIds();
}
