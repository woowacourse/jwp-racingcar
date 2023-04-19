# jwp-racingcar

## 요구사항
### 1. 요청/응답 구현
- [X] `/plays`로 GET요청을 받는 컨트롤러 메서드 구현
- [X] 요청/응답 DTO를 구현한다.
  - [X] `이름 목록`과 `시도 횟수`를 요청받는 DTO
  - [X] `우승자들`과 `참여자 결과(이름, 위치)`를 응답하는 DTO
- [X] `/plays`로 POST요청을 받는 컨트롤러 메서드 구현
  - [X] `전체 이력`을 조회하는 기능
  - [X] `이력가을 응답하는 DTO
    - [X] 승자들
    - [X] 각 차의 정보(이름, 위치)
### 2. 테이블 설계
- [x] 시도횟수를 저장한다.
- [x] 플레이어 별 최종 이동거리와 이름을 저장한다.
- [x] 우승자들을 저장한다.
- [x] 플레이한 날짜/시간을 저장한다.

### 3. DB 연동
- [x] 데이터베이스 의존성 추가 및 설정
- [x] 결과 저장 DAO 구현
  - [x] `JdbcTemplate`사용
---
## ERD
![제목 없는 다이어그램](https://user-images.githubusercontent.com/79090478/231478135-c77923b4-48a6-4cb6-b365-b9feb6d7d567.jpg)




## `/plays` POST 요청
### 요청
```http
POST /plays HTTP/1.1
...
Content-Type: application/json;charset=UTF-8
...
Host: localhost:8080
...

{
  "names":"망고, 루카, 현구막, 소니",
  "count":"10"
}
```

### 정상 응답
```http
HTTP/1.1 201
Content-Type: application/json
...

{
  "winners": "망고",
  "racingCars": [
    {
      "name": "망고",
      "position": 7
    },
    {
      "name": " 루카",
      "position": 3
    },
    {
      "name": " 현구막",
      "position": 3
    },
    {
      "name": " 소니",
      "position": 6
    }
  ]
}
```

### 예외
```http
HTTP/1.1 400
Content-Type: text/plain;charset=UTF-8
...
Connection: close

요청이 올바르지 않습니다.
```


## `/plays` GET 요청
### 요청
```http
GET /plays HTTP/1.1
...
Content-Type: application/json;charset=UTF-8
...
Host: localhost:8080
...

```

### 정상 응답
```http
HTTP/1.1 200
Content-Type: application/json
...

[
  {
    "winners":"망고,루카",
    "racingCars":[
      {
        "name":"망고",
        "position":5
      },
      {
        "name":"루카",
        "position":5
      }
    ]
  },
  {
    "winners":"루쿠",
    "racingCars":[
      {
        "name":"망고",
        "position":5
      },
      {
        "name":"루카",
        "position":3
      },
      {
        "name":"루쿠",
        "position":9
      },
      {
        "name":"현구막",
        "position":7
      },
      {
        "name":"소니 ",
        "position":6
      }
    ]
  }
]
```

### 예외
```http
HTTP/1.1 400
Content-Type: text/plain;charset=UTF-8
...
Connection: close

요청이 올바르지 않습니다.
```
