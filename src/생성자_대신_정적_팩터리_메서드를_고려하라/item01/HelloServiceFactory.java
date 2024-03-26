package 생성자_대신_정적_팩터리_메서드를_고려하라.item01;

//  장점 3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다. (인터페이스 기반 프레임워크, 인터페이스에 정적 메소드)
//  장점 4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다. (EnumSet)
//  장점 5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다. (서비스 제공자 프레임워크)

import java.util.Optional;
import java.util.ServiceLoader;

public class HelloServiceFactory {

    public static void main(String[] args) {

        // 장점 3
        // 장점 4
        HelloService eng = HelloService.of("ko");
        HelloService ko = HelloService.of("en");

        // 장점 5
        // 장점 5: HelloService로 구현된 클래스를 Iterable 복수를 가져온다
        ServiceLoader<HelloService> loader = ServiceLoader.load(HelloService.class);

        // 장점 5: HelloService로 구현된 클래스 복수중 가장 첫번째꺼를 가져와라 이때는 있을 수도 없을수도 있기때문에 Optional이다.
        // 장점 5: 서비스로더를 사용하면 의존성이 제거된다 import를 하지 않아도 구현체를 사용할 수 있다.
        Optional<HelloService> helloServiceOptional = loader.findFirst();

        // 장점 5: 만약 값이 존재한다면 람다식을 실행해라.
        helloServiceOptional.ifPresent(h -> {
            System.out.println(h.hello());
        });

    }

}
