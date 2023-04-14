# jwp-racingcar

## 웹 요청 / 응답 구현하기

### 경주 게임 시작
WebController
- [x] 게임 play Post 요청 받기
  - [x] 자동차 이름 분리
  - [x] 자동차 객체 생성
  - [x] 게임 결과 반환 (winners, racingCars)

ControllerAdvice
- [x] Count 검증 예외 처리

Service
- [x] 게임 플레이
  - [x] 자동차 이동
  - [x] CAR_RESULT 테이블에 자동차 데이터 삽입
  - [x] PLAY_RESULT 테이블에 게임 데이터 삽입

Dto
- [x] CarDto
- [x] RacingGameRequestDto
- [x] RacingGameResponseDto

Dao
- [x] CarDao
  - [x] InMemoryCarDao
    - [x] CAR_RESULT 데이터 saveAll
- [x] GameDao
  - [x] InMemoryGameDao
    - [x] PLAY_RESULT 데이터 save

## DB 연동하기
- [x] CAR_RESULT
  - [x] 플레이 횟수, 우승자, 플레이한 날짜/시간 저장
- [x] PLAY_RESULT
  - [x] 게임 ID, 플레이어 별 최종 이동거리 저장
