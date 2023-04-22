# jwp-racingcar

## TODO

- ~~winners table을 foreign key만으로 구성 (game_id, car_id)~~
    - [x] cars table에서 winner의 car id를 다시 얻는 법에 대한 고민 필요
- 게임이 생성될때는 DB에 기록할 필요가 없는가?

## HOW

- 요청/응답을 위한 컨트롤러 객체
    - 기존 컨트롤러 참고하면서.

## 기능 목록

- 컨트롤러
    - 참가자 이름과 시도 횟수를 받는다(JSON)
        - 게임 결과로 응답한다.(JSON)
- 서비스
    - 게임실행 결과를 db에 저장한다
        - 플레이 횟수(trialCount)
        - 플레이어 별 최종 이동 거리 (이름(name), 최종 위치(position))
        - 우승자(winners)
        - 플레이한 날짜/시간

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