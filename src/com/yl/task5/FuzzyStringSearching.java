package com.yl.task5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FuzzyStringSearching {
    public static void main(String[] args) throws IOException {
        System.out.println(new FuzzyStringSearching().fuzzySearch());
    }

    public String fuzzySearch() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String[] lines = new String[2];

            for (int i = 0; i < lines.length; i++) {
                lines[i] = reader.readLine();
            }

            // Проверка на null и пустую строку.
            if (check(lines)) {
                return "Некорректный ввод данных";
            }

            char[] oneSymbols = lines[0].toCharArray();
            char[] twoSymbols = lines[1].toCharArray();
            int iterationOne = 0;
            int iterationTwo = 0;
            for (char rev : oneSymbols) {
                while (iterationOne < twoSymbols.length) {
                    if (rev == twoSymbols[iterationOne]) {
                        iterationTwo += 1;
                        iterationOne += 1;
                        break;
                    }
                    iterationOne += 1;
                }
            }
            return iterationTwo == oneSymbols.length ? "true" : "false";

        }
    }

    public static boolean check(String[] lines) {
        if (lines == null) {
            return true;
        }

        for (String s : lines) {
            if (s == null || s.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
