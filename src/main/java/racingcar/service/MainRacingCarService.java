package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import racingcar.dto.NamesAndCountDto;
import racingcar.dto.WinnersAndCarsDto;

@Service
public class MainRacingCarService {

    private final PlayRacingCarService playRacingCarService;
    private final FindRecordService findRecordService;
    private final SaveRacingCarResultService saveRacingCarResultService;

    public MainRacingCarService(final PlayRacingCarService playRacingCarService,
        final FindRecordService findRecordService,
        final SaveRacingCarResultService saveRacingCarResultService) {
        this.playRacingCarService = playRacingCarService;
        this.findRecordService = findRecordService;
        this.saveRacingCarResultService = saveRacingCarResultService;
    }

    public List<WinnersAndCarsDto> findAllRecords() {
        final List<RacingCarResult> racingCarResults = findRecordService.findAllRecords();
        return racingCarResults.stream()
            .map(WinnersAndCarsDto::from)
            .collect(Collectors.toList());
    }

    public WinnersAndCarsDto raceCar(final NamesAndCountDto namesAndCountDto) {
        final List<String> names = List.of(namesAndCountDto.getNames().split(","));
        final int count = namesAndCountDto.getCount();

        final RacingCarResult racingCarResult = playRacingCarService.playRacingCar(names, count);
        saveRacingCarResultService.saveRacingCarResult(racingCarResult);

        return WinnersAndCarsDto.from(racingCarResult);
    }
}
