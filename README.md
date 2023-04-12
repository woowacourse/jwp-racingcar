# jwp-racingcar



## 기능 요구사항
- [x] 자동차 경주 코드 가져오기
- [x] 웹 요청/응답 구현하기
  - [x] 콘솔 입력 &rarr; POST 요청
    - [x] 자동차 이름과 이동 횟수를 한 번에 입력받는다
  - [x] 콘솔 출력 &rarr; HTTP 응답
    - [x] 우승자를 조회할 수 있다
    - [x] 모든 차의 최종 위치를 조회할 수 있다
- [ ] DB 연동하기
  - [x] H2 DB 연동
  - [ ] 플레이 이력 저장
    - [x] 플레이 횟수 (trialCount)
    - [x] 플레이한 시간, 날짜 (playTime)
    - [ ] 플레이어 별 최종 이동 거리 (name, position)
    - [ ] 우승 여부 (isWinner)
