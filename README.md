# jwp-racingcar

### 기능 구현 목록

- [x] 자동차 경주 코드 가져오기
- [x] 웹 요청/응답 구현하기
- [x] DB 연동하기
- [ ] 콘솔 어플리케이션에서 플레이의 중간 과정을 출력하는 로직을 제거한다.
- [ ] 콘솔 어플리케이션에서 웹 어플리케이션과 동일하게 우승자와 player 별 최종 이동거리를 출력한다.
- [ ] 콘솔 어플리케이션과 웹 어플리케이션의 중복 코드를 제거한다.

### 게임 진행

- [x] 받은 요청에 대해 자동차 경주를 진행하고 우승자와 각 자동차들의 최종 위치를 `JSON` 형식으로 응답한다.
  - 요청 형식
    ```
    POST /plays HTTP/1.1
    content-type: application/json; charset=UTF-8
    host: localhost:8080
    
    {
        "names": "브리,토미,브라운",
        "count": 10
    }
    ```
  - 응답 형식
    ```
    HTTP/1.1 200 
    Content-Type: application/json
    
    {
        "winners": "브리",
        "racingCars": [
            {
                "name": "브리",
                "position": 9
            },
            {
                "name": "토미",
                "position": 7
            },
            {
                "name": "브라운",
                "position": 3
            },
        ]
    }
    ```
- [x] 페이지를 통해 기능이 정상적으로 동작하는 지 확인할 수 있다.
- [x] 자동차 경주 게임의 플레이 이력을 DB에 저장한다.
  - [x] 플레이 횟수(trialCount)
  - [x] 플레이어 별 최종 이동 거리(name), 최종 위치(position)
  - [x] 우승자(winners)
  - [x] 플레이한 날짜/시간
- [ ] `/plays`로 GET 요청 시 DB에 저장된 플레이 이력을 `JSON` 형식으로 응답한다.
  - 요청 형식
    ```
    GET /plays HTTP/1.1
    ```
  - 응답 형식
    ```
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
          }
        ]
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
          }
        ]
      }
    ]
    ```
