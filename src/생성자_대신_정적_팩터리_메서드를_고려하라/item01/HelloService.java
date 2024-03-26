package 생성자_대신_정적_팩터리_메서드를_고려하라.item01;

// 완변공략: 서비스 구현체들이 다양한 형태로 구현체로 만들어진 수 있는 서비스 제공자 인터페이스 HelloService 서비스 제공자 인터페이스이다.

public interface HelloService {

    String hello();

    // ETC: 완벽공략에서 언급된 자바 8부터는 인터페이스가 정적 메서드를 가질 수 없다는 제한이 풀렸기 때문에 인스턴스화 불가 동반 클래스를 둘 이유가 별로 없다.
    static HelloService of(String lang) {
        if (lang.equals("ko")) {
            return new KoreanHelloService();
        } else {
            return new EnglishHelloService();
        }
    }

    static String hi() {
        return "hi";
    }

    static String by() {
        return "bye";
    }

}
