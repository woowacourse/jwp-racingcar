package racingcar.services;

import java.util.Iterator;
import java.util.List;

import racingcar.model.manager.CarMoveManager;

public class TestMoveManager implements CarMoveManager {
    private final Iterator<Boolean> booleans;

    public TestMoveManager(List<Boolean> booleans) {
        this.booleans = booleans.iterator();
    }

    @Override
    public boolean isMove(int number) {
        return booleans.next();
    }
}
