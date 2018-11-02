package com.example.nickl.mvvmexample.utilities;

public final class StringUtilities {

    private StringUtilities() {
    }

    public static String stringFromNumbers(int... numbers) {
        StringBuilder sNumbers = new StringBuilder();
        for (int number : numbers) {
            sNumbers.append(number);
        }
        return sNumbers.toString();
    }
}
