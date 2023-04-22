# jwp-racingcar

## 페어

| <img src="https://avatars.githubusercontent.com/u/51906604?v=4" alt="" width=200> | <img src="https://avatars.githubusercontent.com/u/82203978?v=4" alt="" width=200/> |
|:---------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------:|
|                         [저문](https://github.com/jeomxon)                          |                         [헤나](https://github.com/hyena0608)                         | |

- 규칙
    - 15분 마다 페어는 번갈아가면서 진행한다.
    - 휴식 필요하면 얘기한다.
    - 퇴근하려면 얘기한다.
    - 미리 학습하기보다 필요할 때 같이 학습한다.


## 1단계 요구사항

- 웹 애플리케이션
  - [X] spring-boot-starter-web 의존성 추가
- 웹 요청
  - [X] MediaType : application/json 형태로 요청 데이터 처리

```json
{
  "names": "헤나,저문",
  "count": 10
}
```

  - 웹 응답
    - [X] 게임 결과 응답 처리

```json
{
  "winners": "헤나,저문",
  "racingCars": [
    {
      "name": "헤나",
      "position": 5
    },
    {
      "name": "저문",
      "position": 5
    }
  ]
}
```


- DB 연동
  - [X] spring-boot-starter-jdbc 의존성 추가
  - [X] h2 database 의존성 추가
  - [X] 테이블 구성


- DB 저장
  - [X] 플레이어 정보를 저장한다.
  - [X] 게임 정보를 저장한다.


- 예외 처리
  - IllegalArgumentException
    - BAD_REQUEST(400) 응답
  - 나머지는 서버 내부 에러로 판단
    - INTERNAL_SERVER_ERROR(500)


- 테스트
  - RestAssured를 이용한 인수 테스트 
    - [X] 사용자 레이싱 게임 시작 요청 시 게임 결과 응답 시나리오 테스트


## 2단계 요구사항

- 레이싱 게임 결과 히스토리 조회 API
  - [X] GET `/plays`
