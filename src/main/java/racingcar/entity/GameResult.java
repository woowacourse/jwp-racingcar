package racingcar.entity;


public final class GameResult {

	private String name;
	private int position;
	private boolean isWinner;

	public GameResult (final String name, final int position, final boolean isWinner) {
		this.name = name;
		this.position = position;
		this.isWinner = isWinner;
	}

	public String getName () {
		return name;
	}

	public int getPosition () {
		return position;
	}

	public boolean isWinner () {
		return isWinner;
	}
}
