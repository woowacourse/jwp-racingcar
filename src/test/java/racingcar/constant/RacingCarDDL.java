package racingcar.constant;

public class RacingCarDDL {
    public static final String DROP_TABLE_CAR = "DROP TABLE car IF EXISTS";
    public static final String DROP_TABLE_GAME = "DROP TABLE game IF EXISTS";
    public static final String CREATE_TABLE_GAME = "CREATE TABLE GAME ("
            + "id          INT         NOT NULL AUTO_INCREMENT,"
            + "trial_count  INT         NOT NULL,"
            + "created_at  DATETIME    NOT NULL default current_timestamp,"
            + "PRIMARY KEY (id));";
    public static final String CREATE_TABLE_CAR = "CREATE TABLE car ("
            + "id          INT         NOT NULL AUTO_INCREMENT,"
            + "name        VARCHAR(50) NOT NULL,"
            + "position    INT         NOT NULL,"
            + "is_winner    TINYINT(1) NOT NULL DEFAULT 0,"
            + "game_id     INT         NOT NULL,"
            + "PRIMARY KEY (id),"
            + "FOREIGN KEY (game_id)"
            + "REFERENCES GAME(id) ON DELETE CASCADE);";
}
