# jwp-racingcar

<br>

# 📚 도메인 모델 네이밍 사전

| 한글명    | 영문명             | 설명                                               | 분류        |
|--------|-----------------|--------------------------------------------------|-----------|
| 자동차    | Car             | 이동과 이동 정보 상태를 가진 자동차 객체                          | class     |
| 자동차 이름 | CarName         | 자동차 이름 값을 의미하는 VO 객체                             | class     |
| 이동 정도  | Position        | 전진 정도를 의미하는 VO 객체                                | class     |
| 자동차들   | Cars            | 자료구조로 자동차 객체목록을 관리하고, 자동차들을 움직이고 점수를 계산하는 일급 컬렉션 | class     |
| 시도 횟수  | Trial           | 모든 자동차에 대한 이동 지시 횟수를 의미하는 VO 객체                  | class     |
| 결과     | Result          | 자동차와 그에 대응하는 이동 횟수 정보를 가진 DTO 객체                 | class     |
| 숫자 생성기 | NumberGenerator | Integer 하나를 만들어내는 인터페이스                          | interface |

<br>

# 🖌️프로그램 흐름도

```mermaid
flowchart
    subgraph client
        subgraph 기본 페이지
            A[자동차 이름 이동 횟수 입력 submit 버튼 클릭]
            D[응답 결과를 결과 창에 띄움]
            EX[에러 메세지를 담은 Alert창 띄우기]
        end
        subgraph /list.html 페이지
            G[url 접속]
            I[목록을 결과 페이지에 띄운다.]
        end

    end
    subgraph server
        B(유효성 검사) --> C(자동차 이동&우승자 도출) --> F(데이터베이스에 레이싱 정보, 자동차 정보 추가)
        H(데이터베이스로부터 레이싱 정보 불러오기)
    end
    A -->|/plays로 POST 요청 전송| B
    G -->|/plays로 GET 요청 전송| H
    F -->|경주 결과 데이터를 담은 응답 전송| D
    B -->|사용자의 입력 형식이 잘못된 경우| EX
    H -->|경주 결과 목록 데이터를 담은 응답 전송| I
```

# 👨‍🍳 기능 목록

## 컨트롤러(Controller)

### 웹 요청/응답 구현하기

웹을 통해 게임을 플레이 할 수 있도록 구현하세요.

- [x] 클라이언트로부터 다음과 같은 Post요청을 받을 수 있습니다.
  ```
  Request
  POST /plays HTTP/1.1
  content-type: application/json; charset=UTF-8
  host: localhost:8080
  
  {
    "names": "브리,토미,브라운",
    "count": 10
  }
  ```
    - [x] content-type은 application-json이다.
    - [x] charset은 utf-8이다.
    - [x] body 안에 자동차 이름 목록(names)와 이동 횟수(count) 정보가 들어있다.
        - [x] 자동차 이름 또는 이동 횟수로 잘못된 값이 들어오면 예외 처리를 한다.

- [x] 애플리케이션은 받은 Post 요청에 대해 자동차 경주를 진행하고, 우승자와 각 자동차들의 최종 위치를 JSON 형식으로 응답합니다.
  ```
  Response
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
    - [x] 우승자(winners)가 들어있다.
    - [x] 자동차들(racingCars) 정보가 들어있다.
        -[x] 자동차 이름(name)과 이동 정도(position)이 들어있다.

- [x] 클라이언트로부터 다음과 같은 GET 요청을 받는다.
  ```
  GET /plays HTTP/1.1
  ```
    - [x] 어플리케이션은 DB로부터 불러온 정보를 다음과 같은 형식으로 가공하여 응답한다.
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

### 출력 방식 수정

- [x] 콘솔 어플리케이션에서 플레이 중간과정을 출력하는 로직 제거
- [x] 콘솔 어플리케이션에서 웹 어플리케이션과 동일하게 우승자와 플레이어 별 최종 이동 거리를 출력하도록 수정

### 리팩토링

- [x] 콘솔 어플리케이션과 웹 어플리케이션의 중복 코드 제거

## DB(DAO)

- H2 데이터베이스를 사용한다.
- DB 테이블 설계
    - Database 명 : `racing_car`
        - Table 명 : `racing`

          | id | 플레이한 날짜/시간(created_at) |
          |-------|-----------------------|-----------------------------|
          | 1 | 2023-04-17 14:46:55.097777 |
        - Table 명 : `car_info`

          | id    | racing_id | 이름(name) | 최종 위치(position) | is_winnner |
                                                                                                                                                                                                              |-------|-----------|-----------|--------------------|------------|
          | 1     | 1         | backFox   | 3                  | false      |
          | 2     | 1         | amaranth  | 5                  | true       |
          | 3     | 1         | test      | 2                  | false      |
- 삽입(Insert)
    - [x] racing 테이블에 새 경주 기록을 추가한다.(플레이 횟수 데이터를 저장한다)
    - [x] car_info 테이블에 경주에 참여한 자동차 정보를 추가한다.
- 조회(Select)
    - [x] racing 테이블에 추가한 경주 기록의 id를 불러온다.
    - [x] 모든 경주 기록의 ID들을 불러온다.
    - [x] 특정 경주의 자동차 정보(car_info)들을 불러온다.
    - [x] racing의 우승자들을 조회한다.

# 프로그래밍 요구사항

- 도메인의 의존성을 최소한으로 구현한다.
- 한 줄에 점을 하나만 찍는다.
- 게터/세터/프로퍼티를 쓰지 않는다.
- 모든 객체지향 생활 체조 원칙을 잘 지키며 구현한다.
- 프로그래밍 체크리스트의 원칙을 지키면서 프로그래밍 한다.

# 📌 Commit Convention

커밋 메시지는 다음과 같은 형태로 작성합니다.

```Bash
> "커밋의 타입: 커밋 메세지 내용"
ex) "docs: 기능 목록 추가"
```

커밋의 타입은 아래 10가지 중 가장 적절한 것을 선택해 작성합니다.

|  커밋의 타입  |              설명               |
|:--------:|:-----------------------------:|
|   feat   |           새로운 기능 추가           |
|   fix    |             버그 수정             |
| comment  |        필요한 주석 추가 및 변경         |
|   test   |           테스트 코드 추가           |
|   docs   | 문서를 추가 혹은 수정 (ex. README 수정)  |
|  rename  |    파일 혹은 폴더명을 수정하거나 옮기는 작업    |
|  remove  |        파일을 삭제하는 작업만 수행        |
|  chore   |   빌드 태스크 업데이트, 패키지 매니저를 설정    |
| refactor |            코드 리팩토링            |
|  style   | 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우 |

- 상세한 컨벤션
  내용은 [Angular JS Git Commit Message Conventions](https://gist.github.com/stephenparish/9941e89d80e2bc58a153)
  를 참고

<br>

# 📌 Code Convention

- 구글 Java 코딩 컨벤션을 준수합니다.
- IntelliJ의 Formatter를 적용합니다.