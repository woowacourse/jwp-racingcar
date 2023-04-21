package racingcar.controller;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import racingcar.domain.car.Cars;
import racingcar.domain.racinggame.RacingGame;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;
import racingcar.service.RacingCarService;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@WebMvcTest(WebRacingCarController.class)
class WebRacingCarControllerTest {
    @MockBean
    private RacingCarService racingCarService;
    
    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.standaloneSetup(new WebRacingCarController(racingCarService));
    }
    
    @Test
    void 이름과_시도횟수를_전달하면_게임_결과를_반환한다() {
        GameRequestDto gameRequestDto = new GameRequestDto("아벨,스플릿,포비", "12");
        final RacingGame racingGame = new RacingGame("아벨,스플릿,포비", 0);
        final Cars cars = racingGame.getCars();
        GameResponseDto gameResponseDto = new GameResponseDto(cars.getWinners(), cars.getCars());
        
        given(racingCarService.playGame(any(), any())).willReturn(gameResponseDto);
        
        RestAssuredMockMvc.given().log().all()
                .contentType(ContentType.JSON)
                .body(gameRequestDto)
                .when().post("/plays")
                .then().log().all()
                .status(HttpStatus.OK)
                .contentType(ContentType.JSON)
                .body("winners", is("아벨, 스플릿, 포비"))
                .body("racingCars.name", contains("아벨", "스플릿", "포비"));
    }
    
    @Test
    void 진행했던_모든_게임의_결과를_반환한다() {
        final RacingGame firstRacingGame = new RacingGame("아벨,스플릿", 20);
        final Cars firstCars = firstRacingGame.getCars();
        GameResponseDto firstResult = new GameResponseDto(firstCars.getWinners(), firstCars.getCars());
        
        final RacingGame secondRacingGame = new RacingGame("아벨,스플릿,포비", 20);
        final Cars secondCars = secondRacingGame.getCars();
        GameResponseDto secondResult = new GameResponseDto(secondCars.getWinners(), secondCars.getCars());
        List<GameResponseDto> gameResponseDtos = List.of(firstResult, secondResult);
        
        given(racingCarService.findAllGameResult()).willReturn(gameResponseDtos);
        
        RestAssuredMockMvc.given().log().all()
                .when().get("/plays")
                .then().log().all()
                .status(HttpStatus.OK)
                .contentType(ContentType.JSON)
                .body("size()", is(2))
                .body("[0].winners", is("아벨, 스플릿"))
                .body("[1].winners", is("아벨, 스플릿, 포비"))
                .body("[0].racingCars.name", contains("아벨", "스플릿"))
                .body("[1].racingCars.name", contains("아벨", "스플릿", "포비"));
    }
}
