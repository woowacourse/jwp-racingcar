package racingcar.dao;

import racingcar.domain.Car;
import racingcar.entity.PlayResultEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryPlayResultDAO implements PlayResultDAO {

    private int id = 0;
    private List<PlayResultEntity> data = new ArrayList<>();

    @Override
    public int returnTableIdAfterInsert(final Integer count, final List<Car> winners) {
        data.add(new PlayResultEntity(++id, count, makeWinnersString(winners), new Timestamp(1)));
        return id;
    }

    private String makeWinnersString(List<Car> winners) {
        return winners.stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<PlayResultEntity> findAll() {
        return data;
    }
}
