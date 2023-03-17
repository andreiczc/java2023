package ro.ase.lab3;

import java.io.*;
import java.util.*;

public class Application {

    public static void printToFile(Set<Integer> values, String fileName) {
        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(fileName);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);

            for(int value : values) {
                dataOutputStream.writeInt(value);
            }

            bufferedOutputStream.flush();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't open file");
        } catch (IOException e) {
            System.out.println("Couldn't write to file");
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                System.out.println("Couldn't close the file");
            }
        }

    }

    public static int[] addElement(int[] original, int value) {
        int[] result = new int[original.length + 1];
        for(int i = 0; i < original.length; ++i) {
            result[i] = original[i];
        }
        result[original.length] = value;

        return result;
    }

    public static void main(String[] args) {
        int[] array = new int[5];
        for(int i = 0; i < array.length; ++i) {
            array[i] = i;
        }

        System.out.println("Initial: " +  Arrays.toString(array));
        array = addElement(array, 5);
        System.out.println("After: " +  Arrays.toString(array));

        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 5; ++i) {
            list.add(i);
        }
        System.out.printf("Initial: ");
        /*for(int element : list) {
            System.out.printf("%d ", element);
        }*/
        for(int i = 0; i < list.size(); ++i) {
            System.out.printf("%d ", list.get(i));
        }
        System.out.println();
        list.remove(0);
        System.out.printf("After removal: ");
        for(int i = 0; i < list.size(); ++i) {
            System.out.printf("%d ", list.get(i));
        }
        System.out.println();
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.printf("After sorting: ");
        for(int i = 0; i < list.size(); ++i) {
            System.out.printf("%d ", list.get(i));
        }
        System.out.println();

        List<Integer> list2 = new LinkedList<>();

        Map<Integer, Person> map = new HashMap<>();
        map.put(1, new Person("name1", 21));
        map.put(3, new Person("name3", 24));
        map.put(2, new Person("name2", 23));

        Person person = map.get(2);
        System.out.printf("Person 2 is named %s and is aged %d\n",
                person.getName(), person.getAge());

        for(Map.Entry<Integer, Person> pair : map.entrySet()) {
            System.out.printf("Key %d. Person named %s and aged %d\n",
                                    pair.getKey(), pair.getValue().getName(), pair.getValue().getAge());
        }
        Map<Integer, Person> orderedMap = new TreeMap<>();
        Map<Integer, Person> orderedMap2 = new LinkedHashMap<>();

        map.remove(2);
        for(Map.Entry<Integer, Person> pair : map.entrySet()) {
            System.out.printf("Key %d. Person named %s and aged %d\n",
                    pair.getKey(), pair.getValue().getName(), pair.getValue().getAge());
        }

        Set<Integer> set = new HashSet<>();
        List<Integer> houses = List.of(6, 1, 2, 1, 1, 2, 3, 4, 5);

        for(int house : houses) {
            if(set.contains(house)) {
                System.out.println("Duplicate found");
                continue;
            }

            set.add(house);
        }

        for(int value : set) {
            System.out.println(value);
        }

        Set<Integer> orderedSet = new LinkedHashSet<>();

        // print set to a file
        printToFile(set, "values.txt");
    }

}
