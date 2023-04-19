# jwp-racingcar

# 요구사항

## 입력
- [ ] 자동차 이름 입력
  - [ ] 쉼표 구분자로 이름 나누기
  - [ ] [예외 처리] Null or Empty
  - [ ] [예외 처리] 영어, 한글, 쉼표가 아닌 문자가 포함

- [ ] 경주 횟수 입력
    - [ ] [예외 처리] Null or Empty
    - [ ] [예외 처리] 숫자가 아닌 문자 포함

## 출력
- [ ] 최종 결과 출력
  - [ ] 우승자 출력 (여러명일 수 있다)
  - [ ] 각 자동차의 현 상황 출력

## 도메인
- [x] 자동차
  - [x] 이름
    - [x] [예외 처리] 이름 길이 1~5자
  - [x] Position
    - [x] 1 증가한다.

- [x] 레이싱 게임
  - [x] 시도 횟수 (TryNumber)
    - [x] [예외 처리] 100만 초과
    - [x] [예외 처리] 음수
    - [x] 1 감소한다.
    - [x] 지정된 시도 횟수가 0이 되면 true를 반환한다 (isFinished)
  - [x] 시도 횟수만큼 이동을 시도한다

  - [x] 이동 여부 결정 (RandomBasedMoveStrategy - 0 ~ 9중 랜덤 숫자 생성)
    - [x] 무브 : 랜덤 숫자 4이상
    - [x] 스탑 : 랜덤 숫자 3이하

