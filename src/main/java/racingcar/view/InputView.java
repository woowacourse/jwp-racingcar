package racingcar.view;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String inputCarName() {
        return scanner.next();
    }

    public int inputTryCount() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException inputMismatchException) {
            throw new IllegalArgumentException(inputMismatchException);
        }
    }
}
