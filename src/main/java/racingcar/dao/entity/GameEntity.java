package racingcar.dao.entity;

import java.time.LocalDateTime;

public class GameEntity {

	private Long id;
	private String winners;
	private int trialCount;
	private LocalDateTime createdAt;

	public GameEntity(final Long id, final String winners, final int trialCount, final LocalDateTime createdAt) {
		this.id = id;
		this.winners = winners;
		this.trialCount = trialCount;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public String getWinners() {
		return winners;
	}

	public int getTrialCount() {
		return trialCount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setWinners(final String winners) {
		this.winners = winners;
	}

	public void setTrialCount(final int trialCount) {
		this.trialCount = trialCount;
	}

	public void setCreatedAt(final LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
