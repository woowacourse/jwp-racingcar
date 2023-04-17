# jwp-racingcar

# ✨기능 구현 목록

- [x] 웹 컨트롤러 적용
    - [x] "/plays" post 요청에 대한 컨트롤러 작성
        - [x] requestBody로 받은 자동차 이름을 통한 자동차 생성
        - [x] requestBody로 받은 이동횟수 따른 게임 진행
        - [x] 게임 결과 응답
    - [x] "/plays" get 요청에 대한 컨트롤러 작성
        - [x] 게임 플레이 이력 응답
- [x] DB 적용
    - [x] table 생성
    - [x] 게임 별 게임결과 저장
    - [x] 게임 이력 조회

### 리팩터링 목록

- [x] console application 기존 기능 수정
    - [x] 플레이의 중간 과정 출력 로직 제거
    - [x] 우승자와와 플레이어별 이동거리를 출력하도록 수정
- [ ] console application 과 web application 의 중복 코드 제거
    - [ ] 두 application 은 입출력과 데이터 저장 방식을 제외하고는 내부 비즈니스 로직은 동일
    - [ ] 두 application 의 비즈니스 로직은 새로운 객체를 도출 하여 중복 제거 가능
