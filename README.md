# jwp-racingcar

# 미션 요구사항

- [x] 자동차 경주 코드 가져오기
- [x] 웹 요청/응답 구현하기
  - [x] 사용자가 index 페이지에서 자동차 이름과 시도 횟수 정보를 포함한 요청을 보낸다.
  - [x] 자동차 경주 게임 진행 결과를 응답한다.
  - [x] 사용자가 자동차 경주 게임 플레이 이력을 조회할 수 있다.
- [x] DB 연동하기
  - [x] '플레이 횟수, 플레이어별 최종 이동 거리, 우승자, 플레이한 날짜/시간'을 저장한다.
- [ ] 기존 기능 수정 - 출력 방식 수정
  - [x] Console Application에서 '플레이의 중간 과정을 출력하는 로직'을 제거한다.
  - [ ] Console Application에서 Web Application과 동일하게 '우승자와 player 별 최종 이동거리'를 출력하도록 수정한다.
- [ ] 리팩터링 - 중복 코드 수정
  - [ ] Console Application과 Web application의 중복 코드를 제거한다.
    - 힌트: 두 application은 입출력과 데이터 저장 방식을 제외하고는 내부 비즈니스 로직은 동일해졌습니다.
    - 힌트: 두 application의 비즈니스 로직은 새로운 객체를 도출 하여 중복 제거를 할 수 있습니다.

## 게임 플레이 이력 조회 API 요구사항
### Request
```http request
GET /plays HTTP/1.1
```

### Response
```http response
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
            },
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
            },
        ]
    }
]
```

# 프로그램 요구사항

- [x] 게임하기
  - [x] 자동차 이름, 시도할 횟수를 입력한다.
  - [x] 자동차 경주 게임 결과를 출력한다.
- [x] 이력조회
  - [x] 실행한 게임의 이력을 볼 수 있다. '모든 자동차의 이동 횟수'와 '우승자'를 출력한다.

# 프로그램 제한사항

- [x] 자동차 이름은 최소 1글자에서 최대 5글자이다.
- [x] 자동차는 최소 2개 이상이어야 한다.
- [x] 자동차의 이름은 중복될 수 없다.
- [x] 시도할 횟수는 1 이상 정수여야 한다.
