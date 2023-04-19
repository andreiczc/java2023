package ro.ase.lab4;

import ro.ase.lab4.models.Car;
import ro.ase.lab4.models.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Car car = new Car(1998, Color.WHITE);
        car.move(100);
        System.out.printf("The tax is %f\n", car.computeTax());
        System.out.printf("Mean distance travelled is %f\n", car.getMeanDistance());
        Car car2 = new Car(1998, Color.BLACK);

        List<Car> cars = new ArrayList<>();
        cars.add(car);
        cars.add(car2);
        Collections.sort(cars);

        int originalCarsSize = cars.size();
        for(int i = 0; i < originalCarsSize; ++i) {
            cars.add(cars.get(i).clone());
        }
        System.out.printf("Cars length %d\n", cars.size());

        car.writeFieldsToFile("car.bin");
        car.writeObjectToFile("carObject.bin");
    }

}
