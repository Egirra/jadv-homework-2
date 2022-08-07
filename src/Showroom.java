import java.util.ArrayList;
import java.util.List;

public class Showroom {
    private final int CARS = 10;
    List<Car> cars = new ArrayList<>(CARS);
    Manufacturer manufacturer = new Manufacturer(this);

    public void sellCar() {
        manufacturer.sellCar();
    }

    public void releasedCar() {
        manufacturer.makeCar();
    }

    List<Car> getCars() {
        return cars;
    }
}