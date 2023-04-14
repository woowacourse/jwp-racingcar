package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.RacingCarGameDao;
import racingcar.domain.Cars;
import racingcar.domain.Count;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.ResultResponseDto;
import racingcar.repository.RacingCarRepository;
import racingcar.service.RacingCarService;
import racingcar.util.RandomNumberGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RacingCarWebController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/plays")
    public ResponseEntity<ResultResponseDto> play(@RequestBody RacingGameRequestDto racingGameRequestDto) {
        List<String> names = Arrays.stream(racingGameRequestDto.getNames().split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        RacingCarService racingCarService = new RacingCarService(
                Cars.of(names),
                new RandomNumberGenerator(),
                new RacingCarRepository(new RacingCarGameDao(jdbcTemplate))
        );
        racingCarService.play(Count.of(racingGameRequestDto.getCount()));

        ResultResponseDto resultResponseDto = new ResultResponseDto(racingCarService.getWinners(), racingCarService.getCars());
        return ResponseEntity.ok(resultResponseDto);
    }
}
