# jwp-racingcar

## 웹 요청 / 응답 구현하기

### 경주 게임 시작
WebController
- [x] 게임 play Post 요청 받기
  - [x] 자동차 이름 분리
  - [x] 자동차 객체 생성
  - [x] 게임 결과 반환 (winners, racingCars)

Service
- [x] 게임 플레이

Dto
- [x] CarDto
- [x] RequestDto
- [x] ResponseDto

## DB 연동하기
- [x] 플레이 횟수, 플레이어 별 최종 이동거리, 우승자, 플레이한 날짜/시간 저장

## 리팩토링, 공부할 내용
- [x] 사용자가 입력한 값에 대한 Validation 구현
  - [x] 시도횟수는 양수여야 한다.
  - [x] 같은 게임에 한해서 차 이름은 중복될 수 없다.
- [x] 잘못된 값에 대한 예외처리
- [x] CAR_RESULT가 is_winner 컬럼을 갖도록 테이블 및 Dao 수정
  - CAR_RESULT가 is_winner 컬럼을 가짐으로써 조인이 필요 없어졌다. 게임 이력 조회의 책임 GameDao -> CarDao로 이동
- [x] 콘솔 애플리케이션 만들기
- [x] Dao가 뷰에 전달할 DTO를 반환하지 않도록 수정
  - DB 로직이 뷰를 알고 있는 것!
- [x] Exception.class에 대한 ExceptionHandler도 생성하기
  - 어떤 에러 코드를 사용하면 좋을까? -> 정의되지 않은 예외에 대해서는 서버의 문제로 간주하고 500(INTERNAL_SERVER_ERROR) 사용?
  - Exception.class를 catch 했다는 것은 예상치 못한 예외가 발생했다는 것이므로, 이에 대해서는 log를 남기도록 결정
  - 각 상황에 맞는 예외 메세지를 응답하고, 로그를 남겨야겠지만 어떤 예외인지 모르는 상황에서는 어떻게 처리하는 것이 최선일까? 
- [x] CacheDao에서 Cache(Map)에 저장하기 위해 Entity 클래스를 생성
- [x] CacheDao, InMemoryDao 클래스명 변경
- [x] Exception.class를 잡는 ExceptionHandler에서 어떤 예외가 발생하는지 알 수 있도록 수정
  - 고객에게 오류에 대한 메시지가 전달되어도 될지에 대해 고민
- [x] cars.moveResut를 실행하는 Game 객체 생성

## 궁금한 내용
- [x] CAR_RESULT와 PLAY_RESULT를 조인해서 조회하는 책임은 어떤 Dao 클래스가 가지는 것이 좋을까?
- [x] 사용자의 입력값에 대한 유효성 검사는 어떤 계층에서 이뤄져야 할까?
- [x] 현재는 순수 자바 코드로 작성된 도메인 객체가 있고, 해당 도메인에서 유효성 검사가 이뤄지고 있다. 보통은 어떻게 유효성 검사를 할까?
- [x] PLAY_RESULT의 winners column 제거
- [ ] DB에 종속적이지 않은 테스트를 작성하는 방법
- [ ] GamePlayResponseDto(View - Controller - Service 계층이 주고받는 Dto)의 생성자로 List<GameFinishedCarDto>(Service - Repository 계층이 주고받는 Dto)를 넘겨줘도 괜찮을까?
  - GamePlayResponseDto 생성자 내에 데이터를 변환하는 로직이 생기는 것에 대해 의문이 생긴다. 하지만, 서비스에 두기에는 서비스의 코드가 지저분해지는 것 같다.
- [x] BatchUpdate 사용해보기
- [x] BatchUpdate를 통해 여러 건의 데이터를 insert할 때 성능을 개선할 수 있다는 것은 알게 되었다. 그래서 학습을 위해 BatchUpdate를 사용했는데 10개 정도의 데이터를 저장할 땐 성능 차이가 크지 않았던 것 같다. 현업에서는 어떤 기준으로 성능 개선을 고려할까?
- [ ] 고객에게 예외 메세지가 전달되면 안 되는 이유?
  - 예외 메세지는 개발자가 보는 것인데, 이를 고객에게 전달하면 의도치 않은 정보까지 전달될 수 있기 때문? -> 보안의 문제
  - 그렇다면, 예외 메세지는 개발자가 보기 위해서만 쓰는 것?
## 2단계 요구사항
- [x] 게임 플레이 이력 조회 API 구현
  - [x] Request: GET /plays HTTP/1.1
  - [x] Response:
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
        }
      ]
    }
  - [x] 응답용 DTO 필요 -> List<ResponseDto>
    - 뷰에서 받는 데이터에 맞추기 위해 새로운 DTO 클래스를 생성하지 않고 List를 반환하는 것으로 결정
  - [x] ResponseDto 클래스명 변경 필요해보임
  - [x] SELECT 쿼리 작성
- [x] 기존 기능 수정 - 출력 방식 수정
  - [x] console application에서 플레이의 중간 과정을 출력하는 로직을 제거
  - [x] console application에서 web application과 동일하게 우승자와 player 별 최종 이동거리를 출력하도록 수정
- [x] 리팩터링 - 중복 코드 제거
  - [x] 두 application은 입출력과 데이터 저장 방식을 제외하고는 내부 비즈니스 로직은 동일하다.
  - [x] 두 application의 비즈니스 로직은 새로운 객체를 도출 하여 중복 제거를 할 수 있다.
