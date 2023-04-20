# jwp-racingcar

## 요구사항
- [x] 자동차 경주 코드 가져오기
- [x] 게임 플레이
  - PostMapping
  - path: /plays
  - Request
    - type: json
    - names
    - count
  - Response
    - type: json
    - winners
    - List\<CarDto> racingCars
      - CarDto
        - name
        - position
    - count
- [x] DB 연동하기
- [x] 게임 플레이 이력 조회 API 구현 
  - [x] 전체 PlayResult 조회
  - [x] 전체 Player 
  - Get
  - path: /plays
  - Request
  - Response
    - type: json
    - List\<String> winners
    - List\<CarDto> racingCars
      - CarDto
        - name
        - position
- [x] 기존 기능 수정 - 출력 방식 수정
- [x] 리팩터링 - 중복 코드 제거
  - PlayerDao
    - PlayerInMemoryDao
    - PlayerJdbcDao
  - PlayResultDao
    - PlayResultInMemoryDao
    - PlayResultJdbcDao

## 추가 기능

- [x] 에러 핸들링

## DB 테이블

[data.sql](https://github.com/parkmuhyeun/jwp-racingcar/blob/step2/src/main/resources/data.sql)