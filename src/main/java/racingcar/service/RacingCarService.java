package racingcar.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.PlayerInfoDAO;
import racingcar.dao.PlayerResultDAO;
import racingcar.domain.CarFactory;
import racingcar.domain.Cars;
import racingcar.dto.CarDto;
import racingcar.dto.MoveRequestDto;
import racingcar.dto.MoveResponseDto;
import racingcar.dto.PlayResponseDto;
import racingcar.entity.PlayerResultEntity;
import racingcar.genertor.NumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingCarService {

    private final PlayerResultDAO playerResultDAO;
    private final PlayerInfoDAO playerInfoDAO;
    private final NumberGenerator randomNumberGenerator;

    public RacingCarService(PlayerResultDAO playerResultDAO, PlayerInfoDAO playerInfoDAO, NumberGenerator numberGenerator) {
        this.playerResultDAO = playerResultDAO;
        this.playerInfoDAO = playerInfoDAO;
        this.randomNumberGenerator = numberGenerator;
    }

    public MoveResponseDto moveCar(MoveRequestDto moveRequestDto) {
        Cars cars = new Cars(CarFactory.buildCars(moveRequestDto.getNames()));
        play(cars, moveRequestDto.getCount(), randomNumberGenerator);
        saveCarResult(cars, moveRequestDto.getCount());
        return new MoveResponseDto(cars.findWinners(), cars.getCars());
    }

    @Transactional
    public void saveCarResult(Cars cars, int trialCount) {
        int tableId = playerResultDAO.returnTableIdAfterInsert(trialCount, cars.findWinners());
        playerInfoDAO.insert(tableId, cars.getCars());
    }

    public void play(Cars cars, int count, NumberGenerator numberGenerator) {
        while (count-- > 0) {
            cars.moveCars(numberGenerator);
        }
    }

    @Transactional
    public List<PlayResponseDto> findAllGameHistory() {
        final ArrayList<PlayResponseDto> playResponseDtos = new ArrayList<>();
        for (PlayerResultEntity playerResultEntity : playerResultDAO.findAll()) {
            playResponseDtos.add(new PlayResponseDto(playerResultEntity.getWinners(), makeCarDto(playerResultEntity)));
        }
        return playResponseDtos;
    }

    private List<CarDto> makeCarDto(final PlayerResultEntity playerResultEntity) {
        return playerInfoDAO.findPlayerByResultId(playerResultEntity.getId()).stream()
                .map((playerInfo) -> new CarDto(playerInfo.getName(), playerInfo.getPosition()))
                .collect(Collectors.toList());
    }
}
