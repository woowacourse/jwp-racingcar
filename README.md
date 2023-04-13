# jwp-racingcar

- [x] 레이싱 카 프로덕션 코드 가져오기
- [x] 파라미터 받아오기
- [x] dto 매핑하기
    - [x] 반환값을 dto로 만들기
- [x] 테이블 설계

```sql
CREATE TABLE games
(
  `game_id`      INT           NOT NULL AUTO_INCREMENT,
  `count`        INT           NOT NULL,
  `winner`       VARCHAR(100)  NOT NULL,
  `timeStamp`    TIMESTAMP     NOT NULL,
  PRIMARY KEY (`game_id`)
);
CREATE TABLE cars
(
  `name`       VARCHAR(20) NOT NULL,
  `position`     INT       NOT NULL,
  `game_id`       INT       NOT NULL,
  PRIMARY KEY (`name`, `game_id`),
  FOREIGN KEY ( `game_id` ) REFERENCES `games` ( `game_id` )
);

```
