package racingcar.service;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import racingcar.domain.Car;
import racingcar.domain.dto.RacingCarResultDto;
import racingcar.repository.dao.FindAllResultsDao;
import racingcar.repository.dto.ResultDto;

@Service
public class FindRacingCarResultService {

    private final FindAllResultsDao findAllResultsDao;

    public FindRacingCarResultService(final FindAllResultsDao findAllResultsDao) {
        this.findAllResultsDao = findAllResultsDao;
    }

    public List<RacingCarResultDto> findAll() {
        final List<ResultDto> resultDtos = findAllResultsDao.findAll();
        final Map<Long, List<ResultDto>> recordsByGameId = resultDtos.stream()
            .collect(groupingBy(ResultDto::getGameId));
        return recordsByGameId.values().stream()
            .map(this::toRacingCarResult)
            .collect(toList());
    }

    private RacingCarResultDto toRacingCarResult(final List<ResultDto> resultDtos) {
        final List<String> winners = resultDtos.stream()
            .filter(ResultDto::isWinner)
            .map(ResultDto::getName)
            .collect(toList());
        final List<Car> cars = resultDtos.stream()
            .map(resultDto -> Car.of(resultDto.getName(), resultDto.getPosition()))
            .collect(toList());
        return new RacingCarResultDto(winners, cars);
    }
}
