### 요구 사항 목록

- 스프링 연결
    - [x] POST `/plays` API
        - [x] Request DTO 작성
        - [x] Response DTO 작성
        - [x] Request로 들어온 입력으로 자동차 게임 진행
        - [x] Response로 자동차 게임 결과 전송
    - [x] exception handler적용

- DB
    - [x] H2 Database 세팅
    - [x] `game` table 세팅
    - [x] `player_result` table 세팅
    - [x] DB 연결
- 게임 결과 조회
    - [x] 게임 별 승자, 게임 플레이어 최종 결과(이름, 최종 위치)
- 기존 기능 수정
    - [x] 출력 방식 수정
- 리팩터링
    - [x] 중복 코드 제거
