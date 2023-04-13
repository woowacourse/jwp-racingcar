# jwp-racingcar

## 요구사항

- [x] 자동차 경주 코드 가져오기
- [x] 웹 요청/응답 구현하기

- 요청
    ```http request
    POST /plays HTTP/1.1
    content-type: application/json; charset=UTF-8
    host: localhost:8080
    
    {
        "names": "브리,토미,브라운",
        "count": 10
    }
    ```

- 응답
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

- [x] DB 연동하기
