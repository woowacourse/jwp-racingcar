# jwp-racingcar

# 웹 자동차 경주

### 웹 응답/요청 하기

- [x] 요청
  - [x] 이름과 실행 횟수를 보낸다
- [x] 게임 진행
  - [x] 요청 받은 이름과 실행 횟수를 통해 게임을 진행한다
- [x] 응답
  - [x] 각 플레이어의 결과를 반환한다

### DB 연동 하기

- [x] H2 연결하기
- [x] 저장하기
  - 플레이 횟수(trialCount)
  - 플레이어 별 최종 이동 거리 (이름(name), 최종 위치(position))
  - 우승자(winners)
  - 플레이한 날짜/시간

###
- [x] 게임 플레이 이력 조회 API 구현
    - [x] 요구된 Response 형식에 맞도록 응답하는 GET 메서드 구현
- [x] 기존 기능 수정
    - [x] console application에서 플레이의 중간 과정을 출력하는 로직을 제거
    - [x] console application에서 web application과 동일하게 우승자와 player 별 최종 이동거리를 출력하도록 수정
- [x] 리팩터링 - 중복 코드 제거
    - [x] Service 하나로 통합
