# jwp-racingcar

## 기능 요구사항
- [x] 자동차 경주 코드 가져오기

- [x] 웹 요청/응답 구현하기

- [x] DB 연동하기
  - [x] H2 DB를 연동하기
  - [x] DB table 생성하기
  - [x] 자동차 경주 게임의 플레이 이력을 DB에 저장

- [x] 게임 플레이 이력 조회 API 구현
  - [x] DB에 저장된 플레이 이력을 요청하면 응답하는 기능
- [x] 기존 기능 수정 - 출력 방식 수정
  - [x] console application에서 플레이의 중간 과정을 출력하는 로직 제거
  - [x] console application에서 web application과 동일하게 우승자와 player 별 최종 이동거리를 출력하도록 수정
- [x] 리팩터링 - 중복 코드 제거
  - [x] console application과 web application의 중복 코드를 제거
