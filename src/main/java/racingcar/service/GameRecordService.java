package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.CarDto;
import racingcar.dto.RecordDto;
import racingcar.model.Cars;
import racingcar.model.RecordEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GameRecordService {
    private final GameService gameService;
    private final RecordService recordService;

    public GameRecordService(final GameService gameService, final RecordService recordService) {
        this.gameService = gameService;
        this.recordService = recordService;
    }

    @Transactional
    public void saveGameRecord(final int count, final Cars cars) {
        long gameId = gameService.save(count);
        recordService.save(gameId, cars);
    }

    public List<RecordDto> findRecords() {
        List<RecordEntity> records = recordService.findAll();

        Map<Integer, List<RecordEntity>> groupingRecords = records.stream()
                .collect(Collectors.groupingBy(RecordEntity::getGameId));

        return groupingRecords.entrySet()
                .stream()
                .map(this::findRecord)
                .collect(Collectors.toList());
    }

    private RecordDto findRecord(Map.Entry<Integer, List<RecordEntity>> records) {
        return new RecordDto(recordsToWinner(records), recordsToDto(records));
    }

    private String recordsToWinner(Map.Entry<Integer, List<RecordEntity>> records) {
        return records.getValue()
                .stream()
                .filter(RecordEntity::isWinner)
                .map(RecordEntity::getPlayerName)
                .collect(Collectors.joining(","));
    }

    private List<CarDto> recordsToDto(Map.Entry<Integer, List<RecordEntity>> records) {
        return records.getValue()
                .stream()
                .map(record -> new CarDto(record.getPlayerName(), record.getPosition()))
                .collect(Collectors.toList());
    }
}
