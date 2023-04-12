# jwp-racingcar

## 1단계 기능 요구사항

- [x] 자동차 경주 가져오기
- [ ] 웹 요청 구현하기

    ```http request
    POST /plays HTTP/1.1
    content-type: application/json; charset=UTF-8
    host: localhost:8080
    
    {
        "names": "브리,토미,브라운",
        "count": 10
    }
    ```
- [ ] 웹 응답 구현하기
    - [ ] JSON 형식으로 반환
    ```http request
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
- [ ] DB 연동하기
    - [ ] 자동차 경주 게임의 플레이 이력을 DB에 저장
    ```
    플레이 횟수(trialCount)
    플레이어 별 최종 이동 거리 (이름(name), 최종 위치(position))
    우승자(winners)
    플레이한 날짜/시간
    ```
