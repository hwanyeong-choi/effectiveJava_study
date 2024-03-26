  # 아이템 1. 생성자 대신 정적 팩터리 메서드를 고려하라
## 핵심정리

- 장점
  - [1. 이름을 가질 수 있다. (동일한 시그니처의 생성자를 두개 가질 수 없다)](https://github.com/hwanyeongchoi/effectiveJava_study/blob/main/src/%EC%83%9D%EC%84%B1%EC%9E%90_%EB%8C%80%EC%8B%A0_%EC%A0%95%EC%A0%81_%ED%8C%A9%ED%84%B0%EB%A6%AC_%EB%A9%94%EC%84%9C%EB%93%9C%EB%A5%BC_%EA%B3%A0%EB%A0%A4%ED%95%98%EB%9D%BC/item01/Order.java)
  - [2. 호출될 때마다 인스턴스를 새로 생성하지 않아도 된다. (Boolean.valueOf)](https://github.com/hwanyeongchoi/effectiveJava_study/blob/main/src/%EC%83%9D%EC%84%B1%EC%9E%90_%EB%8C%80%EC%8B%A0_%EC%A0%95%EC%A0%81_%ED%8C%A9%ED%84%B0%EB%A6%AC_%EB%A9%94%EC%84%9C%EB%93%9C%EB%A5%BC_%EA%B3%A0%EB%A0%A4%ED%95%98%EB%9D%BC/item01/Settings.java)
  - [3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다. (인터페이스 기반 프레임워크, 인터페이스에 정적 메소드)](https://github.com/hwanyeongchoi/effectiveJava_study/blob/main/src/%EC%83%9D%EC%84%B1%EC%9E%90_%EB%8C%80%EC%8B%A0_%EC%A0%95%EC%A0%81_%ED%8C%A9%ED%84%B0%EB%A6%AC_%EB%A9%94%EC%84%9C%EB%93%9C%EB%A5%BC_%EA%B3%A0%EB%A0%A4%ED%95%98%EB%9D%BC/item01/HelloServiceFactory.java)
  - [4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다. (EnumSet)](https://github.com/hwanyeongchoi/effectiveJava_study/blob/main/src/%EC%83%9D%EC%84%B1%EC%9E%90_%EB%8C%80%EC%8B%A0_%EC%A0%95%EC%A0%81_%ED%8C%A9%ED%84%B0%EB%A6%AC_%EB%A9%94%EC%84%9C%EB%93%9C%EB%A5%BC_%EA%B3%A0%EB%A0%A4%ED%95%98%EB%9D%BC/item01/HelloServiceFactory.java)
  - [5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다. (서비스 제공자 프레임워크)](https://github.com/hwanyeongchoi/effectiveJava_study/blob/main/src/%EC%83%9D%EC%84%B1%EC%9E%90_%EB%8C%80%EC%8B%A0_%EC%A0%95%EC%A0%81_%ED%8C%A9%ED%84%B0%EB%A6%AC_%EB%A9%94%EC%84%9C%EB%93%9C%EB%A5%BC_%EA%B3%A0%EB%A0%A4%ED%95%98%EB%9D%BC/item01/HelloServiceFactory.java)
- 단점
  - [상속을 하려면 public이나 protected 생성자가 필요하니 정적 팩터리 메서드만 제공하기 어렵다](https://github.com/hwanyeongchoi/effectiveJava_study/blob/main/src/%EC%83%9D%EC%84%B1%EC%9E%90_%EB%8C%80%EC%8B%A0_%EC%A0%95%EC%A0%81_%ED%8C%A9%ED%84%B0%EB%A6%AC_%EB%A9%94%EC%84%9C%EB%93%9C%EB%A5%BC_%EA%B3%A0%EB%A0%A4%ED%95%98%EB%9D%BC/item01/AdvancedSettings.java)
  - [정적 팩터리 메서드는 프로그래머가 찾기 어렵다.](https://github.com/hwanyeongchoi/effectiveJava_study/blob/main/src/%EC%83%9D%EC%84%B1%EC%9E%90_%EB%8C%80%EC%8B%A0_%EC%A0%95%EC%A0%81_%ED%8C%A9%ED%84%B0%EB%A6%AC_%EB%A9%94%EC%84%9C%EB%93%9C%EB%A5%BC_%EA%B3%A0%EB%A0%A4%ED%95%98%EB%9D%BC/item01/Settings.java)


## 완벽 공략
  > p9, 열거 타입은 인스턴스가 하나만 만들어짐을 보장한다.
  > - 상수 목록을 담은 수 있는 데이터 타입.
  >> - 특정한 변수가 가질 수 있는 값을 제한할 수 있다. 타입-세이프티(Type-Safety)를 보장할 수 있다.
  >> - 싱글콘 패턴을 구현할 때 사용하기도 한다.
  
  >질문1) 특정 enum 타입이 가질 수 있는 모든 값을 순회하며 출력하라.
  >>  Arrays.stream(OrderStatus.values()).forEach(it -> System.out.println(it));

  >질문2) enum은 자바의 클래스처럼 생성자, 메소드, 필드를 가질 수 있는가?
  > ```java
  >> public enum OrderStatus {
  >>  PREPARING(1), SHIPPED(2), DELIVERING(3), DELIVERY(4);
  >>  private int number;
  >> 
  >>  OrderStatus(int number) {
  >>      this.number = number;
  >>  }
  >> }
  
  >질문3) enum의 값은 == 연산자로 동일성을 비교할 수 있는가?
  >> - OrderStatus.DELIVERING == OrderStatus.DELIVERY
  
  >과제) enum을 key로 사용하는 map정의하고 enum을 담을 set을 정의하라
  >> - EnumMap<OrderStatus, String> orderStatusStringEnumMap = new EnumMap<>(OrderStatus.class);
  >> - EnumSet<OrderStatus> orderStatuses = EnumSet.allOf(OrderStatus.class);

  ---

  > p9, 같은 객체가 자주 요청되는 상황이라면 플라이웨이트 패턴을 사용할 수 있다.
  >> 완벽 공략 2. 플라이웨이트 패턴
  >>> - 객체를 가볍게 만들어 메모리 사용을 줄이는 패턴
  >>> - 자주 변하는 속성(또는 외적인 속성, extrinsit)과 변하지 않는 속성(또는 내적인 송정, intrinsit)을 분리하고 재사용하여 메모리 사용을 줄일 수 있다.

  ---

  > p10, 자바 8부터는 인터페이스가 정적 메서드를 가질 수 없다는 제한이 풀렸기 때문에 인스턴스화 불가 동반 클래스를 둘 이유가 별로 없다.
  > ``` java
  >>  public interface HelloService {
  >> 
  >>    String hello();
  >>    
  >>    static String hi() {
  >>         return "hi";
  >>    }
  >>
  >>    static String by() {
  >>         return "bye";
  >>     }
  >>  }

  > p11, 서비스 제공자 프레임워크를 만드는 근간이 된다. 자바 8과 9에서 주요 인터페이스의 변화
  >> 자바 8과 9에서 주요 인터페이스의 변화
  >>> - 기본 메소드 (default method)와 정적 메소드를 가질 수 있다.
  >>> - 기본 메소드
  >>>   - 인터페이스에서 메소드 선언 뿐 아니라, 기본적인 구현체까지 제공할 수 있다.
  >>>   - 기존의 인터페이스를 구현하는 클래스에 새로운 기능을 추가할 수 있다.
  >>> - 정적 메소드
  >>>   - 자바 9부터 private static 메소드도 가질 수 있다.
  >>>   - 단, private 필드는 아직도 선안할 수 없다.
  
  >> 질문1) 내림차순으로 정렬하는 Comparator를 만들고 List<Integer>를 정렬하다
  > ``` java
  >> public class Assignment {
  >     public static void main(String[] args) {
  >         List<Integer> numberList = Arrays.asList(1, 2, 3, 4 ,5 ,6 ,7 ,8 ,9, 10);
  >         Comparator<Integer> desc = (o1, o2) -> o2 - o1;
  >         numberList.sort(desc);
  > 
  >         System.out.println(numberList);
  > 
  >  }

  >> 질문2) 질문1에서 만든 Comparator를 사용해서 오름차순으로 정렬하라.
  > ``` java
  > public class Assignment {
  >     public static void main(String[] args) {
  >         List<Integer> numberList = Arrays.asList(1, 2, 3, 4 ,5 ,6 ,7 ,8 ,9, 10);
  >         Comparator<Integer> desc = (o1, o2) -> o2 - o1;
  >         numberList.sort(desc.reversed());
  > 
  >         System.out.println(numberList);
  > 
  >  }

  ---

  > p12, 서비스 제공자 인터페이스가 없다면 각 구현체를 인스턴스로 만들 때 리플렉션을 사용해야한다.
  >> 서비스 제공자 프레임워크 (확장 가능한 애플리케이션을 만드는 방법)
  >>> - 주요 구성 요소
  >>>   - 서비스 제공자 인터페이스 (SPI)와 서비스 제공자(서비스 구현체)
  >>>   - 서비스 제공자 등록 API (서비스 인터페이스의 구현체를 등록하는 방법)
  >>>   - 서비스 접근 API (서비스의 클라이언트가 서비스 인터페이스의 인스턴스를 가져올 때 사용하는 API)
  >>> - 다양한 변형
  >>>   - 브릿지 패턴
  >>>   - 의존 객체 주입 프레임워크
  >>>   - java.util.ServiceLoader
  >>>   ---
  >> 리플렉션
  >>> - 클래스로더를 통해 읽어온 클래스 정보("거울에 반사"된 정보)를 사용하는 기술
  >>> - 리플렉션을 사용해 클래스를 읽어오거나, 인스턴스를 만들거나, 메소드를 실행하거나, 필드의 값을 가져오거나 변경하는것이 가능하다.
  >>> - 언제 사용할까?
  >>>   - 특정 어노테이션이 붙어있는 필드 또는 메소드 읽어오기 (JUnit, Spring)
  >>>   - 특정 이름 패턴에 해당하는 메소드 목록을 가져와 호출하기 (getter, setter)

  > p12, 브리지 패턴
  >> - 구체적인것과 추상적인 부분을 분리해 그사이 다리를통해 연결한다
  >> - 구체적인것과 추상적인 것을 나누는 이유는 그 둘이 따로 영향을 주지 않으면서 각기 독립적으로 발전할 수 있게끔 하는 구조
  >> - 구체적으로 브릿지 패턴이 스프링이나 IOC프레임워크들이 제공하는 PSA 연결이 된다.

  > p12, 의존 객체 주입 프레임워크
