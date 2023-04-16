package racingcar;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase
@Transactional
/*
학습용 주석입니다. 불필요한 주석은 달지 않아야 한다는 것 알고 있습니다 :)
@AutoConfigureTestDatabase:
    @SpringBootTest에 의해 ApplicationContext를 새로 만들 때,
    인메모리 DB를 사용중인 경우 리소스의 sql을 사용해 DB 초기화가 진행된다. (기본값, 변경가능)
    하지만, JVM에 올라간 인메모리 DB는 유지되므로 'Table already exists' 오류가 발생한다.
    이를 해결하기 위해 자동 설정으로 다른 DB를 실행하고, 기존 DB를 대체한다.

@Transactional:
    ApplicationContext가 바뀌지 않는 이상, 기존 테스트에서 삽입한 데이터는 유지되어 다른 테스트에 영향을 미친다.
    이를 해결하기 위해 각 테스트 실행 후 쿼리들을 commit하지 않고, rollback한다.
*/
public @interface DatabaseTest {

}
