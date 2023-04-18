package racingcar.entity;

import java.time.LocalDateTime;

public final class GameResult {

	private Long id;
	private int trialCount;
	private LocalDateTime playDateTime;

	public GameResult (final Long id, final int trialCount, final LocalDateTime playDateTime) {
		this.id = id;
		this.trialCount = trialCount;
		this.playDateTime = playDateTime;
	}

	public Long getId () {
		return id;
	}
}
