# jwp-racingcar

## 1단계 기능 요구사항

- [x] 자동차 경주 가져오기
- [x] 웹 요청 구현하기

    ```http request
    POST /plays HTTP/1.1
    content-type: application/json; charset=UTF-8
    host: localhost:8080
    
    {
        "names": "브리,토미,브라운",
        "count": 10
    }
    ```
- [x] 웹 응답 구현하기
  - [x] JSON 형식으로 반환
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
  - [x] 자동차 경주 게임의 플레이 이력을 DB에 저장

### 리팩토링 목록

- [ ] 콘솔 게임 코드 재입력
- [ ]  `@PostMapping` 어노테이션 사용시 url 작성법 수정
- [ ]  외래키 컬럼명 테이블명_필드명으로 수정

## 2단계 기능 요구사항

- [ ] 게임 플레이 이력 조회 API 구현
  - [ ] web application에서 DB에 저장된 플레이 이력을 요청하면 응답하는 기능을 구현
    - Request
    ```http request
    GET /plays HTTP/1.1
    ```
    - Response
    ```http request
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

- [ ] 기존 기능 수정 - 출력 방식 수정
  - [ ] console application에서 플레이의 중간 과정을 출력하는 로직을 제거
  - [ ] console application에서 web application과 동일하게 우승자와 player 별 최종 이동거리를 출력하도록 수정

- [ ] 리팩터링 - 중복 코드 제거
  - [ ] 두 application의 비즈니스 로직에서 새로운 객체를 도출 하여 중복 제거
