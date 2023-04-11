package racingcar.view.util;

import java.util.Scanner;

public class KeyboardReader {

    private static final Scanner scanner = new Scanner(System.in);

    private KeyboardReader() {
    }

    public static String readLine() {
        return scanner.nextLine();
    }

    public static int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("입력받은 값이 숫자가 아닙니다.");
        }
    }

    public static int readNaturalNumber() {
        int readValue = readInt();
        if (readValue <= 0) {
            throw new IllegalArgumentException("입력받은 값이 자연수가 아닙니다.");
        }
        return readValue;
    }
}
