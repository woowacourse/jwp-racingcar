package racingcar.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Participants {

    private final List<Car> cars;
    private final Map<String, Integer> nameCount;
    
    public Participants(List<String> names) {
        cars = new ArrayList<>();
        nameCount = new HashMap<>();
        names.forEach(this::join);
    }

    private void join(String carName) {
        int identifier = findIdentifier(carName);
        Car participant = new Car(carName, identifier);
        cars.add(participant);
    }

    private int findIdentifier(String name) {
        final int initNumber = 0;
        if (!nameCount.containsKey(name)) {
            nameCount.put(name, initNumber);
            return initNumber;
        }
        return nameCount.merge(name, 1, Integer::sum);
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
