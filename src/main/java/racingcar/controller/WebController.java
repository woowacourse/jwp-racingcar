package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RequestDTO;
import racingcar.dto.ResponseDTO;
import racingcar.service.RacingCarService;

import javax.validation.Valid;

@RestController
public class WebController {

    private final RacingCarService racingCarService;

    public WebController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<ResponseDTO> createGameAndPlay(@Valid @RequestBody RequestDTO requestDTO) {
        ResponseDTO responseDTO = racingCarService.play(requestDTO.getNames(), requestDTO.getCount());
        return ResponseEntity.ok(responseDTO);
    }
}
