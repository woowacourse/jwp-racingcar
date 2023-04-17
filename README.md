# jwp-racingcar

- [x] 레이싱 카 프로덕션 코드 가져오기
- [x] 파라미터 받아오기
- [x] dto 매핑하기
    - [x] 반환값을 dto로 만들기
- [x] 테이블 설계

### 2단계 요구사항
- [x] 결과 출력
  - [x] 기존 출력 기능 코드 가져오기
  - [x] 중간 과정 출력 로직 제거
  - [x] 우승자와 player 별 최종 이동거리 출력

## query
```sql
CREATE TABLE cars
(
`name`       VARCHAR(20) NOT NULL,
`position`     INT       NOT NULL,
`gameId`       INT       NOT NULL,
PRIMARY KEY (`name`),
FOREIGN KEY ( gameId ) REFERENCES games ( gameId )
);
CREATE TABLE games
(
`gameId`        INT          NOT NULL AUTO_INCREMENT,
`count        ` INT          NOT NULL,
`winner`        VARCHAR(100) NOT NULL,
`dateTime`      DATETIME     NOT NULL,
PRIMARY KEY (`gameId`)
);
```
