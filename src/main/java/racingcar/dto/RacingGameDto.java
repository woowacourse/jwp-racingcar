package racingcar.dto;

public class RacingGameDto {
	private final String names;
	private final String trial;

	public RacingGameDto(String names, String trial) {
		this.names = names;
		this.trial = trial;
	}

	public String getNames() {
		return names;
	}

	public String getTrial() {
		return trial;
	}
}
