# jwp-racingcar

## 2단계 리팩토링 목록
- [x] 요청에 대한 값을 검증한다.
- [x] 사용하지 않는 변수는 바로 리턴한다.
- [x] 서비스 레이어에서 도메인을 반환하지 않고 dto를 반환하도록 변경.
- [x] 예외가 발생했을 때 로그로 남겨보기
- [ ] 예외 처리 중복 로직 제거
- [x] 커스텀 예외를 제외한 공통 예외 처리 작성해보기.
- [x] cars를 저장하는 쿼리를 한 방에 처리하도록 해보기.
- [x] 콘솔과 웹 애플리케이션의 중복을 제거한다.

## 요청

- [x] 자동차 경주 참가자와 시도 횟수를 요청 받는다
- [x] 자동차 경주 결과 응답을 원하는 요청을 받는다

## 응답

- [x] 우승자와 각 자동차들의 최종 위치를 JSON 형식으로 응답한다
- [x] 예외가 발생하는 경우, 예외에 해당하는 응답을 반환한다
    - 자동차 경주 참가자
        - [x] 쉼표를 기준으로 자동차 이름을 구분한다
        - [x] 자동차 이름은 문자와 숫자만 가능하다
        - [x] 자동차 이름은 한글자 이상만 가능하다
        - [x] 자동차 이름은 5자 이하만 가능하다
        - [x] 중복된 자동차 이름이 존재하면 안된다
    - 시도 횟수
        - [x] 정수를 입력해야 한다
        - [x] 1보다 크고 100보다 작아야 한다
- [x] 모든 게임에서 각 우승자와 참여자의 결과를 반환한다

## DB

- [x] 자동차 경주 게임의 플레이 이력을 DB에 저장한다
    - 플레이 횟수(trialCount)
    - 플레이어 별 최종 이동 거리 (이름(name), 최종 위치(position))
    - 우승자(winners)
    - 플레이한 날짜/시간

- [x] 자동차 경주 게임의 플레이 이력을 DB에서 가져온다
  - 각 게임별 우승자, 참가자
