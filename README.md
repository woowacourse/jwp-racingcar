# jwp-racingcar

# 기존 1단계 리팩터링
- [x] 컨트롤러 패키징하기
- [x] localtime 생성시간을, 서비스에서 run()메서드가 호출되는 순간으로 하기
- [x] 스프링빈 수동 등록하는 config 폴더 만들기
- [x] car, game dto를 entity 객체로 수정 및 dao 패키지 산하로 이동
- [x] carDao에 여러 데이터 한번에 insert하는 기능 추가

도메인 리팩터링
- [x] RacingGame 객체 생성
- [x] RacingGame 객체 생성에 따른 서비스 객체 수정
- [x] localtime 생성시간을, racingGame 객체 생성되는 순간으로 하기
- [x] car 객체 수정
- [x] 컨트롤러 재귀함수의 사용 수정하기
- [x] 컨트롤러 i 수정

테이블 변경하기
- [x] winner 테이블 생성
- [x] winnerEntity 생성 
- [x] winnerJdbcDao 생성
- [x] GameEntity 수정
- [x] winner테이블 생성에 따른 서비스 객체 수정
- [] int 타입 long으로 바꾸기?
- [] unsigned 추가하기?


시도해보기1
- [x] 도메인 객체에 테이블 속성 모두 삭제
- [x] 엔티티에 도메인 객체를 의존성 주입하여 사용하


# 2단계 미션 진행
[x] 게임 플레이 이력 조회 API 구현

기존 기능 수정 - 출력 방식 수정
- [x] console application의 출력을 변경합니다.
  - [x] console application에서 플레이의 중간 과정을 출력하는 로직을 제거
  - [x] console application에서 web application과 동일하게 우승자와 player 별 최종 이동거리를 출력하도록 수정

리팩터링 - 중복 코드 제거
- [x] console application과 web application의 중복 코드를 제거
  - [x] 두 application은 입출력과 데이터 저장 방식을 제외하고는 내부 비즈니스 로직은 동일
  - [x] 두 application의 비즈니스 로직은 새로운 객체를 도출 하여 중복 제거하기
    - [x] dao를 인터페이스로 만들어 웹dao와 콘솔 dao 분리
  

# 2단계 1차 수정 목록
- [x] dao repository로 이름 변경
- [] consoleDao에 AtomicInteger atomicInteger 사용하기
- [] 테스트 코드 작성
  - [] jdbcDao테스트
  - [] consoleDao 테스트
  - [] 서비스 테스트
  - [] 컨트롤러 테스트
  - [] 통합 테스트
- [] jdbcTemplate 객체 사용하기
  - [] carRepository 변경
  - [] gameRepository 변경
  - [] winnerRepository 변경


시도해보기2
- 도메인 객체를 엔티티로 사용하기
  - 저장할 때, 도메인 객체 저장
  - 조회할 때, 도메인 객체 조회
  - Car 객체의 경우 처음 carId와 gameId가문제임
