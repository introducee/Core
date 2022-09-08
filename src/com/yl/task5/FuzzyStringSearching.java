package com.yl.task5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FuzzyStringSearching {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String oneLine = reader.readLine();
            String twoLine = reader.readLine();
            System.out.println(new FuzzyStringSearching().fuzzySearch(oneLine, twoLine));
        }
    }

    public boolean fuzzySearch(String oneLine, String twoLine) {
        char[] oneSymbols = oneLine.toCharArray();
        char[] twoSymbols = twoLine.toCharArray();
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
        return iterationTwo == oneSymbols.length;
    }
}
