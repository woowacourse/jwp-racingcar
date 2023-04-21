package racingcar.dao;

import racingcar.entity.CarEntity;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCarDao implements CarDao {

    private final List<CarEntity> inMemory = new ArrayList<>();

    @Override
    public Long insert(CarEntity carEntity) {
        inMemory.add(carEntity);
        // TODO: 아이디 반환이 필요 없는데 굳이 맵으로 변환해서 까지  id 반환을 해야할까?
        return 0L;
    }

    @Override
    public List<CarEntity> findByGameResultId(Long gameResultId) {
        return inMemory;
    }
}
