# jwp-racingcar

## 요구사항
- [x] 자동차 경주 코드 가져오기
- [x] 웹 요청
  - path: /plays
  - type: json
  - names
  - count
- [x] 웹 응답
  - path: /plays
  - type: json
  - winners
  - racingCars
    - name
    - position
  - count
- [x] DB 연동하기
- [ ] 게임 플레이 이력 조회 API 구현
  - [ ] 전체 PlayResult 조회
  - [ ] 전체 Player 조회
- [ ] 기존 기능 수정 - 출력 방식 수정
- [ ] 리팩터링 - 중복 코드 제거

## 추가 기능

- [x] 에러 핸들링

## DB 테이블

```sql
CREATE TABLE PLAY_RESULT (
    id          BIGINT         NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(50) NOT NULL,
    trial_count INT NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE PLAYER (
    id              BIGINT NOT NULL AUTO_INCREMENT,
    play_result_id  INT NOT NULL,
    name            VARCHAR(5) NOT NULL,
    position        INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (play_result_id) REFERENCES PLAY_RESULT (id)
);
```