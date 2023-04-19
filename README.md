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

- [x] DB에 저장된 플레이 이력 요청을 받아 응답한다.




### 서비스

- [x] 자동차 경주 게임을 진행 후 결과를 생성한다.

### 레포지토리

- [x] 자동차 경주 게임의 최종 우승자, 시도 횟수, 플레이 시간을 저장한다.
- [x] 자동차의 이름과 최종 위치를 저장한다.

### DB

- [x] Game 테이블 생성한다.
    - id, winners, trial_count, date
- [x] RacingCar 테이블 생성한다.
    - id, game_id, name, position
