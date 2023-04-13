package racingcar.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
        Race race = createRace(playsRequest);
        while (!race.isFinished()) {
            race.playRound();
        }

        List<Car> winners = race.findWinners();
        List<Car> participants = race.getParticipants();

        Game game = Game.of(winners, playsRequest.getCount());
        Long gameId = gameDao.insert(game);

        List<Player> players = participants.stream()
                .map(participant -> Player.of(participant, gameId.intValue()))
                .collect(Collectors.toList());
        playerDao.insert(players);

        return PlaysResponse.of(game.getWinners(), participants);
    }

    private Race createRace(PlaysRequest playsRequest) {
        String delimiter = ",";
        List<String> carNames = Arrays.stream(playsRequest.getNames().split(delimiter, -1)).map(String::strip)
                .collect(Collectors.toList());

        return new Race(playsRequest.getCount(), carNames, numberGenerator);
    }
}
