package racingcar.dao;

import java.util.List;
import java.util.stream.Collectors;

import racingcar.domain.RacingGameInfo;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarsDto;
import racingcar.dto.TryCountDto;
import racingcar.dto.WinnersDto;

public class RacingCarInMemoryDao implements RacingCarDao {
    RacingGameInfo racingGameInfo = new RacingGameInfo();

    @Override
    public void insertGame(RacingCarsDto racingCarsDto, TryCountDto tryCountDto) {
        racingGameInfo.setWinners(racingCarsDto.getWinnerNames());
        racingGameInfo.setRacingCars(racingCarsDto.getRacingCarDtos()
            .stream()
            .map(racingCarDto -> new RacingCarDto(racingCarDto.getName(), racingCarDto.getPosition()))
            .collect(Collectors.toList()));
        racingGameInfo.setCount(tryCountDto.getTryCount());
    }

    @Override
    public List<WinnersDto> selectWinners() {
        return List.of(new WinnersDto(0, racingGameInfo.getWinners()));
    }

    @Override
    public List<RacingCarDto> selectRace(int gameId) {
        return racingGameInfo.getRacingCars();
    }
}
