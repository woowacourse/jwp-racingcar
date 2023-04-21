CREATE TABLE IF NOT EXISTS `application_type`
(
    `id`   INT     NOT NULL AUTO_INCREMENT,
    `type` CHAR(2) NOT NULL COMMENT 'CS: Console/WE: Web',
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `game` (
                                      `id` BIGINT NOT NULL AUTO_INCREMENT,
                                      `trial_count` INT NOT NULL,
                                      `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      `application_id` INT NOT NULL COMMENT 'CS: Console\nWE: Web',
                                      PRIMARY KEY (`id`),
                                      CONSTRAINT `application_game_id`
                                          FOREIGN KEY (`application_id`)
                                              REFERENCES `application_type` (`id`)
                                              ON DELETE NO ACTION
                                              ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `player` (
                                        `id` BIGINT NOT NULL AUTO_INCREMENT,
                                        `game_id` BIGINT NOT NULL,
                                        `name` VARCHAR(50) NOT NULL,
                                        `position` INT NOT NULL,
                                        `is_winner` TINYINT NOT NULL,
                                        PRIMARY KEY (`id`),
                                        CONSTRAINT `game_player_id`
                                            FOREIGN KEY (`game_id`)
                                                REFERENCES `game` (`id`));

insert into application_type(type)
values ('CS');
insert into application_type(type)
values ('WE');
