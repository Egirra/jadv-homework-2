import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Manufacturer {
    private Showroom showroom;
    private final int CARS = 10;
    private final int PRODUCTION = 3000;
    private final int TIMEOUT = 1000;
    private final ReentrantLock locker = new ReentrantLock();
    private final Condition condition = locker.newCondition();

    public Manufacturer(Showroom showroom) {
        this.showroom = showroom;
    }

    public synchronized void makeCar() {
        for (int i = 0; i < CARS; i++) {
            try {
                locker.lock();
                Thread.sleep(PRODUCTION);
                showroom.getCars().add(new Car());
                System.out.println(Thread.currentThread().getName() + " выпустил новый автомобиль Chevrolet");
                condition.signal();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                locker.unlock();
            }
        }
    }

    public synchronized void sellCar() {
        try {
            locker.lock();
            System.out.println(Thread.currentThread().getName() + " зашел в салон");
            while (showroom.getCars().isEmpty()) {
                System.out.println("Машин нет");
                condition.await();
            }
            Thread.sleep(TIMEOUT);
            System.out.println(Thread.currentThread().getName() + " уехал в новом автомобиле");
            showroom.getCars().remove(0);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            locker.unlock();
        }
    }
}