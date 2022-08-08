public class Main {

    public static void main(String[] args) {
        final int BUYERS = 10;
        final Shop shop = new Shop();
        final Manufacturer manufacturer = new Manufacturer(shop);

        for (int i = 0; i < BUYERS; i++) {
            new Thread(null, shop::sellCar, "Покупатель №" + (i + 1)).start();
        }
        new Thread(null, manufacturer::makeCar, "Завод-изготовитель").start();
    }
}