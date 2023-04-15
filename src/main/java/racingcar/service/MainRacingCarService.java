package racingcar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;

@Service
public class MainRacingCarService {

    private final PlayRacingCarService playRacingCarService;
    private final SaveRacingCarResultService saveRacingCarResultService;

    @Autowired
    public MainRacingCarService(final PlayRacingCarService playRacingCarService,
                                final SaveRacingCarResultService saveRacingCarResultService) {
        this.playRacingCarService = playRacingCarService;
        this.saveRacingCarResultService = saveRacingCarResultService;
    }

    public ResponseDto raceCar(final RequestDto requestDto) {
        final List<String> names = List.of(requestDto.getNames().split(","));
        final int count = requestDto.getCount();

        final RacingCarResult racingCarResult = playRacingCarService.playRacingCar(names, count);
        saveRacingCarResultService.saveRacingCarResult(racingCarResult);

        return new ResponseDto(racingCarResult.getWinners(), racingCarResult.getCars());
    }
}
