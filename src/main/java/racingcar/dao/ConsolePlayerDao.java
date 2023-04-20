package racingcar.dao;

import racingcar.entity.Player;

import java.util.List;

public class ConsolePlayerDao implements PlayerDao{
    private List<Player> players;

    @Override
    public void saveAll(final List<Player> players) {
        this.players = players;
    }

    @Override
    public List<Player> findAll() {
        return players;
    }
}
