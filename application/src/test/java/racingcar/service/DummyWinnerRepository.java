package racingcar.service;

import racingcar.domain.Winners;
import racingcar.repository.WinnerRepository;

public class DummyWinnerRepository implements WinnerRepository {

    @Override
    public void save(final Winners winners) {
        // do nothing
    }
}
