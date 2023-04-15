CREATE TABLE TRACK
(
    id          int auto_increment PRIMARY KEY,
    trial_times int      NOT NULL,
    created_at  DATETIME NOT NULL default current_timestamp
);

CREATE TABLE CAR
(
    id        int auto_increment PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    position  int          NOT NULL,
    is_winner BIT          NOT NULL,
    track_id  int          NOT NULL,
    FOREIGN KEY (track_id) REFERENCES TRACK (id)
);
