# jwp-racingcar

## HOW

- 요청/응답을 위한 컨트롤러 객체
    - 기존 컨트롤러 참고하면서.

## 기능 목록

- 컨트롤러
    - 참가자 이름과 시도 횟수를 받는다(JSON)
        - 게임 결과로 응답한다.(JSON)

```http request
POST /plays HTTP/1.1
content-type: application/json; charset=UTF-8
host: localhost:8080

{
    "names": "브리,토미,브라운",
    "count": 10
}

```

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