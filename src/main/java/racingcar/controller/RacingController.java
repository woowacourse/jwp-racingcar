package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.dto.RacingInfoRequestDto;
import racingcar.dto.RacingResultDto;
import racingcar.service.RacingService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class RacingController {
    private final RacingService racingService;

    public RacingController(RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping("/plays")
    public ResponseEntity<Void> playRacing(@RequestBody RacingInfoRequestDto dto) {
        int raceId = racingService.race(dto.getNames(), dto.getCount());
        return ResponseEntity
                .created(URI.create(String.format("/plays/%d", raceId)))
                .build();
    }

    @GetMapping("/plays/{raceId}")
    public ResponseEntity<RacingResultDto> findRaceById(@PathVariable int raceId) {
        Optional<RacingResultDto> resultDto = racingService.findRaceById(raceId);
        if (resultDto.isEmpty()) {
            throw new IllegalArgumentException("레이싱 정보를 식별할 수 없는 id 값입니다.");
        }
        return ResponseEntity
                .ok()
                .body(resultDto.get());
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingResultDto>> findAllRaceResults() {
        return ResponseEntity
                .ok()
                .body(racingService.findAllResults());
    }
}
