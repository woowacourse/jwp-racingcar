package racingcar.dto;

public class GamePlayRequestDto {

	private final String names;
	private final int count;

	public GamePlayRequestDto(final String names, final int count) {
		this.names = names;
		this.count = count;
	}

	public String getNames() {
		return names;
	}

	public int getCount() {
		return count;
	}
}
