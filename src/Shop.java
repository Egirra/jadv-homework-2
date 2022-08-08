import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Shop {
    private Car car = new Car();
    private final int CARS = 10;
    private List<Car> cars = new ArrayList<>(CARS);
    private final int TIMEOUT = 1000;
    private final ReentrantLock locker = new ReentrantLock();
    private final Condition condition = locker.newCondition();

    public void addCar() {
        try {
            locker.lock();
            cars.add(car);
            condition.signal();
        } finally {
            locker.unlock();
        }
    }

    public synchronized void sellCar() {
        try {
            locker.lock();
            System.out.println(Thread.currentThread().getName() + " зашел в салон");
            while (getCars().isEmpty()) {
                System.out.println("Машин нет");
                condition.await();
            }
            Thread.sleep(TIMEOUT);
            System.out.println(Thread.currentThread().getName() + " уехал в новом автомобиле");
            getCars().remove(0);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    List<Car> getCars() {
        return cars;
    }
}