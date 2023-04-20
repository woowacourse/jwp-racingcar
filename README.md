# jwp-racingcar

## 요구사항

- [x] 자동차 경주 코드 가져오기
- [x] 웹 요청/응답 구현하기
    - 요청
        ```http request
        POST /plays HTTP/1.1
        content-type: application/json; charset=UTF-8
        host: localhost:8080
      
        {
            "names": "브리,토미,브라운",
            "count": 10
        }
        ```

    - 응답
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
- [x] 게임 플레이 이력 조회 API 구현
    - 요청

      ```http request
      GET /plays HTTP/1.1
      ```

    - 응답
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
- [ ] 리팩터링 - 중복 코드 제거
