# jwp-racingcar
<br>

## 프로그램 설명
- **이름** : 자동차 경주 게임
- **개요** : 복수의 **자동차 이름**, **이동 시도 횟수**를 입력 받아 자동차 경주 게임을 실행하는 프로그램이다. **게임 실행 결과**를 사용자에게 알려준다.

<br>

## 기능 요구사항

### ✅ POST /plays

**Request example**

```json
POST /plays HTTP/1.1
content-type: application/json; charset=UTF-8
host: localhost: 8080

{
    "names": "브리,토미,브라운",
    "count": 10
}
```

**Response example**

```json
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

- [x] 자동차 경주 진행에 대한 웹 요청을 받을 수 있다.
    - [x] JSON 형태로 입력을 받는다.
        - [x] 참여자들의 이름을 입력받는다.
        - [x] 시도 횟수를 입력받는다.
    - [x] `/plays`로 `POST` 요청을 보낼 시 응답한다.
- [x] 자동차 경주 진행 결과에 대한 웹 응답을 전달할 수 있다.
    - [x] JSON 형태로 전달한다.
        - [x] 우승자들의 이름을 전달한다.
        - [x] 참여자들의 정보를 전달한다.
            - [x] 모든 참여자들의 이름을 전달한다.
            - [x] 모든 참여자들의 이동 거리를 전달한다.
            - [x] 이동 거리의 내림차순으로 정렬 후 전달한다.
    - [x] 성공 시 STATUS CODE `200`를 반환한다.
- [x] 자동차 경주 게임 플레이 이력을 DB에 저장한다.
- [x] H2 Database에 저장된다.
- [x] 저장되는 정보는 다음과 같다.
    - [x] 플레이 횟수
    - [x] 플레이어 별 최종 이동 거리(이름, 최종 위치)
    - [x] 우승자
    - [x] 플레이한 날짜/시간

### ✅ GET /plays

**Request example**

```json
GET /plays HTTP/1.1
```

**Response example**

```json
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

- [x] 자동차 경주 플레이 이력 조회 요청을 받는다.
    - [x] `/plays`로 `GET` 요청을 보낼 시 응답한다.
- [x] 자동차 경주 플레이 이력을 반환한다.
    - [x] JSON 형태로 전달한다.
        - [x] 복수의 게임 결과를 반환한다.
          - [ ] 생성 일시 기준 내림차순으로 정렬한다.
        - [x] 하나의 게임 결과는 다음 정보를 갖고 있다.
          - [x] 우승자 이름
          - [x] 이름, 위치 정보를 갖고 있는 자동차들
    - [x] 성공 시 STATUS CODE `200`를 반환한다.

### 요청 및 응답 에러

- [x] 요청 또는 응답 실패 시 다음과 같은 STATUS CODE를 반환한다.
    - [x] 사용자 입력이 잘못되었을 때는 `400`을 반환한다.
    - [x] 정의되지 않은 경로로 요청하는 경우 `404`를 반환한다.
    - [x] 정의되지 않은 HTTP 메서드를 호출했을 때는 `405`를 반환한다.
    - [x] 서버 내부에서 에러가 발생했을 때는 `500`을 반환한다.

### 콘솔 애플리케이션 관련 리팩터링
- [ ] 콘솔 애플리케이션의 출력을 변경한다.
  - [ ] 웹 애플리케이션과 동일하게 출력한다.
  - [ ] 웹 애플리케이션과의 중복 코드를 제거한다.
