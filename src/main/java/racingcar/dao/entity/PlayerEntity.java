package racingcar.dao.entity;

public class PlayerEntity {

	private Long id;
	private long gameId;
	private String name;
	private int position;

	public PlayerEntity(final Long id, final long gameId, final String name, final int position) {
		this.id = id;
		this.gameId = gameId;
		this.name = name;
		this.position = position;
	}

	public long getId() {
		return id;
	}

	public long getGameId() {
		return gameId;
	}

	public String getName() {
		return name;
	}

	public int getPosition() {
		return position;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public void setGameId(final long gameId) {
		this.gameId = gameId;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setPosition(final int position) {
		this.position = position;
	}
}
