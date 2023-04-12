### 자동차 경주 코드 가져오기

- [x] 이전에 구현한 자동차 경주 미션 코드를 가져와서 racingcar 경로 하위에 위치시킨다.

### 웹 요청/응답 구현하기

- [x] 애플리케이션으로 부터 받은 요청에 대해 우승자와 각 자동차의 결과를 json 형식으로 응답한다.
- [x] 요청은 json 형태로 들어온다.

    ```json
    POST /plays HTTP/1.1
    content-type: application/json; charset=UTF-8
    host: localhost: 8080
    
    {
        "names": "브리,토미,브라운",
        "count": 10
    }
    ```

- [x] 응답은 다음과 같은 형태로 반환한다.
  ```json
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

### db 연동하기

- 플레이 횟수(trialCount)
- 플레이어 별 최종 이동 거리 (이름(name), 최종 위치(position))
- 우승자(winners)
- 플레이한 날짜/시간
