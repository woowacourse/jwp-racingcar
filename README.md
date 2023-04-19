# jwp-racingcar

## 요구사항

- [x] 게임 플레이 이력 조회 API 구현
    - [x] 게임 별 모든 자동차 정보(name, position, is_winner) 조회 (최신 내림차순)
    - [x] 응답 객체 생성
- [x] 콘솔 어플리케이션 기존 기능 수정
    - [x] 플레이의 중간 과정 출력 로직 삭제
    - [x] 우승자, 자동차 별 최종 이동거리를 출력하도록 수정
    - [x] 리팩터링 - 중복 코드 제거
        - [x] 자동차 경주 로직 도메인으로 이동
        - [x] 공통 비즈니스 로직을 최종 도메인 객체에 통합
            - 이를 위해 기존 콘솔 게임 도메인 코드 리팩터링
    - [x] 저장 기능
    - [x] 이력 조회 기능
    - [x] 여러 번 플레이 가능 + 이력 조회
      현

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

### 피드백 체크리스트

- 1단계 피드백
    - [x] [빈 public 생성자 선언하지 않고도 PlayRequestDto의 필드를 json mapping 할 수 있게 만드는 방법은?](https://github.com/woowacourse/jwp-racingcar/pull/82#discussion_r1165362349)
        - [x] 기존 불필요한 디폴트 생성자 선언 코드 삭제
        - [x] @RequestBody 사용법 학습
    - [x] [컨트롤러의 책임 분리](https://github.com/woowacourse/jwp-racingcar/pull/82#discussion_r1165371523)
        - [x] 서비스 계층 도입
        - [x] 자동차 레이스 로직 도메인으로 이동 (콘솔 어플리케이션 기능 수정 후)
    - [x] [@Transactional 사용 이유, 방법, 동작 방식](https://github.com/woowacourse/jwp-racingcar/pull/82#discussion_r1165351296)
        - [x] 트랜잭션 서비스 계층에 적용
        - [x] 성능 이점이 있는 readOnly 적용 (추가로 깊은 학습 필요)
    - [x] [@Component 와 @Repository 의 차이](https://github.com/woowacourse/jwp-racingcar/pull/82#discussion_r1165357619)
    - [x] [@Repository, @Component, @Service 어노테이션의 차이점](https://github.com/woowacourse/jwp-racingcar/pull/82#discussion_r1165357619)
        - [x] Spring PSA 학습
    - [x] [새 id값을 반환하는 insert와 그렇지 않은 insert 메서드에 대해서 어떻게 일관성을 부여할까?](https://github.com/woowacourse/jwp-racingcar/pull/82#discussion_r1165358853)
        - [x] 메서드 네이밍을 구체적으로 수정
        - [x] 한 가지 메서드가 두 가지 기능을 하고 있는 것 아닐까? => id 조회 메서드 분리
            - id 조회 SQL 작성: 가장 최근 시퀀스 값 조회하는 SCOPE_IDENTITY() 와 같은 함수는 DB 제품마다 차이가 크므로 사용하지 않음.
    - [ ] [관계형 데이터베이스에서 발생하는 테스트 독립성 문제](https://github.com/woowacourse/jwp-racingcar/pull/82#discussion_r1165381209)
    - [ ] [DB 테스트 격리 보장](https://github.com/woowacourse/jwp-racingcar/pull/82#discussion_r1165371523)

- 2단계 피드백
    - [ ] [Dao 클래스 분리 기준 정하기](https://github.com/woowacourse/jwp-racingcar/pull/105#discussion_r1167774162)
    - [x] [Dto 사용방식 검토](https://github.com/woowacourse/jwp-racingcar/pull/105#discussion_r1167789126)
    - [x] [도메인 관점에서 테이블 설계 재검토](https://github.com/woowacourse/jwp-racingcar/pull/105#discussion_r1167795949)

### 리팩터링 목록

- Spring 제공 기능 적용하기
    - [ ] 예외 처리 : ExceptionHandler 사용, 예외 발생 정보를 응답 객체에 담아 전달하기
    - [ ] 필요 시 Dto에 Validation 추가
    - [ ] Lombok 라이브러리 사용해 생성자, getter 메서드 코드 단순화
- [x] Dao 구현체가 아닌 인터페이스에 의존하도록 변경 (DB 교체 용이하게)
    - [x] 각 Dao를 여러 DB를 적용할 수 있게 인터페이스로 추상화
    - [x] 여러 개의 Dao에 대한 로직을 Repository 클래스로 추상화
