package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.controller.dto.PlaysRequest;
import racingcar.controller.dto.PlaysResponse;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.domain.Car;
import racingcar.domain.Race;
import racingcar.entity.Game;
import racingcar.entity.Player;
import racingcar.utils.NumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public PlaysResponse play(PlaysRequest playsRequest) {
        Race race = new Race(playsRequest.getCount(), playsRequest.getNames(), numberGenerator);
        while (!race.isFinished()) {
            race.playRound();
        }

        List<Car> winners = race.findWinners();
        List<Car> participants = race.getParticipants();
        List<String> winnerNames = winners.stream()
                .map(Car::getName)
                .collect(Collectors.toList());

        Game game = Game.of(null, winnerNames, playsRequest.getCount());
        Long gameId = gameDao.insert(game);

        List<Player> players = participants.stream()
                .map(participant -> Player.of(participant, gameId.intValue()))
                .collect(Collectors.toList());
        playerDao.insert(players);

        return PlaysResponse.of(winners, participants);
    }

    public List<PlaysResponse> getGamesAll() {
        //TODO: request -> entity -> dao -> service -> response -> controller
        List<PlaysResponse> playsResponses = new ArrayList<>();

        List<Game> games = gameDao.selectAll();
        for (Game game : games) {
            List<Player> players = playerDao.selectAllByGameId(game.getId());
            String winners = game.getWinners();
            playsResponses.add(PlaysResponse.of(winners, players));
        }

        return playsResponses;
    }
}
