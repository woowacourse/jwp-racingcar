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
    - [x] [Dao 클래스 분리 기준 정하기](https://github.com/woowacourse/jwp-racingcar/pull/105#discussion_r1167774162)
        - [x] join을 하더라도 핵심적으로 관여하는 객체에 대한 Dao 클래스가 책임을 가짐
        - [x] join 해서 조회 vs join이 필요한 부분을 비즈니스 로직에서 처리
            - join이 필요한 부분에 대한 로직은 최대한 DB에서 가져가는 게 책임 분리의 관점에서 좋다고 판단함
        - [x] [Dao 클래스를 관리하는 Repository 클래스에 대한 검토](https://github.com/woowacourse/jwp-racingcar/pull/105#discussion_r1173901912)
            - 두 테이블을 통합하는 도메인 객체나 Dto를 정의할 것이 아니면 Repository 사용 의도를 전달하기 어려울 것이라 판단함
            - Entity 를 적절하게 사용하는 쪽에 집중하기로 함에 따라 도메인-DB 연동 로직 변경
        - [x] [Dao 에 대한 Entity 정의하기](https://github.com/woowacourse/jwp-racingcar/pull/105#discussion_r1172280861)
    - [x] [Dto 사용방식 검토](https://github.com/woowacourse/jwp-racingcar/pull/105#discussion_r1167789126)
        - [x] [도메인과 영속 계층의 관계, DB에서 Dto vs Entity 고민](https://github.com/woowacourse/jwp-racingcar/pull/105#discussion_r1167789126)
        - [x] [도메인 관점에서 테이블 설계 재검토](https://github.com/woowacourse/jwp-racingcar/pull/105#discussion_r1167795949)
    - [x] FK 컬럼명을 참조하는 테이블과 일치하도록 변경, 관련 변수명 통일

### 리팩터링 목록

- Spring 제공 기능 적용하기
    - [x] 예외 처리 : ExceptionHandler 사용, 예외 발생 정보를 응답 객체에 담아 전달하기
        - [x] 인자값으로 인한 요청 오류, 그 외의 서버 오류를 구분하여 다른 HTTP 상태 코드 반환하기
        - [x] 커스텀 예외 사용하기
            - 모든 Exception을 처리하면, 원하지 않는 정보를 사용자에게 노출할 위험이 있으므로 관리 가능한 예외에 대해서만 처리하자.
        - [x] 예측하지 못한 예외에 대해서는 로그 남기기
    - [x] RestControllerAdvice 사용하여 ExceptionHandler 전역 처리
        - 예외 처리 페이지로 리다이렉트 시키려면 ControllerAdvice 를 사용하지만 현재 요구사항이 아니라고 판단하여 예외 관련 정보만 반환함.
    - [x] Dto에 Validation 어노테이션 추가
        - 도메인 검증 로직 이전에 일찍 검증할 수 있는 장점이 있다.
        - [x] 예외 메시지는 도메인 검증 로직과 동일하게 작성해야 할까? 구분할 필요가 있을까?
            - 메시지를 동일하게 할지는 프로젝트 협의에 따라 달라질 수 있을 것 같다.
            - 하지만 에러 메시지도 규격을 정해서, 발생 위치에 상관 없이 일관성 있게 던지는 게 좋을 것 같다.
            - @Valid를 통해 던진 예외로는 field명과 예외메시지를 같이 보여줄 수 있다는 장점이 있다.
        - [x] Validation을 사용한 Dto 검증에 대한 예외 처리 로직 리팩터링
    - [x] Lombok 라이브러리 추가, 생성자, getter 어노테이션 적용
- [x] Dao 구현체가 아닌 인터페이스에 의존하도록 변경 (DB 교체 용이하게)
    - [x] 각 Dao를 여러 DB를 적용할 수 있게 인터페이스로 추상화
    - [x] 여러 개의 Dao에 대한 로직을 Repository 클래스로 추상화
- [ ] Spring Test 관련 기능 사용
    - [ ] 불필요한 ApplicationContext 생성 지양, JdbcTest 사용하기
    - [ ] 다른 테스트 어노테이션 학습하고 사용하기
    - [ ] 테스트 격리 관련
