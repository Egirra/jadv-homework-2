public class Main {

    public static void main(String[] args) {
        final int BUYERS = 10;
        final Showroom showroom = new Showroom();

        for (int i = 0; i < BUYERS; i++) {
            new Thread(null, showroom::sellCar, "Покупатель №" + (i + 1)).start();
        }
        new Thread(null, showroom::releasedCar, "Завод-изготовитель").start();
    }
}