# jwp-racingcar

# 웹 자동차 경주

### 웹 응답/요청 하기

- [x] 요청
  - [x] 이름과 실행 횟수를 보낸다
- [x] 게임 진행
  - [x] 요청 받은 이름과 실행 횟수를 통해 게임을 진행한다
- [x] 응답
  - [x] 각 플레이어의 결과를 반환한다

- [ ] 요청
  - [ ] 우승자와 이동횟수를 요구한다
- [ ] 응답
  - [ ] 우승자와 이동횟수를 반환한다
### DB 연동 하기

- [x] H2 연결하기
- [x] 저장하기
  - 플레이 횟수(trialCount)
  - 플레이어 별 최종 이동 거리 (이름(name), 최종 위치(position))
  - 우승자(winners)
  - 플레이한 날짜/시간

### 설명

- RacingCarConsoleApplication : 터미널에서 작동
- RacingCarApplication : h2 db와 web 연결
