# jwp-racingcar

2단계 미션 진행

기존 1단계 리팩터링
- [x] 컨트롤러 패키징하기
- [] localtime 생성시간을, 서비스에서 run()메서드가 호출되는 순간으로 하기
- [x] 스프링빈 수동 등록하는 config 폴더 만들기
- [] car, game dto를 entity 객체로 수정 및 dao 패키지 산하로 이동

도메인 리팩터링
- [] 컨트롤러 재귀함수의 사용 수정하기
- [] 컨트롤러 i 수정
- [] 레이싱 게임 객체 생성
- [] localtime 생성시간을, racingGame 객체 생성되는 순간으로 하기
- [] car 객체 수정
- [] winner 객체 생성 

테이블 변경하기
- [] winner 테이블 생성

[] jdbcTemplate 객체 사용하기