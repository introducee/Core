package com.yl.task3;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ComplexExamples {

    record Person(int id, String name) {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return id() == person.id() && name().equals(person.name());
        }

        @Override
        public int hashCode() {
            return Objects.hash(id(), name());
        }
    }

    private static final Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
            new Person(9, null),
    };

    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    static Set<Person> unique = new TreeSet<>(Comparator.comparingInt(s -> s.id));

    static List<Integer> discardedValue = new ArrayList<>();

    static Map<String, Long> map;

    public static void main(String[] args) throws IOException {
        if (RAW_DATA == null) {
            System.out.println("Получено невалидное значение");
        } else {
            sortAndGroupFunction();
            System.out.println("Индексы невалидных объектов: " + discardedValue.get(0) + "\n");
            writer.flush();
            writer.close();
        }
    }

    public static void sortAndGroupFunction() throws IOException {
        for (Person rawDatum : RAW_DATA) {
            if (rawDatum.name == null) {
                discardedValue.add(rawDatum.id);
            } else {
                unique.add(rawDatum);
            }
        }

        map = unique.stream()
                .collect(Collectors.groupingBy(Person::name, Collectors.counting()));

        writingToFile();
    }

    public static void writingToFile() throws IOException {
        String lineSeparator = "\n\n";

        writer.write("Сортировка по id:");
        for (Person i : unique) {
            writer.newLine();
            writer.write(i.id + " — " + i.name());
        }

        writer.write(lineSeparator + "Группировка по имени:");
        for (Map.Entry<String, Long> i : map.entrySet()) {
            writer.newLine();
            writer.write("Key: " + i.getKey() + "  Value: " + i.getValue());
        }
    }
}