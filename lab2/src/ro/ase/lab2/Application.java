package ro.ase.lab2;

import ro.ase.lab2.exceptions.IllegalMoveException;
import ro.ase.lab2.models.MotorizedVehicle;
import ro.ase.lab2.models.NotMotorizedVehicle;

import java.util.Arrays;

public class Application {

    public int[] doubleArray(int[] original) {
        System.out.println(original);

        int[] result = new int[original.length * 2];
        for(int i = 0; i < original.length; ++i) {
            result[i] = original[i];
            result[i + original.length] = original[i];
        }

        return result;
    }

    public static void main(String[] args) throws IllegalMoveException {
        MotorizedVehicle vehicle = new MotorizedVehicle();
        System.out.printf("Initial position: %d\n", vehicle.getPosition());
        vehicle.move(1);
        System.out.printf("Current position: %d\n", vehicle.getPosition());

        var vehicle2 = new NotMotorizedVehicle();
        System.out.printf("Initial position: %d\n", vehicle2.getPosition());
        vehicle2.move(1);
        System.out.printf("Current position: %d\n", vehicle2.getPosition());

        int[] input = new int[] {1, 1, 2, 2, 3, 3};
        int[] expectedOutput = new int[] {1, 1, 2, 2, 3, 3, 1, 1, 2, 2, 3, 3};
        int[] output = (new Application()).doubleArray(input);

        System.out.println("Equals: " + Arrays.equals(expectedOutput,
                                                output));

        System.out.println("Vehicle 1 and Vehicle 2 are equal: " +
                            vehicle.equals(vehicle2));

        System.out.println(vehicle);
        System.out.println(vehicle2);

        var previousPositions = vehicle.getPreviousPositions();
        System.out.println(Arrays.toString(previousPositions));

        vehicle.move(1);
        previousPositions = vehicle.getPreviousPositions();
        System.out.println(Arrays.toString(previousPositions));
    }

}
