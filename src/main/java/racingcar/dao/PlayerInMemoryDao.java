package racingcar.dao;

import java.util.ArrayList;
import java.util.List;
import racingcar.entity.Player;

public class PlayerInMemoryDao implements PlayerDao {

    private final List<Player> players;

    public PlayerInMemoryDao() {
        players = new ArrayList<>();
    }

    @Override
    public void insert(List<Player> players) {
        this.players.addAll(players);
    }

    @Override
    public List<Player> findAll() {
        return new ArrayList<>(players);
    }
}
