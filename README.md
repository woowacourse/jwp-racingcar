## 🚀 기능 구현에 필요한 사항

### 1. 사용자의 입력

- [X] 자동차 이름 입력
    * 이름은 쉼표(,)를 기준으로 구분
    * 이름의 길이는 5자 이하
    * 이름은 중복될 수 없음
- [X] 시도(이동)할 횟수 입력
    * 시도 횟수는 1회 이상

### 2. 결과 출력

- [X] 우승자 출력
    * 시도(이동) 횟수가 완료되었을 때, 가장 멀리 간 자동차가 우승자가 된다
    * 우승자는 한 명 이상일 수 있다.

### 3. 로직

- [X] 이동 로직
    * 0 ~ 9 사이의 random값을 구한다.
    * 4 이상일 경우 전진하고 3 이하이면 멈춘다.

### 4. 웹 연동

- [X] 웹 요청 구현하기
    - 자동차 이름과 시도 횟수를 Json 형식으로 요청한다.
  ```
  POST /plays HTTP/1.1
  content-type: application/json; charset=UTF-8
  host: localhost:8080

   {
       "names": "브리,토미,브라운",
       "count": 10
   }
  ```
- [X] 웹 응답 구현하기
    - 우승자와 자동차들의 최종 위치를 Json 형식으로 응답한다.

```
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

- [ ] 게임플레이 이력 조회 API 구현
    - db에 저장된 플레이 이력을 요청하면 응답한다.
        - request

      ```GET /plays HTTP/1.1```
        - response
   ```
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

### 5. DB 연동
- [X] 자동차 경주 게임의 플레이 이력을 DB에 저장한다.
    - DB 저장 목록
        - 플레이 횟수(trialCount)
        - 플레이어 별 최종 이동 거리 (이름(name), 최종 위치(position))
        - 우승자(winners)
        - 플레이한 날짜/시간  
