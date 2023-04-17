package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;

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

    public List<ResponseDto> findAllRecords() {
        final List<RacingCarResult> racingCarResults = findRecordService.findAllRecords();
        return racingCarResults.stream()
            .map(racingCarResult -> new ResponseDto(racingCarResult.getWinners(), racingCarResult.getCars()))
            .collect(Collectors.toList());
    }

    public ResponseDto raceCar(final RequestDto requestDto) {
        final List<String> names = List.of(requestDto.getNames().split(","));
        final int count = requestDto.getCount();

        final RacingCarResult racingCarResult = playRacingCarService.playRacingCar(names, count);
        saveRacingCarResultService.saveRacingCarResult(racingCarResult);

        return new ResponseDto(racingCarResult.getWinners(), racingCarResult.getCars());
    }
}
