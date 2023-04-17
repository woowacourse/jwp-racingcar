package view;

public class OutputView {
	private static final String TELL_FINAL_WINNER = "가 최종 우승했습니다";


	public void printWinner(String winners) {
		String result = winners + TELL_FINAL_WINNER;
		System.out.println(result);
	}
}
