package racingcar.view;

public class OutputView {

    private static final String CAR_POSITION_SIGN = "-";
    private static final String COLON = " : ";
    private static final String DELIMITER = ", ";

    public void printPosition(String name, int position) {
        System.out.println(name + COLON + CAR_POSITION_SIGN.repeat(position));
    }

    public void printWinners(String winners) {
        System.out.println("승자 : " + winners);
    }
}
