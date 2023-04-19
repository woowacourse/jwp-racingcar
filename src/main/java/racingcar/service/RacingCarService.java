package racingcar.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.PlayerResultDAO;
import racingcar.dao.PlayersInfoDAO;
import racingcar.domain.CarFactory;
import racingcar.domain.Cars;
import racingcar.dto.MoveRequestDto;
import racingcar.dto.MoveResponseDto;
import racingcar.genertor.NumberGenerator;

@Service
public class RacingCarService {

    private final PlayerResultDAO playerResultDAO;
    private final PlayersInfoDAO playersInfoDAO;
    private final NumberGenerator randomNumberGenerator;

    public RacingCarService(PlayerResultDAO playerResultDAO, PlayersInfoDAO playersInfoDAO, NumberGenerator numberGenerator) {
        this.playerResultDAO = playerResultDAO;
        this.playersInfoDAO = playersInfoDAO;
        this.randomNumberGenerator = numberGenerator;
    }

    public MoveResponseDto moveCar(MoveRequestDto moveRequestDto){
        Cars cars = new Cars(CarFactory.buildCars(moveRequestDto.getNames()));
        play(cars, moveRequestDto.getCount(), randomNumberGenerator);
        saveCarResult(cars, moveRequestDto.getCount());
        return new MoveResponseDto(cars.findWinners(), cars.getCars());
    }

    @Transactional
    public void saveCarResult(Cars cars, int trialCount) {
        int tableId = playerResultDAO.returnTableIdAfterInsert(trialCount, cars.findWinners());
        playersInfoDAO.insert(tableId, cars.getCars());
    }

    public void play(Cars cars, int count, NumberGenerator numberGenerator) {
        while (count-- > 0) {
            cars.moveCars(numberGenerator);
        }
    }


}
