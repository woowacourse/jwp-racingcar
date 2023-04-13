package racingcar.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RequestDTO;
import racingcar.dto.ResponseDTO;
import racingcar.service.RacingCarService;

@RestController
public class WebController {
    private static final String DELIMITER = ",";

    private final RacingCarService racingCarService;

    public WebController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity createGame(@RequestBody RequestDTO requestDTO) {
        List<String> carNames = Arrays.stream(requestDTO.getNames().split(DELIMITER)).collect(Collectors.toList());
        ResponseDTO responseDTO = racingCarService.play(carNames, requestDTO.getCount());
        return ResponseEntity.ok().body(responseDTO);
    }
}
