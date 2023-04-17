package racingcar.domain.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class RandomNumberGenerator implements NumberGenerator {
    Random random = new Random();
    public static final int BOUND = 10;


    @Override
    public List<Integer> generateNumbers(int size) {
        List<Integer> generatedNumbers = new ArrayList<>();
        for (int count = 0; count < size; count++) {
            generatedNumbers.add(random.nextInt(BOUND));
        }
        return generatedNumbers;
    }
}
