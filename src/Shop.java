import java.util.ArrayList;
import java.util.List;

public class Shop {
    private Car car = new Car();
    private final int CARS = 10;
    private List<Car> cars = new ArrayList<>(CARS);
    private final int TIMEOUT = 1000;

    public synchronized void addCar() {
        cars.add(car);
        notify();
    }

    public synchronized void sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в салон");
            while (cars.size() == 0) {
                System.out.println("Машин нет");
                wait();
            }
            Thread.sleep(TIMEOUT);
            System.out.println(Thread.currentThread().getName() + " уехал в новом автомобиле");
            cars.remove(0);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}