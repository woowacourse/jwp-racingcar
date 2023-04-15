# jwp-racingcar

## 요구사항

- [ ] 게임 플레이 이력 조회 API 구현
    - [x] 게임 별 모든 자동차 정보(name, position, is_winner) 조회 (최신 내림차순)
    - [ ] 응답 객체 생성
- [ ] 콘솔 어플리케이션 기존 기능 수정
    - [ ] 플레이의 중간 과정 출력 로직 삭제
    - [ ] 우승자와 player 별 최종 이동거리를 출력하도록 수정
- [ ] 리팩터링 - 중복 코드 제거
    - [ ] 자동차 레이스 로직 도메인으로 이동

## 웹 자동차 경주 게임 API

1. 게임 플레이
2. 플레이 이력 조회

### 1. 게임 플레이

- request

```http request
POST /plays HTTP/1.1
content-type: application/json; charset=UTF-8
host: localhost:8080

{
    "names": "브리,토미,브라운",
    "count": 10
}
```

- response

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

### 2. 플레이 이력 조회

- request

```http request
GET /plays HTTP/1.1
```

- response

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

## 코드리뷰 반영 및 리팩터링 목록

### 1단계 피드백

- [ ] 아래 피드백 학습 후 반영하기
    - [x] [빈 public 생성자 선언하지 않고도 PlayRequestDto의 필드를 json mapping 할 수 있게 만드는 방법은?](https://github.com/woowacourse/jwp-racingcar/pull/82#discussion_r1165362349)
        - [x] 기존 불필요한 디폴트 생성자 선언 코드 삭제
        - @RequestBody 사용법 학습
    - [ ] [컨트롤러의 책임 분리](https://github.com/woowacourse/jwp-racingcar/pull/82#discussion_r1165371523)
        - [x] 서비스 계층 도입
        - [ ] 자동차 레이스 로직 도메인으로 이동 (콘솔 어플리케이션 기능 수정 후)
        - [ ] 서비스 계층 트랜잭션 관련 고민
    - [ ] [@Transactional 사용 이유, 방법, 동작 방식](https://github.com/woowacourse/jwp-racingcar/pull/82#discussion_r1165351296)
    - [x] [@Component 와 @Repository 의 차이](https://github.com/woowacourse/jwp-racingcar/pull/82#discussion_r1165357619)
    - [x] [@Repository, @Component, @Service 어노테이션의 차이점](https://github.com/woowacourse/jwp-racingcar/pull/82#discussion_r1165357619)
    - [ ] [새 id값을 반환하는 insert와 그렇지 않은 insert 메서드에 대해서 어떻게 일관성을 부여할까?](https://github.com/woowacourse/jwp-racingcar/pull/82#discussion_r1165358853)
    - [ ] [관계형 데이터베이스에서 발생하는 테스트 독립성 문제](https://github.com/woowacourse/jwp-racingcar/pull/82#discussion_r1165381209)
    - [ ] [DB 테스트 격리 보장](https://github.com/woowacourse/jwp-racingcar/pull/82#discussion_r1165371523)
