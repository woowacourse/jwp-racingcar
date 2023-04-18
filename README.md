# jwp-racingcar

### 기능 구현 목록

- 1단계: 게임 진행
    - [x] 자동차 경주 코드 가져오기
    - [x] 웹 요청/응답 구현하기
    - [x] DB 연동하기
- 2단계: 게임 이력 조회
    - [x] 게임 플레이 이력 조회 API 구현
    - [x] 기존 기능 수정 - 출력 방식 수정
      - [x] 플레이의 중간 과정을 출력하는 로직을 제거
      - [x] 우승자와 player 별 최종 이동거리를 출력
    - [x] 리팩터링 - 중복 코드 제거
      - [x] console application과 web application의 중복 코드를 제거

### 게임 진행

- [x] 받은 요청에 대해 자동차 경주를 진행하고 우승자와 각 자동차들의 최종 위치를 `JSON` 형식으로 응답한다.
    - Request 형식
      ```
      POST /plays HTTP/1.1
      content-type: application/json; charset=UTF-8
      host: localhost:8080
      
      {
          "names": "브리,토미,브라운",
          "count": 10
      }
      ```
    - Response 형식
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

### 게임 이력 조회

- [ ] DB에 저장된 플레이 이력을 요청하면 응답하는 기능을 구현한다.
    - Request
        ``` 
        GET /plays HTTP/1.1
        ```
    - Response 형식
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
      