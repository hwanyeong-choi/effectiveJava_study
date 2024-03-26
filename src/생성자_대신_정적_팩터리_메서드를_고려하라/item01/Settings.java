package 생성자_대신_정적_팩터리_메서드를_고려하라.item01;

// 장점 2. 호출될 때마다 인스턴스를 새로 생성하지 않아도 된다. (Boolean.valueOf)
// 단점 2. 정적 팩터리 메서드는 프로그래머가 찾기 어렵다.

public class Settings {

    private boolean useAutuSteering;

    private boolean useABS;

    private Difficulty difficulty;

    // 장점 2: 생성자를 private를 통해서 접근을 제어하였기때문에 생성을 제한할 수 있다.
    // 장점 2: 생성자를 private를 지정한다는 의미는 객체생성을 팩토리 메서드로 제한하겠다는 의미이다.
    private Settings() {}

    // 완변공략: Flyweight 패턴과 통용된다는것은 자주 사용하는 객체들을 만들어 두고
    // 완벽공략: 사용할때만 꺼내서 사용하는 개념이기에 통용된다고 이펙티브 자바에서 언급되었다.
    private static final Settings SETTINGS = new Settings();

    // 장점 2
    // 단점 2
    // 단점 2: java doc에서는 정적 메소드를 생성자로 표시하지 않기때문에 java doc에서 생성자로 구분되지 않아서 혼동이 올 수 있다.
    // 단점 2: 해결책으로는 약속된 팩토리 메서드 네이밍을 사용하자 of, valueOf, getInstance, instance, newInstance, create등과 같이 약속해서 해소하자.
    // 단점 2: 네이밍을 사용학기도하고 팩토리 메소드에 대한 상세 내용을 주석으로 작성하자
    public static Settings newInstance() {
        return SETTINGS;
    }

    public static void main(String[] args) {
        System.out.println(Settings.newInstance());
        System.out.println(Settings.newInstance());
        System.out.println(Settings.newInstance());
    }

}
