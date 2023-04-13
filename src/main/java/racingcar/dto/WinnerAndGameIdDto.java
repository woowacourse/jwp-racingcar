package racingcar.dto;

public class WinnerAndGameIdDto {
	private int gameId;
	private String winner;

	public WinnerAndGameIdDto(int gameId, String winner) {
		this.gameId = gameId;
		this.winner = winner;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}
}
