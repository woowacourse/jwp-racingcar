package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.PlayRequestDto;
import racingcar.dto.PlayResultResponseDto;
import racingcar.service.RacingGameService;

import javax.validation.Valid;

@RestController
public class RacingGameController {
    @Autowired
    RacingGameService racingGameService;


    @PostMapping(value = "/plays", consumes = "application/json")
    public PlayResultResponseDto play(@Valid @RequestBody PlayRequestDto playRequestDto) {
        String names = playRequestDto.getNames();
        Integer count = playRequestDto.getCount();

        return racingGameService.run(names, count);
    }
}
