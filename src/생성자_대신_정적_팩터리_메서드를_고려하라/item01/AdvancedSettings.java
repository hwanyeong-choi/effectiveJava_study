package 생성자_대신_정적_팩터리_메서드를_고려하라.item01;

// 단점 1. 상속을 하려면 public이나 protected 생성자가 필요하니 정적 팩터리 메서드만 제공하기 어렵다

// 단점 1: Settings는 기본 생성자에 접근을 private로 막아두었기 때문에 상속이 불가능 하다.
// 단점 1: public class AdvancedSettings extends Settings { }

public class AdvancedSettings { }
