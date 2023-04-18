package racing.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import racing.util.NumberGenerator;

public class Game {

    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 1000000000;
    private final int totalCount;
    private Cars cars;
    private int currentCount = 0;

    public Game(final String totalCount) {
        validateIsNumber(totalCount);
        validateRange(totalCount);
        this.totalCount = Integer.parseInt(totalCount);
    }

    private void validateIsNumber(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("게임 횟수가 숫자가 아닙니다.");
        }
    }


    private void validateRange(final String input) {
        final int count = Integer.parseInt(input);
        if (MIN_COUNT > count || count > MAX_COUNT) {
            throw new IllegalArgumentException("횟수는 1에서 1000000000까지 입니다.");
        }
    }

    public void joinCars(final String carNames) {
        if (cars != null) {
            throw new IllegalStateException("이미 참가자들이 등록되어 있습니다.");
        }
        final List<String> names = Arrays.asList(carNames.split(",", -1));
        final long distinctSize = names.stream()
            .distinct()
            .count();
        if (distinctSize < names.size()) {
            throw new IllegalArgumentException("중복된 이름이 존재합니다.");
        }
        final List<Car> joinCars = names.stream().map(Car::new)
            .collect(Collectors.toList());
        this.cars = new Cars(joinCars);
    }

    public void playGame(NumberGenerator numberGenerator) {
        if (cars == null) {
            throw new IllegalStateException("게임을 플레이 하기위해 참가한 차량이 없습니다.");
        }
        while (currentCount < totalCount) {
            raceOneRound(numberGenerator);
        }
    }

    private void raceOneRound(NumberGenerator numberGenerator) {
        if (cars == null) {
            throw new IllegalStateException("라운드를 진행하기 위해 참가한 차량이 없습니다.");
        }
        final List<Car> allCars = cars.getCars();
        allCars.forEach(car -> car.drive(numberGenerator.generate()));
        currentCount++;
    }

    public void judgeWinner() {
        if (cars == null) {
            throw new IllegalStateException("우승자를 판단하기에는 참가한 차량이 없습니다.");
        }
        final List<Car> candidates = cars.getCars();
        final int winnerDistance = candidates.stream()
            .mapToInt(Car::getPosition)
            .max()
            .orElse(0);
        candidates.forEach(car -> car.updateWinningCondition(winnerDistance));
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<Car> getCars() {
        return cars.getCars();
    }
}
