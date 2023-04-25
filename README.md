# jwp-racingcar

## 요청

- [x] 자동차 경주 참가자와 시도 횟수를 요청 받는다

## 응답

- [x] 게임하기
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
- [x] 이력 조회
  - [x] 각 유저의 이동 횟수와 우승자를 JSON 형식으로 응답한다

## DB

- [x] 자동차 경주 게임의 플레이 이력을 DB에 저장한다
    - 플레이 횟수(trialCount)
    - 플레이어 별 최종 이동 거리 (이름(name), 최종 위치(position))
    - 우승자(winners)
    - 플레이한 날짜/시간

## HTTP
- 게임하기
  - 요청
    ```text
    POST 127.0.0.1:8080/plays
    Content-Type: application/json; charset=UTF-8
    
    {
        "names": "gray, hoy, logan",
        "count": "10"
    }
    ```
  - 응답
    ```text
    GET 127.0.0.1:8080/plays
    Content-Type: application/json; charset=UTF-8

    {
      "winners": "logan",
      "racingCars": [
          {
              "name": "gray",
              "position": 8
          },
          {
              "name": "hoy",
              "position": 7
          },
          {
              "name": "logan",
              "position": 9
          }
      ]
    }
    ```
- 이력 조회
  - 요청
    ```text
    Request Method: GET
    URI: /plays
    ```
  - 응답
    ```text
    HTTP/1.1 200
    Content-Type: application/json

    [
        {
          "winners": "브리",
          "racingCars": [
              {
                "name": "브리",
                "position": 6
              },
              {
                "name": "토미",
                "position": 4
              },
              {
                "name": "브라운",
                "position": 3
              }]
        },
        {
          "winners": "브리,토미,브라운",
          "racingCars": [
              {
                "name": "브리",
                "position": 6
              },
              {
                "name": "토미",
                "position": 6
              },
              {
                "name": "브라운",
                "position": 6
              }]
        }
    ]
    ```
