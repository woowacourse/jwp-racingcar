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

- [x] DB 연동하기
    - [x] H2 DB 연동
    - [x] 플레이 이력 저장
        - [x] 플레이 횟수 (trialCount)
        - [x] 플레이어 별 최종 이동 거리
            - [x] 이름 (name)
            - [x] 최종 위치 (position)
        - [x] 우승자 (winners)
        - [x] 플레이한 날짜/시간 (time)
        - [x] 플레이 횟수 (play_count)

- [x] DB 테이블
    - [x] Race
        - [x] id (pk) : SERIAL
        - [x] 플레이한 날짜/시간 (time) : DATETIME DEFAULT NOW()
        - [x] 플레이 횟수 (play_count)

    - [x] Winner
        - [x] id (pk) : SERIAL
        - [x] race_id (fk) : INT
        - [x] player_id (fk) : INT

    - [x] Player
        - [x] id (pk) : SERIAL
        - [x] 이름 (name) : VARCHAR(255)
        - [x] 최종 위치 (position) : INT
        - [x] race_id (fk) : INT

- [ ] 게임 플레이 이력 조회 API 구현
  - [x] 모든 Race_id를 찾기
  - [x] player_id들로 Car를 찾기
  - [x] race_id로 Car를 찾기
  - [x] race_id로 player_id 찾기
  - [ ] 모든 게임 결과 정보를 가져오기
  - [ ] 모든 게임 결과 정보를 Json으로 반환하기
- [ ] 기존 기능 수정 - 출력 방식 수정
- [ ] 리팩터링 - 중복 코드 제거
