//package racingcar.service;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import racingcar.dao.GameDao;
//import racingcar.dao.PlayerResultDao;
//import racingcar.domain.Car;
//import racingcar.domain.TestNumberGenerator;
//import racingcar.dto.request.GameSaveDto;
//import racingcar.dto.request.PlayerResultSaveDto;
//import racingcar.entity.Game;
//import racingcar.entity.PlayerResult;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//
//@ExtendWith(MockitoExtension.class)
//public class GameServiceTest {
//
//    @Mock
//    GameDao gameDao;
//
//    @Mock
//    PlayerResultDao playerResultDao;
//
//    @InjectMocks
//    GameService gameService;
//
//    @Test
//    public void playGameTest() {
//        GameSaveDto gameSaveDto = new GameSaveDto("ditoo", 10);
//        Game game = new Game(1, gameSaveDto);
//        given(gameDao.createGame(any()))
//                .willReturn(game);
//
//        Car ditooCar = new Car("ditoo");
//        PlayerResult ditooResult = getDitooResult(ditooCar);
//
//        Car leoCar = new Car("leo");
//        PlayerResult leoResult = getLeoResult(leoCar);
//
//        given(playerResultDao.savePlayerResult(any()))
//                .willReturn(ditooResult);
//        given(playerResultDao.savePlayerResult(any()))
//                .willReturn(leoResult);
//
////        GamePlayDto gamePlayDto = GamePlayDto.of(List.of("ditoo"), 2, List.of(ditooCar, leoCar));
////
////        GameResponseDto gameResponseDto = gameService.playGame(gamePlayDto);
////        assertThat(gameResponseDto.getWinners()).isEqualTo(gamePlayDto.getWinners());
//    }
//
//    private PlayerResult getDitooResult(Car ditooCar) {
//        ditooCar.move(new TestNumberGenerator(7).generate());
//        ditooCar.move(new TestNumberGenerator(7).generate());
//        PlayerResultSaveDto ditoo = new PlayerResultSaveDto(1, ditooCar);
//        return new PlayerResult(1, ditoo);
//    }
//
//    private PlayerResult getLeoResult(Car leoCar) {
//        leoCar.move(new TestNumberGenerator(7).generate());
//        PlayerResultSaveDto leo = new PlayerResultSaveDto(1, leoCar);
//        return new PlayerResult(1, leo);
//    }
//}
