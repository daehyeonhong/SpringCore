package hello.singleton;

public class StatefulService {
//    private int price; // 상태를 유지하는 필드

    public int order(final String name,
                     final int price) {
        System.out.printf("name = %s, price = %d%n", name, price);
//        this.price = price; // 여기가 문제!
        return price;
    }
}
