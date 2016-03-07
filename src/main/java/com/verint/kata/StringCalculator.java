package com.verint.kata;

import java.util.ArrayList;

public class StringCalculator {
    private static final String DELIMETER_START = "//";
    private static final int MAX_LIMIT = 1000;

    public int add(String number) {
        int[] processedInput = inputProcessor(number);
        int sum = 0;
        int num;
        for (int c = 0; c < processedInput.length; c++) {
            num = processedInput[c];
            sum += num;
        }
        return sum;
    }

    private int[] inputProcessor(String number) {
        String delimeter = ",";
        String input = "";

        ArrayList<String> invalidParam = new ArrayList<String>();
        String invalidMessage = "";

        if (number.startsWith(DELIMETER_START)) {
            if (number.indexOf("\n") > DELIMETER_START.length() + 1) {
                delimeter = number.substring(DELIMETER_START.length() + 1, number.indexOf("\n") - 1);
            } else {
                delimeter = number.substring(DELIMETER_START.length(), number.indexOf("\n"));
            }
            input = number.substring(number.indexOf("\n") + 1);
            input = input.replace(delimeter, ",");
        } else {
            input = number.replace("\n", ",");
        }

        String[] stringNumber = input.replaceAll("\\s+", "").split(",");
        int[] intNumber = new int[stringNumber.length];

        for (int i = 0; i < intNumber.length; i++) {
            if (!stringNumber[i].isEmpty()) {
                int num = Integer.parseInt(stringNumber[i]);
                if (num <= MAX_LIMIT) {
                    intNumber[i] = num;
                }

                if (num < 0) {
                    invalidParam.add(Integer.toString(num));
                    invalidMessage = "negatives not allowed: ";
                }
            }
        }

        if (!invalidParam.isEmpty()) {
            throwInvalidArgument(invalidMessage, invalidParam);
        }
        return intNumber;
    }

    private void throwInvalidArgument(String message, ArrayList<String> arguments) {
        throw new IllegalArgumentException(message + arguments.toString());
    }
}