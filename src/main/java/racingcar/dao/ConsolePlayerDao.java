package racingcar.dao;

import racingcar.entity.PlayerEntity;

import java.util.List;

public class ConsolePlayerDao implements PlayerDao{
    private List<PlayerEntity> players;

    @Override
    public void saveAll(final List<PlayerEntity> players) {
        this.players = players;
    }

    @Override
    public List<PlayerEntity> findAll() {
        return players;
    }
}
