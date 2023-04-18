# jwp-racingcar

## 개요

해당 레포지토리는 자동차 경주 게임을 웹 애플리케이션으로 전환하고 DB를 연동한 레포지토리입니다.

## 기능 목록

### 컨트롤러

- [x] 자동차 경주 게임을 플레이하기 위한 요청을 받아 우승자와 최종 위치를 응답한다.

    - 요청 형식
        ```text
        POST /plays HTTP/1.1
        
        content-type: application/json; charset=UTF-8
        host: localhost: 8080
        
        {
            "names": "브리,토미,브라운",
            "count": 10
        }
        ```

    - 응답 형식
        ```text
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
                }
            ]
        }
        ```
- [ ] 자동차 경주 게임 플레이 이력 조회 요청을 받아 플레이 이력을 응답한다.

    - 요청 형식
        ```text
        GET /plays HTTP/1.1
        ```

    - 응답 형식
        ```text
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

### 서비스

- [x] 자동차 경주 게임을 진행 후 결과를 생성한다.
- [x] 자동차 경주 게임 플레이 이력을 조회한다.

### 레포지토리

- [x] 자동차 경주 도메인을 저장한다.
- [x] 자동차 경주 도메인 목록을 조회한다.

### Dao

- [x] 자동차 경주 게임 정보를 저장한다.
- [x] 자동차 경주 게임 정보를 조회한다.
- [x] 자동차 경주가 끝난 자동차 정보를 저장한다.
- [x] 자동차 경주가 끝난 자동차 정보를 조회한다.
- [x] 자동차 경주 우승자 정보를 저장한다.

### DB

- [x] RacingGame 테이블 생성한다.
    - id, trial_count, date
- [x] RacingCar 테이블 생성한다.
    - id, game_id, name, position
- [x] RacingWinner 테이블 생성한다.
    - id, game_id, car_id
