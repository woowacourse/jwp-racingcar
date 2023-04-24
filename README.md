# jwp-racingcar

## 1단계 기능 요구사항

- [x] 자동차 경주 코드 가져오기
- [x] api 구현
    - request
        - path: /plays
        - HTTP methods : POST
        - content-type: application/json
        - names
        - count

    - response
        - content-type: application/json
        - winners
        - racingCars
            - name
            - position
- [x] DB 연동하기
     ```sql
      CREATE TABLE PLAY_RESULT (
         id          BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT,
         winners     VARCHAR(50) NOT NULL,
         trial_count INT NOT NULL,
         created_at  DATETIME    NOT NULL default current_timestamp,
         PRIMARY KEY (id)
      );
    
      CREATE TABLE PLAYER (
         id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
         play_result_id  INT NOT NULL,
         name            VARCHAR(5) NOT NULL,
         position        INT NOT NULL,
         PRIMARY KEY (id),
         FOREIGN KEY (play_result_id) REFERENCES PLAY_RESULT (id)
      );
    ```

## 2단계 추가된 기능 요구사항

- [x] 게임 플레이 이력 조회 API 구현

    - request
        - path: /plays
        - HTTP methods : GET
        - type: application/json

    - response
        - type: application/json
        - winners
        - racingCars
            - name
            - position
- [x] Console Application 출력 변경
    - [x] 플레이의 중간 과정 출력 로직 제거
    - [x] 우승자와 player 별 최종 이동거리를 출력하도록 수정
- [x] 중복 코드 제거
    -[x] 새로운 객체를 도출하여 두 application의 비즈니스 로직 중복 제거


질문 : 테이블 조인 등의 기술을 전혀 쓰지 않고 있는데, 서
