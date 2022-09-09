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
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
            new Person(0, "Harry"),
            new Person(0, "Harry"),
            new Person(1, "Harry"),
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Emily"),
    };

    public static void main(String[] args) throws IOException {
        Writer writer = sortAndGroupFunction();
        writer.flush();
        writer.close();
    }

    public static Writer sortAndGroupFunction() throws IOException {
        Set<Person> unique = new HashSet<>(Arrays.asList(RAW_DATA))
                .stream()
                .sorted(Comparator.comparingInt(Person::id))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        Map<String, Long> map = unique.stream()
                .collect(Collectors.groupingBy(Person::name, Collectors.counting()));

        return print(unique, map);
    }

    public static Writer print(Set<Person> unique, Map<String, Long> map) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String lineSeparator = "\n\n";

        // Проверка ввода данных на null, null в имени, пустую строку.
        if (check(RAW_DATA)) {
            writer.write("Некорректный ввод данных");
            return writer;
        }

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
        return writer;
    }

    public static boolean check(Person[] RAW_DATA) {
        if (RAW_DATA == null) {
            return true;
        }

        for (Person person : RAW_DATA) {
            String line;
            if (person == null || (line = person.name) == null || line.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}