public class Manufacturer {
    private final int CARS = 10;
    private final int PRODUCTION = 3000;
    private Shop shop;

    public Manufacturer(Shop shop) {
        this.shop = shop;
    }

    public synchronized void makeCar() {
        for (int i = 0; i < CARS; i++) {
            try {
                Thread.sleep(PRODUCTION);
                shop.addCar();
                System.out.println(Thread.currentThread().getName() + " выпустил новый автомобиль Chevrolet");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}