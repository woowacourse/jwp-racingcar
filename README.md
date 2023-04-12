# jwp-racingcar

## 요구사항

### 웹 요청 / 응답 구현하기

- [ ] POST로 `names` 와 `count`를 입력 받을 수 있다.
- [ ] 우승자와 각 자동차들의 최종 위치를 JSON 형식으로 응답할 수 있다.

### DB 연동하기

- H2 DB를 연동할 수 있다.
    - [ ] 플레이 횟수를 DB에 저장할 수 있다.
    - [ ] 플레이어 별 최종 이동 거리, 최종 위치를 DB에 저장할 수 있다.
    - [ ] 우승자를 DB에 저장할 수 있다.
    - [ ] 플레이한 날짜/시간을 DB에 저장할 수 있다.

## DB 설계

### Player

- ID (id)
- 이름 (name)
- 최종 위치 (position)

### Game

- ID (id)
- 플레이 횟수 (trial_count)
- 우승자 (winners)
- 플레이한 날짜/시간 (time)

### Winners

- ID (id)
- Game ID (game_id)
- Player ID (player_id)
