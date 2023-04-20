package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.domain.Car;
import racingcar.domain.Race;
import racingcar.dto.PlayRequest;
import racingcar.dto.PlayResponse;
import racingcar.entity.Game;
import racingcar.entity.Player;
import racingcar.utils.NumberGenerator;

@Service
public class RacingCarService {

    private final GameDao gameDao;
    private final PlayerDao playerDao;
    private final NumberGenerator numberGenerator;

    public RacingCarService(GameDao gameDao, PlayerDao playerDao, NumberGenerator numberGenerator) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
        this.numberGenerator = numberGenerator;
    }

    public PlayResponse play(PlayRequest playRequest) {
        Race race = new Race(playRequest.getCount(), playRequest.getNames(), numberGenerator);
        while (!race.isFinished()) {
            race.playRound();
        }

        List<Car> winners = race.findWinners();
        List<Car> participants = race.getParticipants();

        Game game = Game.of(winners, playRequest.getCount());
        Long gameId = gameDao.insert(game);

        List<Player> players = participants.stream()
                .map(participant -> Player.of(participant, gameId.intValue()))
                .collect(Collectors.toList());
        playerDao.insert(players);

        return PlayResponse.of(game.getWinners(), players);
    }

    public List<PlayResponse> findHistory() {
        List<Game> games = gameDao.findAll();
        List<Player> players = playerDao.findAll();

        List<PlayResponse> responses = new ArrayList<>();
        for (Game game : games) {
            List<Player> gamePlayers = players.stream()
                    .filter(player -> player.getGameId() == game.getId())
                    .collect(Collectors.toList());
            responses.add(PlayResponse.of(game.getWinners(), gamePlayers));
        }

        return responses;
    }
}
