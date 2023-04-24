package racingcar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.RacingGameDto;
import racingcar.dto.ResultDto;
import racingcar.repository.RacingCarRepository;

@Service
public class RacingGameService {
	RacingCarRepository racingCarRepository;

	public RacingGameService(RacingCarRepository racingCarRepository) {
		this.racingCarRepository = racingCarRepository;
	}

	public Cars save(RacingGameDto racingGameDto) {
		String carNames = racingGameDto.getNames();
		int count = Integer.parseInt(racingGameDto.getCount());

		RacingGame racingGame = new RacingGame(carNames);
		racingGame.startRacing(count);
		Cars cars = racingGame.getCars();
		racingCarRepository.save(cars, count);

		return cars;
	}

	public List<ResultDto> find() {
		return racingCarRepository.find();
	}
}
