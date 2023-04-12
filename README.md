# jwp-racingcar

## 요구사항
### 1. 요청/응답 구현
- [X] `/plays`로 요청을 받는 컨트롤러 메서드 구현
- [X] 요청/응답 DTO를 구현한다.
  - [X] `이름 목록`과 `시도 횟수`를 요청받는 DTO
  - [X] `우승자들`과 `참여자 결과(이름, 위치)`를 응답하는 DTO

### 2. 테이블 설계
- [ ] 시도횟수를 저장한다.
- [ ] 플레이어 별 최종 이동거리와 이름을 저장한다.
- [ ] 우승자들을 저장한다.
- [ ] 플레이한 날짜/시간을 저장한다.

### 3. DB 연동
- [ ] 데이터베이스 의존성 추가 및 설정
- [ ] 결과 저장 DAO 구현
  - [ ] `JdbcTemplate`사용
