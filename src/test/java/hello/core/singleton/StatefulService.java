package hello.core.singleton;

public class StatefulService {
//    private int price;

    public void order(String name, int price) {
        System.out.printf("name = %s price = %d%n", name, price);
        this.getPrice(price);
    }

    public int getPrice(int price) {
        return price;
    }
}
