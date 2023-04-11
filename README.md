# jwp-racingcar

### 기능 구현 목록

- [x] 자동차 경주 코드 가져오기
- [x] 웹 요청/응답 구현하기
- [ ] DB 연동하기

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
- [ ] 자동차 경주 게임의 플레이 이력을 DB에 저장한다.
  - [ ] 플레이 횟수(trialCount)
  - [ ] 플레이어 별 최종 이동 거리(name), 최종 위치(position)
  - [ ] 우승자(winners)
  - [ ] 플레이한 날짜/시간
