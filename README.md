# jwp-racingcar

## 기능 목록 명세

- [x] 게임 플레이 이력 조회 API
- [ ] Console Application의 출력 변경
    - [ ] 플레이의 중간 과정을 출력하는 로직을 제거합니다.
    - [ ] 우승자와 player 별 최종 이동거리를 출력하도록 수정합니다.

## 게임 시작 API

#### Request

```http request
POST /plays HTTP/1.1
content-type: application/json; charset=UTF-8
host: localhost:8080

{
    "names": "브리,토미,브라운",
    "count": 10
}
```

#### Response

```json
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
    }
  ]
}
```

## 게임 플레이 이력 조회 API

#### Request

```http request
GET /plays HTTP/1.1
```

#### Response

```json
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
