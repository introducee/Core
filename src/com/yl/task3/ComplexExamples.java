package com.yl.task3;

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
            new Person(4, "Jack"),
    };

    public static void main(String[] args) {
        Set<Person> unique = new HashSet<>(Arrays.asList(RAW_DATA))
                .stream()
                .sorted(Comparator.comparingInt(Person::id))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        Map<String, Long> map = unique.stream()
                .collect(Collectors.groupingBy(Person::name, Collectors.counting()));

        System.out.println("Сортировка по id:");
        for (Person i : unique) {
            System.out.println(i.id + " — " + i.name());
        }

        System.out.println("""

                Группировка по имени:""");
        for (Map.Entry<String, Long> i : map.entrySet()) {
            System.out.println("Key: " + i.getKey() + "\n" + "Value: " + i.getValue());
        }
    }
}