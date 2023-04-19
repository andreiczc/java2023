package ro.ase.lab4.models;

import ro.ase.lab4.interfaces.Taxable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Car extends Vehicle
        implements Taxable, Comparable<Car>, Cloneable, Serializable {

    private static final int TAX_FACTOR = 5;

    private Color color;
    private List<Integer> distances;

    public Car(int capacity, Color color) {
        super(capacity);
        this.color = color;
        this.distances = new ArrayList<>();
    }

    public float getMeanDistance() {
        if(distances.isEmpty()) {
            return 0;
        }

        float sum = 0;
        for(int distance : distances) {
            sum += distance;
        }

        return sum / distances.size();
    }

    public void writeFieldsToFile(String file) {
        try(DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            // id color distance1 distance2 ...
            out.writeInt(getId());
            out.writeChars(color.getColor());
            for(int distance : distances) {
                out.writeInt(distance);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeObjectToFile(String file) {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(this);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public float computeTax() {
        return getCapacity() * TAX_FACTOR;
    }

    @Override
    public void move(int distance) {
        System.out.printf("Vehicle with id %d has moved %d km\n",
                                getId(), distance);

        distances.add(distance);
    }

    @Override
    public int compareTo(Car o) {
        return (int)(o.getMeanDistance() - this.getMeanDistance());
    }

    @Override
    public String toString() {
        return String.format("Car with id %d has color %s and has a traveled a mean of %f",
                                getId(), color.getColor(), getMeanDistance());
    }

    @Override
    public Car clone() {
        Car car = new Car(this.getCapacity(), this.color);
        for(int distance : distances) {
            car.distances.add(distance);
        }

        return car;
    }
}
