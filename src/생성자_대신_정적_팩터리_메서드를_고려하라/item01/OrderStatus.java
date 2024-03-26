package 생성자_대신_정적_팩터리_메서드를_고려하라.item01;


// 완벽공략: 열거 타입은 인스턴스가 하나만 만들어짐을 보장한다.
public enum OrderStatus {

    PREPARING(1), SHIPPED(2), DELIVERING(3), DELIVERY(4);

    private int number;

    OrderStatus(int number) {
        this.number = number;
    }

}
