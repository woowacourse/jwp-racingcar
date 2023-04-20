package racingcar.game.service;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.car.interfaces.Car;
import racingcar.car.interfaces.CarDAO;
import racingcar.game.dto.GameRequestDTO;
import racingcar.game.dto.GameResponseDTO;
import racingcar.game.interfaces.GameDAO;
import racingcar.game.interfaces.GameResult;
import racingcar.game.interfaces.GameService;
import racingcar.game.model.FixedNumberGenerator;

class RacingCarGameServiceTest {
    
    static class FakeCarDAO implements CarDAO {
        
        @Override
        public void insert(final Car car, final int gameId) {
        
        }
        
        @Override
        public void insertAll(final List<Car> cars, final int gameId) {
        
        }
        
        @Override
        public Car findByName(final String name, final int gameId) {
            return null;
        }
        
        @Override
        public List<Car> findAll(final int gameId) {
            return null;
        }
    }
    
    static class FakeGameDAO implements GameDAO {
        
        @Override
        public int insert(final int count, final GameResult gameResult) {
            return 0;
        }
        
        @Override
        public GameResult find(final int gameId) {
            return null;
        }
        
        @Override
        public List<GameResult> findAll() {
            return null;
        }
    }
    
    @Test
    @DisplayName("RacingCarGameService create 테스트")
    void create() {
        //given
        final CarDAO fakeCarDAO = new FakeCarDAO();
        final GameDAO fakeGameDAO = new FakeGameDAO();
        final GameService service = new RacingCarGameService(new FixedNumberGenerator(), fakeGameDAO, fakeCarDAO);
        final GameRequestDTO gameRequestDTO = GameRequestDTO.create("echo,io", 1);
        
        //when
        final GameResponseDTO responseDTO = service.createGame(gameRequestDTO);
        
        //then
        Assertions.assertEquals("echo,io", responseDTO.getWinners());
        Assertions.assertEquals(2, responseDTO.getRacingCars().size());
    }
}