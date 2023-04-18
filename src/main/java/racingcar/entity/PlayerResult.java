package racingcar.entity;


public final class PlayerResult {


	private Long id;
	private Long gameId;
	private String name;
	private int position;
	private boolean isWinner;

	public PlayerResult (final Long id, final String name, final int position, final boolean isWinner) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.isWinner = isWinner;
	}

	public PlayerResult (final String name, final int position, final boolean isWinner) {
		this.name = name;
		this.position = position;
		this.isWinner = isWinner;
	}

	public PlayerResult (final Long id, final Long gameId, final String name, final int position,
						 final boolean isWinner) {
		this.id = id;
		this.gameId = gameId;
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
