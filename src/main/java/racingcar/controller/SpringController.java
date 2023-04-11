package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Car;
import racingcar.dto.GameInputDto;

import java.net.URI;

@RestController
@RequestMapping("/http-method")
public class SpringController {
    @PostMapping("/car")
    public ResponseEntity createCar(@RequestBody Car car){
        Long id = 1L;
        return ResponseEntity.created(URI.create("/car/"+id)).build();
    }

    @PostMapping("/racingGame")
    public ResponseEntity postInput(@RequestBody GameInputDto gameInputDto){
        Long id = 1L;
        System.out.println(gameInputDto);

        return ResponseEntity.created(URI.create("/racingGame/"+id)).build();
    }
}
