package racingcar.view;

import java.util.List;
import java.util.Scanner;

public final class InputView {

	private Scanner SCANNER = new Scanner(System.in);

	public List<String> inputCarNames () {
		System.out.println("참가자 이름을 ,로 구분해 입력해 주세요");
		return List.of(SCANNER.nextLine().split(","));
	}

	public int inputTrialCount() {
		System.out.println("진행 횟수를 숫자로 입력해 주세요");
		return Integer.parseInt(SCANNER.nextLine());
	}
}
