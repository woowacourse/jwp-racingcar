package racingcar.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingGameRequestDTO;
import racingcar.dto.RacingGameResponseDTO;
import racingcar.service.RacingCarService;

@RestController
public class WebController {
    private static final String DELIMITER = ",";

    private final RacingCarService racingCarService;

    public WebController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingGameResponseDTO> createGame(@RequestBody RacingGameRequestDTO racingGameRequestDTO) {
        final List<String> carNames = Arrays.stream(racingGameRequestDTO.getNames().split(DELIMITER)).collect(Collectors.toList());
        final RacingGameResponseDTO racingGameResponseDTO = racingCarService.play(carNames, racingGameRequestDTO.getCount());
        return ResponseEntity.ok().body(racingGameResponseDTO);
    }
}
