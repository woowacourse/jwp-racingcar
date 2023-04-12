# jwp-racingcar

# 요구사항

- [x] 자동차 경주 코드 가져오기

---

- [x] 웹을 통해 요청받기
    - [x] 자동차 이름 (name)
    - [x] 시도할 횟수 (count)

---

- [x] JSON으로 응답하기
    - [x] 우승자 ( winners )
    - [x] 자동차 ( racingCars )
        - [x] 이름 (name)
        - [x] 위치 (position)

---

- [ ] DB 연동하기
    - [ ] H2 DB 연동
    - [ ] 플레이 이력 저장
        - [ ] 플레이 횟수 (trialCount)
        - [ ] 플레이어 별 최종 이동 거리
            - [ ] 이름 (name)
            - [ ] 최종 위치 (position)
        - [ ] 우승자 (winners)
        - [ ] 플레이한 날짜/시간 (time)

- [x] DB 테이블
    - [x] Race
        - [x] id (pk) : SERIAL
        - [x] 플레이한 날짜/시간 (time) : DATETIME DEFAULT NOW()

    - [x] Winner
        - [x] id (pk) : SERIAL
        - [x] race_id (fk) : INT
        - [x] player_id (fk) : INT

    - [x] Player
        - [x] id (pk) : SERIAL
        - [x] 이름 (name) : VARCHAR(255)
        - [x] 최종 위치 (position) : INT
        - [x] race_id (fk) : INT
